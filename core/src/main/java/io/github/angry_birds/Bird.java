package io.github.angry_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bird {
    private Texture birdTexture;
    private float x;
    private float y;
    float textureWidth ;
    float textureHeight ;
    private float angle;  // Angle for rotation in degrees

    // Constructor with angle
    public Bird(String imagePath, float x, float y, float angle,float textureHeight,float textureWidth) {
        birdTexture = new Texture(imagePath);
        this.textureHeight = textureHeight;
        this.textureWidth = textureWidth;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    // Render method that applies rotation
    public void render(SpriteBatch batch) {



        batch.draw(birdTexture,
            x, y,  // Position of the bird
            textureWidth / 2, textureHeight / 2,  // Origin point for rotation (center)
            textureWidth, textureHeight,  // Width and height of the bird
            1, 1,  // Scale (no scaling here)
            angle,  // Rotation angle
            0, 0,  // Texture region origin (top-left corner)
            (int) birdTexture.getWidth(), (int) birdTexture.getHeight(),  // Texture region size
            false, false);  // Flip flags
    }

    public void resize(int width, int height) {
        // Resize logic if needed
    }

    public void dispose() {
        birdTexture.dispose();
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }


    public void launch(int velocity, int x, int y, float angle) {

    }
}
