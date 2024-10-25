package io.github.angry_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Catapult {
        private Texture catapultTexture;
        private float x;
        private float y;

        public Catapult(String imagePath, float x, float y) {
            // Load the catapult image
            catapultTexture = new Texture(imagePath);

            // Set the position of the catapult
            this.x = x;
            this.y = y;
        }


        public void render(SpriteBatch batch) {
            // Draw the catapult at its specified position
            batch.draw(catapultTexture, x, y,75,100);
        }


        public void dispose() {
            catapultTexture.dispose();
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

}
