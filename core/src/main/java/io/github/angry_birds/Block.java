package io.github.angry_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Block {
    // Attributes
    private float x;
    private float y;
    private int orientationBit;
    private Texture blockTexture;
    private float rotationAngle;

    // Constructor
    public Block(float x, float y,float rotationAngle, String texturePath) {
        this.x = x;
        this.y = y;
        this.orientationBit = orientationBit;
        this.blockTexture = new Texture(texturePath);
        this.rotationAngle = rotationAngle;
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

        float textureWidth = blockTexture.getWidth();
        float textureHeight = blockTexture.getHeight();

        batch.draw(blockTexture,
            x, y,
            textureWidth / 2, textureHeight / 2,
            textureWidth, textureHeight,
            1, 1,
            rotationAngle,
            0, 0,
            (int) textureWidth, (int) textureHeight,
            false, false);
    }


    public void dispose() {
        if (blockTexture != null) {
            blockTexture.dispose();
        }
    }


}
