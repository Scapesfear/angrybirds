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
    private Texture playButton;      // To hold the texture for the play button
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
        background = new Texture(Gdx.files.internal("ui/play_screen_background.jpg")); // Add your background image here
        playButton = new Texture(Gdx.files.internal("ui/play_button.png")); // Adjust path as needed

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
        Gdx.gl.glClearColor(1, 1, 1, 1); // White background
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Update the camera
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Begin drawing with the batch
        batch.begin();

        // Draw the background to cover the entire screen
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // Scale the play button (e.g., make it 1/5 of its original size)
        float scaledWidth = playButton.getWidth() / 5;  // Adjust this to 1/5 of the original width
        float scaledHeight = playButton.getHeight() / 5;  // Adjust this to 1/5 of the original height

        // Draw the play button at the center of the screen with the scaled size
        float playButtonX = (viewport.getWorldWidth() - scaledWidth) / 2;
        float playButtonY = (viewport.getWorldHeight() - scaledHeight) / 2;
        batch.draw(playButton, playButtonX, playButtonY, scaledWidth, scaledHeight);

        batch.end();

        // Handle input (touch/click)
        if (Gdx.input.isTouched()) {
            // Get the touch position in screen space and unproject it to world space
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            // Check if the play button is touched (adjusted for scaled size)
            if (touchPos.x > playButtonX && touchPos.x < playButtonX + scaledWidth
                && touchPos.y > playButtonY && touchPos.y < playButtonY + scaledHeight) {
                // Play button clicked, change screen to MainMenuScreen
                game.setScreen(new MenuScreen(game));
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
        background.dispose(); // Dispose of the background texture
        batch.dispose();      // Dispose of the sprite batch
    }
}
