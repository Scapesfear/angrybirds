package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Catapult extends Actor {
        private Sprite catapultTexture;
        private Sprite catapultboundary;
        private float x;
        private float y;

        public Catapult(String imagePath, float x, float y) {
            catapultTexture = new Sprite(new Texture(Gdx.files.internal(imagePath)));
            catapultboundary=new Sprite(new Texture(Gdx.files.internal("ui/catapultboundary.png")));
            this.x = x;
            this.y = y;
        }


        public void render(SpriteBatch batch, float delta) {
            batch.draw(catapultTexture, x, y,75,100);
            batch.draw(catapultboundary,326,340,catapultboundary.getWidth(),catapultboundary.getHeight());
        }


        public void dispose() {
            catapultTexture.getTexture().dispose();
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
