package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Screen;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    private Main game;               // Reference to the main game class
    private Texture playButton;      // Texture for the play button
    private Texture exitButton;      // Texture for the exit button
    private Texture background;      // To hold the background image
    private SpriteBatch batch;       // To handle rendering
    private OrthographicCamera camera; // Camera to handle the viewport
    private Viewport viewport;       // Viewport to handle resizing
    private Vector3 touchPos;        // To store the touch position

    public FirstScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Initialize the SpriteBatch, background, and camera
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("ui/p_screen_background.jpg")); // Background image
        playButton = new Texture(Gdx.files.internal("ui/play_button.png")); // Play button image
        exitButton = new Texture(Gdx.files.internal("ui/exit_button.png")); // Exit button image

        // Set up the camera and viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera); // Set the viewport size
        camera.position.set(1920 / 2f, 1080 / 2f, 0); // Center the camera
        camera.update();

        // Initialize the touch position vector
        touchPos = new Vector3();
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1); // White background
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Update the camera
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Begin drawing with the batch
        batch.begin();

        // Draw the background to cover the entire screen
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // Scale the play button (e.g., make it 1/5 of its original size)
        float scaledPlayButtonWidth = playButton.getWidth() / 10;
        float scaledPlayButtonHeight = playButton.getHeight() / 10;

        // Draw the play button at the center of the screen
        float playButtonX = (viewport.getWorldWidth() - scaledPlayButtonWidth) / 2;
        float playButtonY = ((viewport.getWorldHeight() - scaledPlayButtonHeight) / 2) - 450;
        batch.draw(playButton, playButtonX, playButtonY, scaledPlayButtonWidth, scaledPlayButtonHeight);

        // Scale and position the exit button in the lower-left corner



        // Position the exit button in the bottom-left corner with some margin
        float exitButtonX = 50;  // 20 pixels from the left edge
        float exitButtonY = 50;  // 20 pixels from the bottom edge
        batch.draw(exitButton, exitButtonX, exitButtonY, 100, 100); // Draw the exit button

        batch.end();

        // Handle input (touch/click)
        if (Gdx.input.justTouched()) {
            // Get the touch position in screen space and unproject it to world space
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            // Check if the play button is touched
            if (touchPos.x > playButtonX && touchPos.x < playButtonX + scaledPlayButtonWidth
                && touchPos.y > playButtonY && touchPos.y < playButtonY + scaledPlayButtonHeight) {
                // Play button clicked, change screen to MenuScreen
                game.setScreen(new MenuScreen(game));
            }

            // Check if the exit button is touched
            if (touchPos.x > exitButtonX && touchPos.x < exitButtonX + 100
                && touchPos.y > exitButtonY && touchPos.y < exitButtonY + 100) {
                // Exit button clicked, exit the game
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport on resize
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
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here
        playButton.dispose(); // Dispose of the play button texture
        exitButton.dispose(); // Dispose of the exit button texture
        background.dispose(); // Dispose of the background texture
        batch.dispose();      // Dispose of the sprite batch
    }
}
