package io.github.angry_birds.Block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.CustomWorld;

public class Stone extends Block {

    public Stone(float x, float y, CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch,float angle) {
        super(x, y,"ui/stone.png", world, batch, shapeRenderer,4,2,50,50,angle);
        super.createRectangulardynamicBody();
    }
}
