package io.github.angry_birds;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bird {
    private Texture birdTexture;

    private float x;
    private float y;

    public Bird(String imagePath, float x, float y) {
        birdTexture = new Texture(imagePath);

        this.x = x;
        this.y = y;
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(birdTexture, x, y);
        batch.end();
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

    //launch from the catapult
    public void launch (int velocity, int x, int y , float angle){


    }



}
