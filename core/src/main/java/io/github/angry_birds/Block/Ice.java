package io.github.angry_birds.Block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.CustomWorld;

public class Ice extends Block {

    public Ice(float x, float y, CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch,float angle) {
        super(x, y,"ui/ice.png", world, batch, shapeRenderer,1,1.5f,50,50,angle);
        super.createRectangulardynamicBody();
    }
}
