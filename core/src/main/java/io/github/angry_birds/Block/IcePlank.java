package io.github.angry_birds.Block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.CustomWorld;

public class IcePlank extends Block {

    public IcePlank(float x, float y, CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch,float angle) {
        super(x, y,"ui/glassplank.png", world, batch, shapeRenderer,2,1f,170,23,angle);
        super.createRectangulardynamicBody();
    }
}
