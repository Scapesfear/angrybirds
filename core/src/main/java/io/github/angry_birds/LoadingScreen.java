package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LoadingScreen implements Screen {

    private final Main game;               // Reference to the main game class
    private Texture background;            // To hold the background image
    private SpriteBatch batch;             // To handle rendering
    private OrthographicCamera camera;     // Camera to handle the viewport
    private Sound sound;
    private long soundId;

    private float elapsedTime;             // Track time to control loading screen duration
    private static final float LOADING_DURATION = 5.0f; // Loading duration of 5 seconds

    private ShapeRenderer shapeRenderer;   // To draw the loading bar

    public LoadingScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Initialize the SpriteBatch, background, and camera
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("ui/loading.jpg")); // Add your background image here
        sound = Gdx.audio.newSound(Gdx.files.internal("ui/abf.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Set camera size to screen size
        camera.update();

        // Initialize the ShapeRenderer for the loading bar
        shapeRenderer = new ShapeRenderer();

        // Play sound only once when the screen is shown
        soundId = sound.play(0.75f); // Start playing at 75% volume
        sound.setLooping(soundId, true); // Set looping

        elapsedTime = 0f; // Reset elapsed time when the screen is shown
    }


    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1); // Black background
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Update the camera
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Calculate the correct scaling factor to maintain the aspect ratio
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float imageWidth = background.getWidth();
        float imageHeight = background.getHeight();

        // Calculate the aspect ratios
        float screenAspectRatio = screenWidth / screenHeight;
        float imageAspectRatio = imageWidth / imageHeight;

        // Variables to hold the final width and height
        float finalWidth, finalHeight;

        // Determine whether to scale based on width or height to avoid gaps
        if (screenAspectRatio > imageAspectRatio) {
            // Screen is wider than the image, scale by height
            finalHeight = screenHeight;
            finalWidth = imageWidth * (screenHeight / imageHeight);
        } else {
            // Screen is taller than the image, scale by width
            finalWidth = screenWidth;
            finalHeight = imageHeight * (screenWidth / imageWidth);
        }

        // Center the image after scaling
        float xPosition = (screenWidth - finalWidth) / 2;
        float yPosition = (screenHeight - finalHeight) / 2;

        // Begin drawing with the batch
        batch.begin();
        // Draw the scaled and centered background
        batch.draw(background, xPosition, yPosition, finalWidth, finalHeight);
        batch.end();

        // Update the elapsed time
        elapsedTime += delta;

        // Calculate loading progress (from 0.0 to 1.0 based on elapsed time)
        float progress = Math.min(elapsedTime / LOADING_DURATION, 1.0f);

        // Draw the loading bar (progress bar)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // First draw the black background bar
        shapeRenderer.setColor(0, 0, 0, 1);  // Black color for background bar

        // Calculate loading bar dimensions and position
        float barWidth = screenWidth * 0.4f; // Loading bar takes up 40% of screen width
        float barHeight = 30;                // Height of the loading bar
        float barX = (screenWidth - barWidth) / 2;  // Center the loading bar horizontally
        float barY = (screenHeight * 0.1f) - 10;    // Position the loading bar near the bottom

        // Draw the black background bar first (empty bar)
        shapeRenderer.rect(barX, barY, barWidth, barHeight);

        // Now draw the yellow progress bar on top of the black background
        shapeRenderer.setColor(1, 1, 0, 1);  // Yellow color for loading bar
        shapeRenderer.rect(barX, barY, barWidth * progress, barHeight);

        shapeRenderer.end();

        // After the loading duration, transition to the FirstScreen
        if (elapsedTime >= LOADING_DURATION) {
            sound.stop(soundId);  // Stop the sound before transitioning
            game.setScreen(new FirstScreen(game));  // Transition to the FirstScreen
        }
    }


    @Override
    public void resize(int width, int height) {
        // Update the camera when the screen is resized
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        sound.dispose();       // Dispose of the sound
        background.dispose();  // Dispose of the background texture
        batch.dispose();       // Dispose of the sprite batch
        shapeRenderer.dispose(); // Dispose of the ShapeRenderer
    }
}
