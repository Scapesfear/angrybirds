
package io.github.angry_birds.Bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import io.github.angry_birds.Catapult;
import io.github.angry_birds.CustomWorld;

public class Bird {
    private Body dynamicFallingBody;
    private Sprite birdTexture;
    private float x;
    private float y;
    private float angle;  // Angle for rotation in degrees
    private final float PIXELS_TO_METERS = 50f;
    private SpriteBatch batch;
    private CustomWorld world;
    private int maxHits;
    private ShapeRenderer shapeRenderer;
    private Catapult catapult;
    private float density ;

    public Bird(String imagePath, CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch,Catapult catapult,float density) {
        birdTexture = new Sprite(new Texture(imagePath));
        this.world = world;
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;
        this.maxHits = 2;
        this.catapult = catapult;
        this.density = density;
    }


    public int getMaxHits() {
        return maxHits;
    }

    public void setMaxHits(int maxHits) {
        this.maxHits = maxHits;
    }

    public void decrementHits() {
        this.maxHits--;
        if (this.maxHits <= 0) {
            // Queue the body for destruction
            world.getBodiesToDestroy().add(dynamicFallingBody);
        }
    }

    // Render method that applies rotation
    public void renderafterlaunch(SpriteBatch batch) {
        if (dynamicFallingBody != null && !world.getBodiesToDestroy().contains(dynamicFallingBody)) {
            float posX = dynamicFallingBody.getPosition().x * PIXELS_TO_METERS;
            float posY = dynamicFallingBody.getPosition().y * PIXELS_TO_METERS;

            batch.draw(birdTexture.getTexture(),
                dynamicFallingBody.getPosition().x * PIXELS_TO_METERS - 22.5f,
                dynamicFallingBody.getPosition().y * PIXELS_TO_METERS - 22.5f,
                45f, 45f);
        }
    }

    public void renderbeforelaunch(SpriteBatch batch, Vector3 touchPos) {
        batch.draw(birdTexture.getTexture(),
            touchPos.x - 22.5f,
            touchPos.y - 22.5f,
            22.5f, 22.5f,  // Origin of rotation (center of the sprite)
            45, 45,        // Width and height
            1, 1,          // Scale
            catapult.getCatapultAngle(),  // Rotation angle from Catapult
            0, 0,          // Source X and Y
            birdTexture.getRegionWidth(), birdTexture.getRegionHeight(),  // Source width and height
            false, false); // Flip X and Y
    }
    public Body createDynamicFallingBody(Vector3 touchPos) {
        // Create the body definition
        float x = touchPos.x;
        float y = touchPos.y;
        float radius = 22.5f;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        Body body = world.createBody(bodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius / PIXELS_TO_METERS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = density;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);
        body.setAngularDamping(2f);
        circleShape.dispose();
        this.dynamicFallingBody = body;
        body.setUserData(this);
        return body;
    }

    public void dispose() {
        birdTexture.getTexture().dispose();
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getAngle() {
        return catapult.getCatapultAngle();
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }


    public void idle(boolean bool,SpriteBatch batch) {
        if(bool) {
            batch.draw(birdTexture.getTexture(),
                416,
                465,
                40, 40);
        }
    }

    public boolean isinboundary(){
        if (dynamicFallingBody.getPosition().x >=1610 || dynamicFallingBody.getPosition().x <=-10||dynamicFallingBody.getPosition().y<=-10) {
            return true;
        } else {
            return false;
        }
    }
    public boolean stationary(){
        if (dynamicFallingBody.getLinearVelocity().x == 0 && dynamicFallingBody.getLinearVelocity().y == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void renderafterdeath(){
        //make the bird disappear
        //make the body disappear
        dynamicFallingBody.setActive(false);
        birdTexture.setAlpha(0);

    }

    public boolean isAlive() {
        return maxHits > 0;
    }
}
