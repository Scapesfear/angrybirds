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
    private Texture asteroid3;
    private Texture world1;
    private Texture world2;
    private Texture world3;// New asteroid
    private final BitmapFont font;
    private OrthographicCamera camera;
    private Viewport viewport;
    private final Vector3 touchPos;
    private final Sound sound;
    private float asteroid1X, asteroid1Y;      // Position for asteroid1
    private float asteroid1SpeedX;             // Horizontal speed for asteroid1
    private float asteroid1Rotation;           // Rotation angle for asteroid1
    private float asteroid1RotationSpeed;      // Speed of rotation for asteroid1

    private float asteroid2X, asteroid2Y;      // Position for asteroid2
    private float asteroid2SpeedX;             // Horizontal speed for asteroid2
    private float asteroid2Rotation;           // Rotation angle for asteroid2
    private float asteroid2RotationSpeed;      // Speed of rotation for asteroid2

    private float asteroid3X, asteroid3Y;      // Position for asteroid3
    private float asteroid3SpeedX;             // Horizontal speed for asteroid3
    private float asteroid3Rotation;           // Rotation angle for asteroid3
    private float asteroid3RotationSpeed;      // Speed of rotation for asteroid3



    private float screenWidth;
    private Texture worldboundary1;

    public MenuScreen(Main game, Sound sound) {
        this.game = game;
        this.sound = sound;
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont(); // Default font
        font.getData().setScale(2); // Set font size larger for better visibility
        touchPos = new Vector3();
    }

    public MenuScreen(Main game) {
        this.game = game;
        this.sound = Gdx.audio.newSound(Gdx.files.internal("ui/abf.mp3"));
        long soundId = sound.play(0.75f);
        sound.setLooping(soundId, true);
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
        asteroid2 = new Texture(Gdx.files.internal("ui/frozepig.png"));
        asteroid3 = new Texture(Gdx.files.internal("ui/asteroid3.png"));
        world1=new Texture(Gdx.files.internal("ui/world1.png"));
        world2=new Texture(Gdx.files.internal("ui/redplanet.png"));
        world3=new Texture(Gdx.files.internal("ui/jupiter.png"));
        worldboundary1=new Texture(Gdx.files.internal("ui/grayity.png"));// Load asteroid3 texture
        camera = new OrthographicCamera();
        viewport = new FitViewport(1600, 900, camera);
        camera.position.set(1600 / 2f, 900 / 2f, 0);

        // Get screen dimensions
        screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Set asteroid starting positions
        asteroid1X = -asteroid1.getWidth();  // Start off-screen to the left
        asteroid1Y = ((screenHeight - asteroid1.getHeight()) / 2) + 100; // Vertically centered

        asteroid2X = -asteroid2.getWidth() - 100;  // Start off-screen to the left
        asteroid2Y = 700; // Vertically centered

        asteroid3X = screenWidth + 200;  // Start off-screen to the left for asteroid3
        asteroid3Y = 100; // Different vertical position for asteroid3

        // Set horizontal speed
        asteroid1SpeedX = 15f;
        asteroid2SpeedX = 10f;
        asteroid3SpeedX = 15f; // Speed for asteroid3

        // Initialize rotation angle and speed
        asteroid1Rotation = 0f;
        asteroid1RotationSpeed = 15f;  // Rotate counterclockwise

        asteroid2Rotation = 360f;
        asteroid2RotationSpeed = 5f;  // Rotate clockwise

        asteroid3Rotation = 180f;
        asteroid3RotationSpeed = 10f;  // Rotate counterclockwise but faster
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(5 / 255f, 28 / 255f, 78 / 255f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update camera
        camera.update();

        // Begin drawing
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Draw background
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.draw(world1,300f,300f,0.9f*world1.getWidth(),0.9f*world1.getHeight());
        batch.draw(world2,700f,300f,0.9f*world1.getWidth(),0.9f*world1.getHeight());
        batch.draw(world3,1100f,300f,0.9f*world1.getWidth(),0.9f*world1.getHeight());
        batch.draw(worldboundary1,273f,277f,1.1f*world1.getWidth(),1.1f*world1.getHeight());
        batch.draw(worldboundary1,673f,277f,1.1f*world1.getWidth(),1.1f*world1.getHeight());
        batch.draw(worldboundary1,1073f,277f,1.1f*world1.getWidth(),1.1f*world1.getHeight());
        // Draw layer1 at the bottom
//        for (int i = 0; i <= screenWidth; i += 350) {
//            batch.draw(layer1, i, 0, 350, 200);
//        }

        // Update and draw asteroid1 (move right, rotate counterclockwise)
        asteroid1X += asteroid1SpeedX * delta;
        if (asteroid1X > 1.5f*screenWidth) {
            asteroid1X = -asteroid1.getWidth();  // Reset position
        }

        asteroid1Rotation += asteroid1RotationSpeed * delta;
        if (asteroid1Rotation > 360) asteroid1Rotation -= 360;

        batch.draw(asteroid1, asteroid1X, asteroid1Y,
            (float) asteroid1.getWidth() / 2, (float) asteroid1.getHeight() / 2,
            asteroid1.getWidth(), asteroid1.getHeight(),
            0.75f, 0.75f, asteroid1Rotation,
            0, 0, asteroid1.getWidth(), asteroid1.getHeight(),
            false, false);

        // Update and draw asteroid2 (move right, rotate clockwise)
        asteroid2X += asteroid2SpeedX * delta;
        if (asteroid2X > screenWidth) {
            asteroid2X = -asteroid2.getWidth() - 100;
        }

        asteroid2Rotation -= asteroid2RotationSpeed * delta;  // Rotate clockwise
        if (asteroid2Rotation < 0) asteroid2Rotation += 360;

        batch.draw(asteroid2, asteroid2X, asteroid2Y,
            (float) asteroid2.getWidth() / 2, (float) asteroid2.getHeight() / 2,
            asteroid2.getWidth(), asteroid2.getHeight(),
            0.5f, 0.5f, asteroid2Rotation,
            0, 0, asteroid2.getWidth(), asteroid2.getHeight(),
            false, false);

        // Update and draw asteroid3 (move right, rotate counterclockwise)
        asteroid3X -= asteroid3SpeedX * delta;
        if (asteroid3X < -10) {
            asteroid3X = screenWidth + 200;
        }

        asteroid3Rotation += asteroid3RotationSpeed * delta;  // Rotate counterclockwise
        if (asteroid3Rotation > 360) asteroid3Rotation -= 360;

        batch.draw(asteroid3, asteroid3X, asteroid3Y,
            (float) asteroid3.getWidth() / 2, (float) asteroid3.getHeight() / 2,
            asteroid3.getWidth(), asteroid3.getHeight(),
            0.4f, 0.4f, asteroid3Rotation,
            0, 0, asteroid3.getWidth(), asteroid3.getHeight(),
            false, false);

        // Draw the back button
        float scaledExitButtonWidth = (float) backbutton.getWidth() / 2;
        float scaledExitButtonHeight = (float) backbutton.getHeight() / 2;
        batch.draw(backbutton, 50, 40, scaledExitButtonWidth, scaledExitButtonHeight);

        batch.end();

         if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();

            // Convert touch coordinates to game world
            camera.unproject(touchPos.set(x, y, 0f), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());

            // Check if back button is pressed
            if (touchPos.x >= 50 && touchPos.x <= 150 && touchPos.y >= 50 && touchPos.y <= 150) {
                game.setScreen(new FirstScreen(game,sound));
            }

            // Check if the first rectangle is touched
            if (touchPos.x >= 737 && touchPos.x <= 900 && touchPos.y >= 337 && touchPos.y <= 500) {
                game.setScreen(new levelbg(game, sound));

            }
             if (touchPos.x >= 1137 && touchPos.x <= 1300 && touchPos.y >= 337 && touchPos.y <= 500) {
                 game.setScreen(new levelbg(game, sound));

             }
             if (touchPos.x >= 337 && touchPos.x <= 500 && touchPos.y >= 337 && touchPos.y <= 500) {
                 game.setScreen(new levelbg(game, sound));

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
        asteroid3.dispose();  // Dispose asteroid3
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
        background.dispose();
        layer1.dispose();
        backbutton.dispose();
        asteroid1.dispose();
    }
}
