package io.github.angry_birds;

import com.badlogic.gdx.physics.box2d.*;
import io.github.angry_birds.Bird.Bird;

public class GameContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if (bodyA.getType() == BodyDef.BodyType.DynamicBody) {
            decrementHits(bodyA);
        }
        if (bodyB.getType() == BodyDef.BodyType.DynamicBody) {
            decrementHits(bodyB);
        }
    }

    private void decrementHits(Body body) {
        Object userData = body.getUserData();
        if (userData instanceof Bird) {
            ((Bird) userData).decrementHits();
        }
        // Add similar checks for other dynamic objects like Pig, Block, etc.
    }

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
}
