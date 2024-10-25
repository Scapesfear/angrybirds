package io.github.angry_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block {
    // Attributes
    private float x;
    private float y;
    private int orientationBit;
    private Texture blockTexture;
    private float scaleX;
    private float scaleY;
    private float rotationAngle;

    // Constructor
    public Block(float x, float y,float rotationAngle, String texturePath,float scaleX,float scaleY) {
        this.x = x;
        this.y = y;
        this.orientationBit = orientationBit;
        this.blockTexture = new Texture(texturePath);
        this.rotationAngle = rotationAngle;
        this.scaleX=scaleX;
        this.scaleY =scaleY;

    }

    // Getters and Setters
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

    public int getOrientationBit() {
        return orientationBit;
    }

    public void setOrientationBit(int orientationBit) {
        this.orientationBit = orientationBit;
        this.rotationAngle = (orientationBit == 1) ? 90f : 0f;
    }


    public void render(SpriteBatch batch) {



        float originX = blockTexture.getWidth() / 2f;
        float originY = blockTexture.getHeight() / 2f;

        batch.draw(
            blockTexture,   // Texture to draw
            x,              // x position
            y,              // y position
            originX,        // origin x for rotation and scaling
            originY,        // origin y for rotation and scaling
            blockTexture.getWidth(),  // Width of texture
            blockTexture.getHeight(), // Height of texture
            scaleX,         // Scaling in x direction
            scaleY,         // Scaling in y direction
            rotationAngle,  // Rotation angle
            0,              // Source x (top-left corner)
            0,              // Source y (top-left corner)
            (int) blockTexture.getWidth(),  // Source width
            (int) blockTexture.getHeight(), // Source height
            false,          // No flip horizontally
            false           // No flip vertically
        );
    }


    public void dispose() {
        if (blockTexture != null) {
            blockTexture.dispose();
        }
    }


}
