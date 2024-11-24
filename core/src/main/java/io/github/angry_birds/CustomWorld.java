package io.github.angry_birds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;

public class CustomWorld {
    private World world;
    private List<Body> bodiesToDestroy;

    public CustomWorld(Vector2 gravity, boolean doSleep) {
        world = new World(gravity, doSleep);
        bodiesToDestroy = new ArrayList<>();
    }

    public List<Body> getBodiesToDestroy() {
        return bodiesToDestroy;
    }

    public World getWorld() {
        return world;
    }

    public void step(float timeStep, int velocityIterations, int positionIterations) {
        world.step(timeStep, velocityIterations, positionIterations);
    }

    public void destroyBody(Body body) {
        world.destroyBody(body);
    }

    public Body createBody(BodyDef bodyDef) {
        return world.createBody(bodyDef);
    }

    public void dispose() {
        world.dispose();
    }
}
