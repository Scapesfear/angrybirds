package io.github.angry_birds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.angry_birds.Main;

public class LoadingScreen implements Screen {

    private final Main game;
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private float elapsedTime;
    private static final float LOADING_DURATION = 10.0f;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;

    private static final float WORLD_WIDTH = 1600;
    private static final float WORLD_HEIGHT = 900;

    public LoadingScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("ui/load.png"));

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(5/255f, 28/255f, 78/255f,1.0f);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.end();

        elapsedTime += delta;
        float progress = Math.min(elapsedTime / LOADING_DURATION, 1.0f);
        progress = (float) Math.pow(progress, 0.5);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        float barWidth = WORLD_WIDTH * 0.4f;
        float barHeight = 30;
        float barX = (WORLD_WIDTH - barWidth) / 2;
        float barY = WORLD_HEIGHT * 0.1f - 10;

        shapeRenderer.setColor(0.1f, 0.1f, 0.1f, 1);
        shapeRenderer.rect(barX, barY, barWidth, barHeight);

        shapeRenderer.setColor(0, 162/255f, 232/255f, 1);
        shapeRenderer.rect(barX, barY, barWidth * progress, barHeight);

        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(barX, barY, barWidth, barHeight);
        shapeRenderer.end();

        if (elapsedTime >= LOADING_DURATION) {
            game.setScreen(new FirstScreen(game));
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
    public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
}
