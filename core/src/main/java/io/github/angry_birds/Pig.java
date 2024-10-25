package io.github.angry_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Pig {

    protected Texture pigTexture;
    float x;
    float y;
    float angle; // New attribute for angle

    // Constructor
    public Pig(String imagePath, float startX, float startY, float initialHealth, float initialAngle) {
        pigTexture = new Texture(imagePath);
        this.x = startX;
        this.y = startY;
        this.angle = initialAngle; // Initialize the angle
    }

    // Render the pig if it's alive
    public void render(SpriteBatch batch) {
        batch.draw(pigTexture, this.x, this.y, pigTexture.getWidth() / 2, pigTexture.getHeight() / 2, // Origin at the center
            pigTexture.getWidth(), pigTexture.getHeight(), // Width and height
            1, 1, // Scale
            angle, // Rotation angle
            0, 0, // Source (texture) coordinates
            pigTexture.getWidth(), pigTexture.getHeight(), // Source width and height
            false, // Flip horizontally
            false); // Flip vertically
    }

    // Dispose of the texture when it's no longer needed
    public void dispose() {
        pigTexture.dispose();
    }
}
