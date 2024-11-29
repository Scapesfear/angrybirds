package io.github.angry_birds.Block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.CustomWorld;

public class    Stonehollow extends Block {

    public Stonehollow(float x, float y, CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, float angle, int health) {
        super(x, y,"ui/hollowstone.png", world, batch, shapeRenderer,health,1.8f,50,50,angle,5);
        super.createRectangulardynamicBody();
    }
}
