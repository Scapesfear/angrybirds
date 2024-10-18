package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen implements Screen {

    private final Main game;               // Reference to the main game class
    private Texture playButton;            // To hold the texture for the play button
    private Texture background;            // To hold the background image
    private SpriteBatch batch;             // To handle rendering
    private OrthographicCamera camera;     // Camera to handle the viewport
    private Sound sound;
    private long soundId;

    public LoadingScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Initialize the SpriteBatch, background, and camera
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("ui/loading.png")); // Add your background image here
        playButton = new Texture(Gdx.files.internal("ui/play_button.png")); // Adjust path as needed
        sound = Gdx.audio.newSound(Gdx.files.internal("ui/abf.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Set camera size to screen size
        camera.update();

        // Play sound only once when the screen is shown
        soundId = sound.play(0.75f); // Start playing at 75% volume
        sound.setLooping(soundId, true); // Set looping
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
    }
}
