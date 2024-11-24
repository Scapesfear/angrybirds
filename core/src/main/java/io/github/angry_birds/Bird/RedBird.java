package io.github.angry_birds.Bird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.Catapult;
import io.github.angry_birds.CustomWorld;

public class RedBird extends Bird {
    public RedBird(CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, Catapult catapult) {
        super("ui/redbird.png", world, shapeRenderer, batch,catapult );
    }
}
