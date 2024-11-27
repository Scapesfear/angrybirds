package io.github.angry_birds.Block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.CustomWorld;

public class Woodplank extends Block {

    public Woodplank(float x, float y, CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch,float angle) {
        super(x, y,"ui/wood.png", world, batch, shapeRenderer,1,1.5f,170,23, angle);
        super.createRectangulardynamicBody();
    }
}
