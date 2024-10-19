package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen {

    private Main game;              // Reference to the main game class
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Texture background;
    private Texture layer1;
    private Texture backbutton;
    private Texture asteroid1;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Vector3 touchPos ;

    private float asteroid1X, asteroid1Y;      // Position
    private float asteroid1SpeedX;             // Horizontal speed
    private float asteroid1Rotation;           // Rotation angle
    private float asteroid1RotationSpeed;      // Speed of rotation
    private float screenWidth, screenHeight;
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
        background = new Texture(Gdx.files.internal("ui/menu_background.png"));
        layer1 = new Texture(Gdx.files.internal("ui/layer1.png"));
        backbutton = new Texture(Gdx.files.internal("ui/back_button.png"));
        asteroid1 = new Texture(Gdx.files.internal("ui/asteroid1.png"));
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        camera.position.set(1920 / 2f, 1080 / 2f, 0);


        // Get screen dimensions
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        // Set the asteroid starting position (center of screen, horizontally)
        asteroid1X = -asteroid1.getWidth();  // Start off-screen (to the left)
        asteroid1Y = (screenHeight - asteroid1.getHeight()) / 2; // Vertically centered

        // Set the horizontal speed of the asteroid (pixels per second)
        asteroid1SpeedX = 200f;  // Adjust speed as needed

        // Initialize rotation angle and speed (degrees per second)
        asteroid1Rotation = 0f;
        asteroid1RotationSpeed = 45f;  // Spin at 45 degrees per second


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
        // put layer 1 in bottom nad scale down  it


        batch.draw(layer1, 0, 0, 400, 200);
        batch.draw(layer1, 300, 0, 400, 200);
        batch.draw(layer1, 600, 0, 400, 200);
        batch.draw(layer1, 900, 0, 400, 200);
        batch.draw(layer1, 1200, 0, 400, 200);
        batch.draw(layer1, 1500, 0, 400, 200);
        batch.draw(layer1, 1800, 0, 400, 200);
        batch.draw(layer1, 2100, 0, 400, 200);


        // draw a floating asteroid
        asteroid1X += asteroid1SpeedX * delta;   // Move to the right

        // If the asteroid goes off the right edge, reset to the left side
        if (asteroid1X > screenWidth) {
            asteroid1X = -asteroid1.getWidth();
        }

        // Update the rotation angle
        asteroid1Rotation += asteroid1RotationSpeed * delta;
        if (asteroid1Rotation > 360) asteroid1Rotation -= 360; // Keep rotation between 0-360 degrees

        batch.draw(asteroid1, asteroid1X, asteroid1Y,
            asteroid1.getWidth() / 2, asteroid1.getHeight() / 2,  // Origin at center
            asteroid1.getWidth(), asteroid1.getHeight(),          // Width and height
            1, 1,                                                 // Scale
            asteroid1Rotation,                                    // Rotation angle
            0, 0, asteroid1.getWidth(), asteroid1.getHeight(),     // Source rect
            false, false);

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

            // Check if the touch position is inside the first rectangle
            if (touchPos.x >= 300 && touchPos.x <= 600 && touchPos.y >= 200 && touchPos.y <= 900) {
                game.setScreen(new levelbg(game));
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
        background.dispose();
        layer1.dispose();
        backbutton.dispose();
        asteroid1.dispose();

    }
}
