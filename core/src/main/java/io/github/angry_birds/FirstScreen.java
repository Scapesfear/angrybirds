package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    private Texture playButton; // To hold the texture for the play button
    private SpriteBatch batch;  // To handle rendering
    private Texture background; // To hold the texture for the background

    // Store original width and height for resizing
    private float originalWidth;
    private float originalHeight;

    @Override
    public void show() {
        // Initialize the SpriteBatch
        batch = new SpriteBatch();
        // Load the play button texture
        background = new Texture(Gdx.files.internal("ui/play_screen_background.jpg")); // Adjust path as needed
        playButton = new Texture(Gdx.files.internal("ui/play_button.png")); // Adjust path as needed

        // Store the original dimensions
        originalWidth = playButton.getWidth();
        originalHeight = playButton.getHeight();
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1); // White background
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Calculate new dimensions
        float newWidth = Gdx.graphics.getWidth() / 5; // 1/10 of the width
        float newHeight = (newWidth / originalWidth) * originalHeight; // Maintain aspect ratio

        // Begin drawing with the batch
        batch.begin();
        // Draw the play button at the center of the screen with the new size
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(playButton,
            (Gdx.graphics.getWidth() - newWidth) / 2,
            (Gdx.graphics.getHeight() - newHeight) / 2,
            newWidth, // New width for the play button
            newHeight // New height for the play button
        );
        // End drawing
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
        playButton.dispose(); // Dispose of the play button texture
        batch.dispose();      // Dispose of the sprite batch
    }
}
