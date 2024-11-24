package io.github.angry_birds.Pig;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.CustomWorld;

public class HektorPorko extends Pig {
    public HektorPorko( CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, float x, float y, float angle) {
        super("ui/hektor.png", world, shapeRenderer, batch, x, y, angle);
    }
}
