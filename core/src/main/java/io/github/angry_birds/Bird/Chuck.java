package io.github.angry_birds.Bird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.angry_birds.Catapult;
import io.github.angry_birds.CustomWorld;

public class Chuck extends Bird {
    public Chuck(CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, Catapult catapult) {
        super("ui/chuck.png", world, shapeRenderer, batch,catapult,1 );
    }
}
