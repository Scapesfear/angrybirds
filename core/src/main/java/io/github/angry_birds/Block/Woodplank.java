package io.github.angry_birds.Block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.CustomWorld;

public class Woodplank extends Block {

    public Woodplank(float x, float y, CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, float angle, int health) {
        super(x, y,"ui/wood.png", world, batch, shapeRenderer,health,1.5f,168,22, angle,3);
        super.createRectangulardynamicBody();
    }
}
