package io.github.angry_birds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class levelbg implements Screen {
    private final Main game;
    private Texture Background;
    private Texture levelbgsand;
    private Texture menubutton;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture backbutton;
    private Texture reload;
    private Viewport viewport;
    private final Sound sound;
    private final Vector3 touchPos;
    private Stage stage;
    private Window window;
    private Texture mainbutton;
    private Texture play;


    public levelbg(Main game,Sound asound) {
        this.game = game;
        sound = asound;
        sound.stop();
        touchPos=new Vector3();
    }
    public levelbg(Main game) {
        this.game = game;
        sound = Gdx.audio.newSound(Gdx.files.internal("ui/abf.mp3"));;
        touchPos=new Vector3();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        Background = new Texture(Gdx.files.internal("ui/screen.png"));
        levelbgsand = new Texture("ui/menu_background.png");
        backbutton = new Texture("ui/backnew.png");
        mainbutton = new Texture("ui/menuescreen.png");
        menubutton = new Texture("ui/pause.png");
        reload=new Texture("ui/restart.png") ;
        play=new Texture("ui/play_.png");
        camera = new OrthographicCamera();
        viewport = new FitViewport(1600, 900, camera);
        camera.position.set(1600 / 2f, 900 / 2f, 0);
        camera.update();
        stage=new Stage(viewport,batch);
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background= new TextureRegionDrawable(new TextureRegion(Background));
        windowStyle.titleFont = new BitmapFont();

        window=new Window("",windowStyle);
        window.setSize(618,436);
        window.setVisible(false);
        window.setPosition((1600-618) / 2f , (900-436) / 2f);

        ImageButton.ImageButtonStyle resumeButtonStyle = new ImageButton.ImageButtonStyle();
        resumeButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(mainbutton));
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(play)); // Set the up state image

        ImageButton.ImageButtonStyle quitButtonStyle = new ImageButton.ImageButtonStyle();
        quitButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(reload));  // Set the up state image
        ImageButton resumeButton = new ImageButton(resumeButtonStyle);
        ImageButton quitButton = new ImageButton(quitButtonStyle);
        ImageButton playbutton= new ImageButton(playButtonStyle);
        window.add(resumeButton).pad(10).size(reload.getWidth(), reload.getHeight());
        window.add(quitButton).pad(10);
        window.add(playbutton).pad(10).size(reload.getWidth(), reload.getHeight());
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                 game.setScreen(new levelbg(game));

            }
        });

        playbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.setVisible(false);  // Close the menu on "Resume"
                Gdx.input.setInputProcessor(null);  // Reset input processor

            }
        });

        stage.addActor(window);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        float scaledExitButtonWidth = (float) backbutton.getWidth() / 2;
        float scaledExitButtonHeight = (float) backbutton.getHeight() / 2;
        batch.draw(levelbgsand,0,0,1790f,viewport.getWorldHeight());
        batch.draw(backbutton, 50, 40, scaledExitButtonWidth, scaledExitButtonHeight);
        batch.draw(menubutton, 50, 900 - 40 - menubutton.getHeight(), menubutton.getWidth(), menubutton.getHeight());

        batch.end();
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            camera.unproject(touchPos.set(x, y, 0f), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
            if (touchPos.x >= 50 && touchPos.x <= 150 && touchPos.y >= 50 && touchPos.y <= 150) {
                this.dispose();
                game.setScreen(new MenuScreen(game));
            }
            if (touchPos.x >= 50 && touchPos.x <= 150 && touchPos.y >= 900-40- menubutton.getHeight() && touchPos.y <= 900-40) {
                window.setVisible(true);
                Gdx.input.setInputProcessor(stage);

            }
        }

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {dispose();}

    @Override
    public void dispose() {
        levelbgsand.dispose();
        batch.dispose();
        sound.dispose();
        backbutton.dispose();
        menubutton.dispose();
        Background.dispose();
        stage.dispose();
    }



}
