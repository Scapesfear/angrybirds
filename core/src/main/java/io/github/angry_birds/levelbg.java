package io.github.angry_birds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.ArrayList;
import java.util.List;

public class levelbg implements Screen {
    private final Main game;
    private Texture Background;
    private Texture levelbgsand;
    private Texture menubutton;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private final Sound sound;
    private final Vector3 touchPos;
    private Stage stage;
    private Window window;
    private Window windowwin;
    private Window windowlose;
    private Texture planet;
    private Catapult catapult;
    private List<Bird> birds;
    private Texture planet2;
    private List<Block> blocks;
    private List<Pig> pigs;

    public levelbg(Main game, Sound asound) {
        Sound sound1;
        this.game = game;
        sound1 = asound;
        sound1.stop();
        sound1.dispose();
        sound1 =Gdx.audio.newSound(Gdx.files.internal("ui/space1.mp3"));
        sound = sound1;
        touchPos = new Vector3();
    }

    public levelbg(Main game) {
        this.game = game;
        sound = Gdx.audio.newSound(Gdx.files.internal("ui/space1.mp3"));
        touchPos = new Vector3();}

    @Override
    public void show() {
        batch = new SpriteBatch();
        Background = new Texture(Gdx.files.internal("ui/screen.png"));
        levelbgsand = new Texture("ui/menu_background.png");
        Texture mainbutton = new Texture("ui/menuescreen.png");
        menubutton = new Texture("ui/pause.png");
        Texture reload = new Texture("ui/restart.png");
        Texture play = new Texture("ui/play_.png");
        catapult= new Catapult("ui/catapult.png", 400, 400);
        planet = new Texture("ui/planet.png");
        planet2 = new Texture("ui/planet.png");
        birds = new ArrayList<>();
        birds.add(new RedBird(0, 378, 400,55,55));
        birds.add(new Chuck(20, 335, 370,55,55));
        birds.add(new Bomb(55, 293, 315,60,60));
        blocks = new ArrayList<>();
        blocks.add(new Wood(90, 1170, 530, 1f, 1f));
        blocks.add(new Ice(0, 1170-100.5f+5+1+1, 730-100.5f+10, 1f, 1f));
        blocks.add(new Stone(90, 1170-201, 530, 1f, 1f));
        pigs = new ArrayList<>();
        pigs.add(new AlienPig(0, 1170-100.5f+25, 730-100.5f+25, 75f, 75f ));
        pigs.add(new HektorPorko(0, 1170-201+25+30+25+100+30, 530+25+100, 75f, 75f));
        pigs.add(new KingPig(0, 1170-201+25+30+30+50+15+5, 530+25+100-180+5, 75f, 75f));
        camera = new OrthographicCamera();
        viewport = new FitViewport(1600, 900, camera);
        camera.position.set(1600 / 2f, 900 / 2f, 0);
        camera.update();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = new TextureRegionDrawable(new TextureRegion(Background));
        windowStyle.titleFont = new BitmapFont();

        window = new Window("", windowStyle);
        window.setSize(618, 436);
        window.setVisible(false);
        window.setPosition((1600 - 618) / 2f, (900 - 436) / 2f);

        ImageButton.ImageButtonStyle resumeButtonStyle = new ImageButton.ImageButtonStyle();
        resumeButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(mainbutton));
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(play));
        ImageButton.ImageButtonStyle quitButtonStyle = new ImageButton.ImageButtonStyle();
        quitButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(reload));
        ImageButton resumeButton = new ImageButton(resumeButtonStyle);
        ImageButton quitButton = new ImageButton(quitButtonStyle);
        ImageButton playbutton = new ImageButton(playButtonStyle);
        window.add(resumeButton).padTop(200).padLeft(20).size(reload.getWidth(), reload.getHeight());
        window.add(quitButton).padTop(200).padLeft(20);
        window.add(playbutton).padTop(200).padLeft(20).size(reload.getWidth(), reload.getHeight());

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
                window.setVisible(false);
                Gdx.input.setInputProcessor(null);
            }
        });

        stage.addActor(window);

        Texture win = new Texture(Gdx.files.internal("ui/LevelCleared.png"));
        Window.WindowStyle windowStylewin = new Window.WindowStyle();
        windowStylewin.background = new TextureRegionDrawable(new TextureRegion(win));
        windowStylewin.titleFont = new BitmapFont();

        windowwin = new Window("", windowStylewin);
        windowwin.setSize(500, 900);
        windowwin.setVisible(false);
        windowwin.setPosition((1600 - 500) / 2f, (0) / 2f);

        ImageButton resumeButton1 = new ImageButton(resumeButtonStyle);
        ImageButton quitButton1 = new ImageButton(quitButtonStyle);
        ImageButton playbutton1 = new ImageButton(playButtonStyle);

        resumeButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        quitButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new levelbg(game));
            }
        });

        playbutton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                windowwin.setVisible(false);
                Gdx.input.setInputProcessor(null);
            }
        });

        windowwin.add(resumeButton1).padTop(500).size(reload.getWidth(), reload.getHeight());
        windowwin.row();
        windowwin.add(quitButton1).padTop(18);
        windowwin.row();
        windowwin.add(playbutton1).padTop(18).size(reload.getWidth(), reload.getHeight());
        stage.addActor(windowwin);


        Texture lose = new Texture(Gdx.files.internal("ui/Levelfailed.png"));
        Window.WindowStyle windowStylelose = new Window.WindowStyle();
        windowStylelose.background = new TextureRegionDrawable(new TextureRegion(lose));
        windowStylelose.titleFont = new BitmapFont();

        windowlose = new Window("", windowStylelose);
        windowlose.setSize(500, 900);
        windowlose.setVisible(false);
        windowlose.setPosition((1600 - 500) / 2f, (0) / 2f);

        ImageButton resumeButton2 = new ImageButton(resumeButtonStyle);
        ImageButton quitButton2 = new ImageButton(quitButtonStyle);
        ImageButton playbutton2 = new ImageButton(playButtonStyle);

        resumeButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        quitButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new levelbg(game));
            }
        });

        playbutton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                windowlose.setVisible(false);
                Gdx.input.setInputProcessor(null);
            }
        });

        windowlose.add(resumeButton2).padTop(500).size(reload.getWidth(), reload.getHeight());
        windowlose.row();
        windowlose.add(quitButton2).padTop(18);
        windowlose.row();
        windowlose.add(playbutton2).padTop(18).size(reload.getWidth(), reload.getHeight());
        stage.addActor(windowlose);
        long soundId = sound.play(1);
        sound.setLooping(soundId, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(levelbgsand, 0, 0, 1790f, viewport.getWorldHeight());
        batch.draw(menubutton, 50, 900 - 40 - menubutton.getHeight(), menubutton.getWidth(), menubutton.getHeight());
        batch.draw(planet, 346, 193, 215, 215);

        catapult.render(batch);
        for (Bird bird : birds) {
            bird.render(batch);
        }

        for (Block block : blocks) {
            block.render(batch);
        }
        for (Pig pig : pigs) {
            pig.render(batch);
        }
        batch.draw(planet2, 950+10+10, 73, 415, 415);
        batch.end();

        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            camera.unproject(touchPos.set(x, y, 0f), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
            if (touchPos.x >= 50 && touchPos.x <= 150 && touchPos.y >= 50 && touchPos.y <= 150) {
                game.setScreen(new MenuScreen(game));
                return;
            }
            if (touchPos.x >= 50 && touchPos.x <= 150 && touchPos.y >= 900 - 40 - menubutton.getHeight() && touchPos.y <= 900 - 40) {
                window.setVisible(true);
                Gdx.input.setInputProcessor(stage);
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            windowwin.setVisible(true);
            Gdx.input.setInputProcessor(stage);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            windowlose.setVisible(true);
            Gdx.input.setInputProcessor(stage);
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
        menubutton.dispose();
        Background.dispose();
        stage.dispose();
    }
}
