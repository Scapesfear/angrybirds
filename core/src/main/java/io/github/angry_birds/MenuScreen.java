package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Screen;

public class MenuScreen implements Screen {

    private Main game;              // Reference to the main game class
    private ShapeRenderer shapeRenderer;  // To render rectangles
    private SpriteBatch batch;           // To render text
    private BitmapFont font;             // To display numbers in the rectangles

    public MenuScreen(Main game) {
        this.game = game;
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont(); // Default font
        font.getData().setScale(2); // Set font size larger for better visibility
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for the game.
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Render 4 rectangles, each with a number
        float rectWidth = 200;
        float rectHeight = 100;

        float startX = (Gdx.graphics.getWidth() - rectWidth) / 2;  // Center rectangles horizontally
        float startY = Gdx.graphics.getHeight() - rectHeight * 2;  // Place first rectangle higher

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);

        // Draw the first rectangle
        shapeRenderer.rect(startX, startY, rectWidth, rectHeight);

        // Draw the second rectangle below
        shapeRenderer.rect(startX, startY - rectHeight - 20, rectWidth, rectHeight);

        // Draw the third rectangle below
        shapeRenderer.rect(startX, startY - 2 * (rectHeight + 20), rectWidth, rectHeight);

        // Draw the fourth rectangle below
        shapeRenderer.rect(startX, startY - 3 * (rectHeight + 20), rectWidth, rectHeight);

        shapeRenderer.end();

        // Render the numbers in the center of the rectangles
        batch.begin();
        font.setColor(Color.WHITE);

        // Draw numbers inside each rectangle
        font.draw(batch, "1", startX + rectWidth / 2 - 10, startY + rectHeight / 2 + 10);
        font.draw(batch, "2", startX + rectWidth / 2 - 10, startY - rectHeight / 2 + 10);
        font.draw(batch, "3", startX + rectWidth / 2 - 10, startY - 3 * rectHeight / 2 - 30);
        font.draw(batch, "4", startX + rectWidth / 2 - 10, startY - 5 * rectHeight / 2 - 50);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Called when the window is resized
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
        // Clean up resources
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
