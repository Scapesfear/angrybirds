package io.github.angry_birds.Bird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import io.github.angry_birds.CustomWorld;

public class Chuck extends Bird {
    public Chuck(CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch) {
        super("ui/chuck.png", world, shapeRenderer, batch);
    }
}
