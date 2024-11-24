package io.github.angry_birds.Bird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import io.github.angry_birds.CustomWorld;

public class Bomb extends Bird {
    public Bomb(CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch) {
        super("ui/bomb.png", world, shapeRenderer, batch);
    }
}
