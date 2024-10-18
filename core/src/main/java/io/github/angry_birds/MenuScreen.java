package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.w3c.dom.Text;

public class MenuScreen implements Screen {

    private Main game;              // Reference to the main game class
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Texture background;
    private Texture backbutton;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Vector3 touchPos ;
    public MenuScreen(Main game) {
        this.game = game;
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont(); // Default font
        font.getData().setScale(2); // Set font size larger for better visibility
        touchPos = new Vector3();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("ui/p_screen_background.jpg"));
        backbutton = new Texture(Gdx.files.internal("ui/back_button.png"));
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        camera.position.set(1920 / 2f, 1080 / 2f, 0);


    }


    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);  // Optional: Set background color for blank areas
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Update camera
        camera.update();

        // Draw the background image using the batch
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // Draw the back button
        batch.draw(backbutton, 50, 50, 100, 100);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLUE);  // Set rectangle color

        // Draw the rectangle (x, y, width, height)
        shapeRenderer.rect(300,200 , 300, 700);
        shapeRenderer.rect(800,200 , 300, 700);
        shapeRenderer.rect(1300,200 , 300, 700);


        batch.end();



        shapeRenderer.end();


        if (Gdx.input.justTouched()) {
            // Get the touch position
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();

            // Convert the touch position to the game's coordinate system

            camera.unproject(touchPos.set(x, y, 0));

            // Check if the touch position is inside the back button
            if (touchPos.x >= 50 && touchPos.x <= 150 && touchPos.y >= 50 && touchPos.y <= 150) {
                game.setScreen(new FirstScreen(game));
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
