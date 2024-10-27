package io.github.angry_birds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class FirstScreen implements Screen {

    private final Main game;
    private Texture playButton;
    private Texture exitButton;
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Vector3 touchPos;
    private final Sound sound;
    private static final float WORLD_WIDTH = 1600;
    private static final float WORLD_HEIGHT = 900;
    private Stage stage;

    public FirstScreen(Main game) {
        this.game = game;
        this.sound = Gdx.audio.newSound(Gdx.files.internal("ui/abf.mp3"));
        long soundId = sound.play(0.5f);
        sound.setLooping(soundId, true);

    }
    public FirstScreen(Main game,Sound sound1) {
        this.game = game;
        sound =sound1;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("ui/void.png"));
        playButton = new Texture(Gdx.files.internal("ui/playbutton.png"));
        exitButton = new Texture(Gdx.files.internal("ui/quit3.png"));
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);
        camera.update();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        touchPos = new Vector3();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(5/255f, 28/255f, 78/255f,1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Draw background and buttons
        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);

        float scaledPlayButtonWidth = (float) playButton.getWidth() ;
        float scaledPlayButtonHeight = (float) playButton.getHeight() ;

        float playButtonX = (WORLD_WIDTH - scaledPlayButtonWidth) / 2;
        float playButtonY = (WORLD_HEIGHT - scaledPlayButtonHeight) / 2;
        batch.draw(playButton, playButtonX, playButtonY, scaledPlayButtonWidth, scaledPlayButtonHeight);

        float scaledExitButtonWidth = (float) exitButton.getWidth() / 2;
        float scaledExitButtonHeight = (float) exitButton.getHeight() / 2;
        float exitButtonX = 50;
        float exitButtonY = 40;
        batch.draw(exitButton, exitButtonX, exitButtonY, scaledExitButtonWidth, scaledExitButtonHeight);

        batch.end();

        // Handle input
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos, viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());

            // Check for play button press
            if (touchPos.x > playButtonX+40 && touchPos.x < playButtonX + scaledPlayButtonWidth -40
                && touchPos.y > playButtonY+10 && touchPos.y < playButtonY + scaledPlayButtonHeight-10) {
                game.setScreen(new MenuScreen(game,sound));
            }

            // Check for quit button press (adjust Y position for letterboxing)
            if (touchPos.x > 50 && touchPos.x < 150
                && touchPos.y > 40 && touchPos.y < 140 ) {
                Gdx.app.exit();
            }

        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {dispose();}

    @Override
    public void dispose() {
        playButton.dispose();
        exitButton.dispose();
        background.dispose();
        batch.dispose();
    }
}
