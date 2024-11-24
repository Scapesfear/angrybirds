package io.github.angry_birds.Pig;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.CustomWorld;

public class AlienPig extends Pig {
    public AlienPig( CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, float x, float y, float angle) {
        super("ui/alienpig.png", world, shapeRenderer, batch, x, y, angle);
    }
}
