package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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

    private final Main game;              // Reference to the main game class
    private SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;
    private Texture background;
    private Texture layer1;
    private Texture backbutton;
    private Texture asteroid1;
    private Texture asteroid2;
    private final BitmapFont font;
    private OrthographicCamera camera;
    private Viewport viewport;
    private final Vector3 touchPos ;
    private final Sound sound;
    private float asteroid1X, asteroid1Y;      // Position
    private float asteroid1SpeedX;             // Horizontal speed
    private float asteroid1Rotation;           // Rotation angle
    private float asteroid1RotationSpeed;      // Speed of rotation
    private float asteroid2X, asteroid2Y;      // Position
    private float asteroid2SpeedX;             // Horizontal speed
    private float asteroid2Rotation;           // Rotation angle
    private float asteroid2RotationSpeed;
    private float screenWidth, screenHeight;
    public MenuScreen(Main game, Sound sound) {
        this.game = game;
        this.sound=sound;
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
        backbutton = new Texture(Gdx.files.internal("ui/backnew.png"));
        asteroid1 = new Texture(Gdx.files.internal("ui/asteroid1.png"));
        asteroid2=new Texture(Gdx.files.internal("ui/asteroid2.png"));
        camera = new OrthographicCamera();
        viewport = new FitViewport(1600, 900, camera);
        camera.position.set(1600 / 2f, 900 / 2f, 0);


        // Get screen dimensions
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        // Set the asteroid starting position (center of screen, horizontally)
        asteroid1X = -asteroid1.getWidth();  // Start off-screen (to the left)
        asteroid1Y = ((screenHeight - asteroid1.getHeight()) / 2)+15 ; // Vertically centered

        asteroid2X = -asteroid2.getWidth()-100;  // Start off-screen (to the left)
        asteroid2Y = 80 ; // Vertically centered

        // Set the horizontal speed of the asteroid (pixels per second)
        asteroid1SpeedX = 25f;  // Adjust speed as needed
        asteroid2SpeedX = 35f;
        // Initialize rotation angle and speed (degrees per second)
        asteroid1Rotation = 0f;
        asteroid1RotationSpeed = 15f;  // Spin at 45 degrees per second
        asteroid2Rotation = 45f;
        asteroid2RotationSpeed = 20f;

    }


    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(5/255f, 28/255f, 78/255f,1.0f);  // Optional: Set background color for blank areas
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        asteroid2X += asteroid2SpeedX * delta;
        // If the asteroid goes off the right edge, reset to the left side
        if (asteroid1X > screenWidth) {
            asteroid1X = -asteroid1.getWidth();
        }
        if (asteroid2X > screenWidth) {
            asteroid2X = -asteroid2.getWidth();
        }

        // Update the rotation angle
        asteroid1Rotation += asteroid1RotationSpeed * delta;
        if (asteroid1Rotation > 360) asteroid1Rotation -= 360; // Keep rotation between 0-360 degrees
        asteroid2Rotation += asteroid2RotationSpeed * delta;
        if (asteroid2Rotation > 360) asteroid2Rotation -= 360;

        batch.draw(asteroid1, asteroid1X, asteroid1Y,
            (float) asteroid1.getWidth() / 2, (float) asteroid1.getHeight() / 2,  // Origin at center
            asteroid1.getWidth(), asteroid1.getHeight(),          // Width and height
            1, 1,                                                 // Scale
            asteroid1Rotation,                                    // Rotation angle
            0, 0, asteroid1.getWidth(), asteroid1.getHeight(),     // Source rect
            false, false);

        batch.draw(asteroid2, asteroid2X, asteroid2Y,
            (float) asteroid2.getWidth() / 2, (float) asteroid2.getHeight() / 2,  // Origin at center
            asteroid2.getWidth(), asteroid2.getHeight(),          // Width and height
            1, 1,                                                 // Scale
            asteroid2Rotation,                                    // Rotation angle
            0, 0, asteroid2.getWidth(), asteroid2.getHeight(),     // Source rect
            false, false);

        // Draw the back button

        float scaledExitButtonWidth = (float) backbutton.getWidth() / 2;
        float scaledExitButtonHeight = (float) backbutton.getHeight() / 2;
        float exitButtonX = 50;
        float exitButtonY = 40;
        batch.draw(backbutton,exitButtonX,exitButtonY, scaledExitButtonWidth,scaledExitButtonHeight);
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

            camera.unproject(touchPos.set(x,y,0f), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());



            // Check if the touch position is inside the back button
            if (touchPos.x >= 50 && touchPos.x <= 150 && touchPos.y >= 50 && touchPos.y <= 150) {
                game.setScreen(new FirstScreen(game));
            }

            // Check if the touch position is inside the first rectangle
            if (touchPos.x >= 300 && touchPos.x <= 600 && touchPos.y >= 200 && touchPos.y <= 900) {
                game.setScreen(new levelbg(game));
                sound.stop();
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
        asteroid2.dispose();
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
        background.dispose();
        layer1.dispose();
        backbutton.dispose();
        asteroid1.dispose();
        sound.dispose();

    }
}
