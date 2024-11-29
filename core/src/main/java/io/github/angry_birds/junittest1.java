package io.github.angry_birds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.junit.jupiter.api.Test;
import testbirds.testbird;
import testblocks.testblock;
import testpigs.testPigs;

public class junittest1 {
    @Test
    public void test1() {
        //testing damage done by a bird on a pig
        CustomWorld world = null;
        ShapeRenderer shapeRenderer = null;
        SpriteBatch batch = null;
        testPigs p = new testPigs("ui/king-pig.png", world, shapeRenderer, batch, 0, 0, 0, "kingpig",50);
        testbird  b = new testbird("ui/redbird.png", world, shapeRenderer, batch,null,1.7f,"redbird",15 );
        testblock bl = new testblock(0, 0,"ui/ice.png", world, batch, shapeRenderer,50,1.5f,50,50,0,3);
        testblock bl1 = new testblock(0, 0,"ui/ice.png", world, batch, shapeRenderer,50,1.5f,50,50,0,3);
        processcontacts(p,b);
        assert(p.Hp==35);
        processcontacts(p,bl);
        assert(p.Hp==32);
        processcontacts(bl,b);
        assert(bl.health==35);
        processcontacts(bl,bl1);
        assert(bl.health==32&&bl1.health==47);
        processcontacts(b,p);
        assert(p.Hp==17);
        processcontacts(b,bl);
        assert(bl.health==17);
        processcontacts(bl,p);
        assert(p.Hp==14);
        processcontacts(p,p);
        assert(p.Hp==14);
    }
    public void processcontacts(Object objA, Object objB) {
            if (objA instanceof testbird && objB instanceof testblock) {
                testbird bird = (testbird) objA;
                testblock block = (testblock) objB;
                block.health -= bird.damage;
            } else if (objA instanceof testblock && objB instanceof testbird) {
                testbird bird = (testbird) objB;
                testblock block = (testblock) objA;
                block.health -= bird.damage;
            } else if (objA instanceof testbird && objB instanceof testPigs) {
                testbird bird = (testbird) objA;
                testPigs pig = (testPigs) objB;
                pig.Hp -= bird.damage;
            } else if (objA instanceof testPigs && objB instanceof testbird) {
                testbird bird = (testbird) objB;
                testPigs pig = (testPigs) objA;
                pig.Hp -= bird.damage;
            }
            if (objA instanceof levelbg && objB instanceof testblock) {
                testblock block = (testblock) objB;
                block.health -= 2;
            } else if (objA instanceof testblock && objB instanceof levelbg) {
                testblock block = (testblock) objA;
                block.health -= 2;
            } else if (objA instanceof levelbg && objB instanceof testPigs) {
                testPigs pig = (testPigs) objB;
                pig.Hp -= 2;
            } else if (objA instanceof testPigs && objB instanceof levelbg) {
                testPigs pig = (testPigs) objA;
                pig.Hp -= 2;
            } else if (objA instanceof testblock && objB instanceof testblock) {
                testblock blockA = (testblock) objA;
                testblock blockB = (testblock) objB;
                blockA.health -= blockB.damage;
                blockB.health -= blockA.damage;
            } else if (objA instanceof testblock && objB instanceof testPigs) {
                testPigs pig = (testPigs) objB;
                testblock block = (testblock) objA;
                pig.Hp -= block.damage;
            } else if (objA instanceof testPigs && objB instanceof testblock) {
                testPigs pig = (testPigs) objA;
                testblock block = (testblock) objB;
                pig.Hp -= block.damage;
            }
    }
}


