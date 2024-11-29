package io.github.angry_birds.Pig;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.CustomWorld;

public class KingPig extends Pig {
    public KingPig( CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, float x, float y, float angle,int HP) {
        super("ui/king-pig.png", world, shapeRenderer, batch, x, y, angle, "kingpig",HP);
    }
}
