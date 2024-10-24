package io.github.angry_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Pig {

    protected Texture pigTexture;
    float x;
    float y;


    // Constructor
    public Pig(String imagePath, float startX, float startY, float initialHealth) {
        pigTexture = new Texture(imagePath);
        this.x = startX;
        this.y = startY;
    }


    // Render the pig if it's alive
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(pigTexture, this.x, this.y);
        batch.end();
    }

    // Dispose of the texture when it's no longer needed
    public void dispose() {
        pigTexture.dispose();
    }


}
