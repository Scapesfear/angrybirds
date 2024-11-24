package io.github.angry_birds.Pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Pig {

    protected Texture pigTexture;
    float x;
    float y;
    float textureWidth;
    float textureHeight;
    float angle; // New attribute for angle

    // Constructor
    public Pig(String imagePath, float startX, float startY, float initialAngle, float textureWidth, float textureHeight) {
        pigTexture = new Texture(imagePath);
        this.x = startX;
        this.y = startY;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.angle = initialAngle; // Initialize the angle
    }

    // Render the pig if it's alive
    public void render(SpriteBatch batch) {
        batch.draw(pigTexture, this.x, this.y, textureWidth/ 2, textureHeight/2, // Origin at the center
            textureWidth, textureHeight, // Width and height
            1, 1, // Scale
            angle, // Rotation angle
            0, 0, // Source (texture) coordinates
            (int)pigTexture.getHeight(),(int) pigTexture.getWidth(), // Source width and height
            false, // Flip horizontally
            false); // Flip vertically
    }

    // Dispose of the texture when it's no longer needed
    public void dispose() {
        pigTexture.dispose();
    }
}
