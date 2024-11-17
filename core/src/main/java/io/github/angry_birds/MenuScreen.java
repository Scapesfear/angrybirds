package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen {

    private final Main game;
    private SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;
    private Texture background;
    private Texture backbutton;
    private Texture asteroid1;
    private Texture asteroid2;
    private Texture asteroid3;
    private Texture world1;
    private Texture world2;
    private Texture world3;
    private final BitmapFont font;
    private OrthographicCamera camera;
    private Viewport viewport;
    private final Vector3 touchPos;
    private final Sound sound;
    private float asteroid1X, asteroid1Y;
    private float asteroid1SpeedX;
    private float asteroid1Rotation;
    private float asteroid1RotationSpeed;
    private float asteroid2X, asteroid2Y;
    private float asteroid2SpeedX;
    private float asteroid2Rotation;
    private float asteroid2RotationSpeed;
    private float asteroid3X, asteroid3Y;
    private float asteroid3SpeedX;
    private float asteroid3Rotation;
    private float asteroid3RotationSpeed;
    private float asteroid4X, asteroid4Y;
    private float asteroid4SpeedX;
    private float asteroid4Rotation;
    private Texture asteroid4;
    private float screenWidth;
    private Texture Background;
    private Texture worldboundary1;
    private Window window;
    private Stage stage;
    private Texture worldclouds;

    public MenuScreen(Main game, Sound sound) {
        this.game = game;
        this.sound = sound;
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2);
        touchPos = new Vector3();
    }

    public MenuScreen(Main game) {
        this.game = game;
        this.sound = Gdx.audio.newSound(Gdx.files.internal("ui/abf.mp3"));
        long soundId = sound.play(0.5f);
        sound.setLooping(soundId, true);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2);
        touchPos = new Vector3();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        Background = new Texture(Gdx.files.internal("ui/window.png"));
        background = new Texture(Gdx.files.internal("ui/newback.jpeg"));
        backbutton = new Texture(Gdx.files.internal("ui/backnew.png"));
        asteroid1 = new Texture(Gdx.files.internal("ui/asteroid1.png"));
        asteroid2 = new Texture(Gdx.files.internal("ui/frozepig.png"));
        asteroid3 = new Texture(Gdx.files.internal("ui/asteroid3.png"));
        asteroid4 = new Texture(Gdx.files.internal("ui/asteroid2.png"));
        world1 = new Texture(Gdx.files.internal("ui/world1.png"));
        world2 = new Texture(Gdx.files.internal("ui/redplanet.png"));
        world3 = new Texture(Gdx.files.internal("ui/jupiter.png"));
        worldboundary1 = new Texture(Gdx.files.internal("ui/grayity.png"));
        worldclouds = new Texture(Gdx.files.internal("ui/theclouds.png"));
        camera = new OrthographicCamera();
        viewport = new FitViewport(1600, 900, camera);
        camera.position.set(1600 / 2f, 900 / 2f, 0);
        screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        asteroid1X = -asteroid1.getWidth();
        asteroid1Y = ((screenHeight - asteroid1.getHeight()) / 2) + 15;
        asteroid2X = -asteroid2.getWidth() - 100;
        asteroid2Y = 700;
        asteroid3X = screenWidth + 200;
        asteroid3Y = 200;
        asteroid4X = screenWidth + 1600;
        asteroid4Y = 600;
        asteroid1SpeedX = 15f;
        asteroid2SpeedX = 10f;
        asteroid3SpeedX = 15f;
        asteroid4SpeedX = 30f;
        asteroid1Rotation = 0f;
        asteroid1RotationSpeed = 0;
        asteroid2Rotation = 360f;
        asteroid2RotationSpeed = 5f;
        asteroid3Rotation = 0f;
        asteroid3RotationSpeed = 0f;
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = new TextureRegionDrawable(new TextureRegion(Background));
        windowStyle.titleFont = new BitmapFont();

        window = new Window("", windowStyle);
        window.setSize(402, 300);
        window.setVisible(false);
        window.setPosition((1600 - 402) / 2f, (900 - 300) / 2f);
        Texture right=new Texture("ui/right.png");
        Texture wrong=new Texture("ui/wrong.png");
        ImageButton.ImageButtonStyle tick = new ImageButton.ImageButtonStyle();
        tick.imageUp = new TextureRegionDrawable(new TextureRegion(right));
        ImageButton.ImageButtonStyle notick = new ImageButton.ImageButtonStyle();
        notick.imageUp = new TextureRegionDrawable(new TextureRegion(wrong));
        ImageButton Tick = new ImageButton(tick);
        ImageButton Notick = new ImageButton(notick);

        window.add(Tick).padTop(150).padLeft(10);
        window.add(Notick).padTop(150).padLeft(50);
        Tick.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new levelbg(game,sound));
            }
        });
        Notick.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new levelbg(game,sound));
            }
        });

        stage.addActor(window);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(5 / 255f, 28 / 255f, 78 / 255f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        asteroid1X += asteroid1SpeedX * delta;
        if (asteroid1X > 1.5f * screenWidth) {
            asteroid1X = -asteroid1.getWidth();
        }
        asteroid1Rotation += asteroid1RotationSpeed * delta;
        if (asteroid1Rotation > 360) asteroid1Rotation -= 360;
        batch.draw(asteroid1, asteroid1X, asteroid1Y,
            (float) asteroid1.getWidth() / 2, (float) asteroid1.getHeight() / 2,
            asteroid1.getWidth(), asteroid1.getHeight(),
            0.75f, 0.75f, asteroid1Rotation,
            0, 0, asteroid1.getWidth(), asteroid1.getHeight(),
            false, false);

        asteroid2X += asteroid2SpeedX * delta;
        if (asteroid2X > screenWidth) {
            asteroid2X = -asteroid2.getWidth() - 100;
        }
        asteroid2Rotation -= asteroid2RotationSpeed * delta;
        if (asteroid2Rotation < 0) asteroid2Rotation += 360;
        batch.draw(asteroid2, asteroid2X, asteroid2Y,
            (float) asteroid2.getWidth() / 2, (float) asteroid2.getHeight() / 2,
            asteroid2.getWidth(), asteroid2.getHeight(),
            0.5f, 0.5f, asteroid2Rotation,
            0, 0, asteroid2.getWidth(), asteroid2.getHeight(),
            false, false);
        asteroid3X -= asteroid3SpeedX * delta;
        if (asteroid3X < -10) {
            asteroid3X = screenWidth + 200;
        }
        asteroid3Rotation += asteroid3RotationSpeed * delta;
        if (asteroid3Rotation > 360) asteroid3Rotation -= 360;
        batch.draw(asteroid3, asteroid3X, asteroid3Y,
            (float) asteroid3.getWidth() / 2, (float) asteroid3.getHeight() / 2,
            asteroid3.getWidth(), asteroid3.getHeight(),
            1f, 1f, asteroid3Rotation,
            0, 0, asteroid3.getWidth(), asteroid3.getHeight(),
            false, false);
        asteroid4X -= asteroid4SpeedX * delta;
        if (asteroid4X < -10) {
            asteroid4X = screenWidth + 1600;
        }
        asteroid4Rotation += 0;
        if (asteroid4Rotation > 360) asteroid4Rotation -= 360;
        batch.draw(asteroid4, asteroid4X, asteroid4Y,
            (float) asteroid4.getWidth() / 2, (float) asteroid4.getHeight() / 2,
            asteroid4.getWidth(), asteroid4.getHeight(),
            2f, 2f, asteroid4Rotation,
            0, 0, asteroid4.getWidth(), asteroid4.getHeight(),
            false, false);
        float scaledExitButtonWidth = (float) backbutton.getWidth() / 2;
        float scaledExitButtonHeight = (float) backbutton.getHeight() / 2;
//        batch.draw(backbutton, 50, 40, scaledExitButtonWidth, scaledExitButtonHeight);
        batch.draw(worldclouds, 0, 0,worldclouds.getWidth(),worldclouds.getHeight());
        batch.draw(world1, 300f, 300f, 0.9f * world1.getWidth(), 0.9f * world1.getHeight());
        batch.draw(world2, 700f, 300f, 0.9f * world1.getWidth(), 0.9f * world1.getHeight());
        batch.draw(world3, 1100f, 300f, 0.9f * world1.getWidth(), 0.9f * world1.getHeight());
        batch.draw(worldboundary1, 273f, 277f, 1.1f * world1.getWidth(), 1.1f * world1.getHeight());
        batch.draw(worldboundary1, 673f, 277f, 1.1f * world1.getWidth(), 1.1f * world1.getHeight());
        batch.draw(worldboundary1, 1073f, 277f, 1.1f * world1.getWidth(), 1.1f * world1.getHeight());
        batch.draw(backbutton, 50, 40, scaledExitButtonWidth, scaledExitButtonHeight);
        batch.end();
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            camera.unproject(touchPos.set(x, y, 0f), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
            if (touchPos.x >= 50 && touchPos.x <= 150 && touchPos.y >= 50 && touchPos.y <= 150) {
                game.setScreen(new FirstScreen(game,sound));
            }
            if (touchPos.x >= 737 && touchPos.x <= 900 && touchPos.y >= 337 && touchPos.y <= 500) {
                window.setVisible(true);
                Gdx.input.setInputProcessor(stage);
            }
            if (touchPos.x >= 1137 && touchPos.x <= 1300 && touchPos.y >= 337 && touchPos.y <= 500) {
                window.setVisible(true);
                Gdx.input.setInputProcessor(stage);
            }
            if (touchPos.x >= 337 && touchPos.x <= 500 && touchPos.y >= 337 && touchPos.y <= 500) {
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
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        asteroid2.dispose();
        asteroid3.dispose();
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
        background.dispose();
        backbutton.dispose();
        asteroid1.dispose();
        world1.dispose();
        world2.dispose();
        world3.dispose();
        worldboundary1.dispose();
        stage.dispose();
        worldclouds.dispose();
        asteroid4.dispose();

    }
}
