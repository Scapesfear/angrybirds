package io.github.angry_birds;

import com.badlogic.gdx.physics.box2d.*;
import io.github.angry_birds.Bird.Bird;
import io.github.angry_birds.Block.Block;
import io.github.angry_birds.Pig.Pig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameContactListener implements ContactListener {
    levelbg lvl;
    private final Set<ArrayList<Body>> processedContacts;
    GameContactListener(levelbg levelbg) {
    this.lvl=levelbg;
        this.processedContacts = new HashSet<>();
    }
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        ArrayList<Body> contactPair1 = new ArrayList<>();
        contactPair1.add(bodyA);
        contactPair1.add(bodyB);
        ArrayList<Body> contactPair2 = new ArrayList<>();
        contactPair2.add(bodyA);
        contactPair2.add(bodyB);
        if (lvl.freeze) {
            if (processedContacts.contains(contactPair1)||processedContacts.contains(contactPair2)) {
                return;
            }
            processedContacts.add(contactPair1);
            if (!(bodyA.getUserData() instanceof levelbg) && !(bodyB.getUserData() instanceof levelbg)) {
                if(bodyA.getUserData() instanceof Bird && bodyB.getUserData() instanceof Block){
                    Bird bird = (Bird) bodyA.getUserData();
                    Block block = (Block) bodyB.getUserData();
                    block.health=block.health-bird.damage;
                }
                else if (bodyA.getUserData() instanceof Block && bodyB.getUserData() instanceof Bird) {
                    Bird bird = (Bird) bodyB.getUserData();
                    Block block = (Block) bodyA.getUserData();
                    block.health=block.health-bird.damage;
                }
                else if(bodyA.getUserData() instanceof Bird && bodyB.getUserData() instanceof Pig){
                    Bird bird = (Bird) bodyA.getUserData();
                    Pig pig = (Pig) bodyB.getUserData();
                    pig.Hp=pig.Hp-bird.damage;
                }
                else if (bodyA.getUserData() instanceof Pig && bodyB.getUserData() instanceof Bird) {
                    Bird bird = (Bird) bodyB.getUserData();
                    Pig pig = (Pig) bodyA.getUserData();
                    pig.Hp=pig.Hp-bird.damage;
                }
            }
            else{
                if (bodyA.getUserData() instanceof levelbg && bodyB.getUserData() instanceof Block) {
                    Block b=((Block) bodyB.getUserData());
                    b.health=b.health-2;
                }
                else if (bodyB.getUserData() instanceof levelbg && bodyA.getUserData() instanceof Block) {
                    Block a=((Block) bodyA.getUserData());
                    a.health=a.health-2;
                }
                else if (bodyA.getUserData() instanceof levelbg && bodyB.getUserData() instanceof Pig) {
                    Pig b=((Pig) bodyB.getUserData());
                    b.Hp=b.Hp-2;
                }
                else if (bodyB.getUserData() instanceof levelbg && bodyA.getUserData() instanceof Pig) {
                    Pig a=((Pig) bodyA.getUserData());
                    a.Hp=a.Hp-2;
                }
                else if (bodyA.getUserData() instanceof Block && bodyB.getUserData() instanceof Block) {
                    Block a=((Block) bodyA.getUserData());
                    Block b=((Block) bodyB.getUserData());
                    a.health=a.health-b.damage;
                    b.health=b.health-a.damage;
                }
                else if (bodyB.getUserData() instanceof Block && bodyA.getUserData() instanceof Pig) {
                    Pig A=((Pig) bodyA.getUserData());
                    Block B=((Block) bodyB.getUserData());
                    A.Hp=A.Hp-B.damage;
                }

                else if (bodyB.getUserData() instanceof Pig&& bodyA.getUserData() instanceof Block) {
                    Pig b=((Pig) bodyB.getUserData());
                    Block a=((Block) bodyA.getUserData());
                    b.Hp=b.Hp-a.damage;
                }
            }
        }
    }
    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

    public void reset() {
        processedContacts.clear();
    }
}
