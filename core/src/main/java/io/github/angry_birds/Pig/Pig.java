package io.github.angry_birds.Pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import io.github.angry_birds.CustomWorld;

public class Pig {
    private Body dynamicFallingBody;
    private Sprite pigTexture;
    private float x;
    private float y;
    private float angle;  // Angle for rotation in degrees
    private final float PIXELS_TO_METERS = 50f;
    private SpriteBatch batch;
    private CustomWorld world;
    private int maxHits;
    private ShapeRenderer shapeRenderer;
    private boolean alive;

    public Pig(String imagePath, CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, float x, float y, float angle) {
        pigTexture = new Sprite(new Texture(imagePath));
        this.world = world;
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.maxHits = 2;
        createDynamicFallingBody();
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
    public void render(SpriteBatch batch) {
        if (dynamicFallingBody != null) {
            batch.draw(pigTexture.getTexture(),
                dynamicFallingBody.getPosition().x * PIXELS_TO_METERS - 22.5f,
                dynamicFallingBody.getPosition().y * PIXELS_TO_METERS - 22.5f,
                22.5f, 22.5f,  // Origin of rotation (center of the sprite)
                45f, 45f,  // Width and height
                1, 1,  // Scale
                dynamicFallingBody.getAngle() * (180f / (float)Math.PI),  // Rotation angle in degrees
                0, 0,  // Source X and Y
                pigTexture.getRegionWidth(), pigTexture.getRegionHeight(),  // Source width and height
                false, false); // Flip X and Y
        }
    }

    public Body createDynamicFallingBody() {
        float radius = 22.5f;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        Body body = world.createBody(bodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius / PIXELS_TO_METERS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1.2f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);
        body.setAngularDamping(4f);
        circleShape.dispose();
        this.dynamicFallingBody = body;
        body.setUserData(this);
        return body;
    }

    public void dispose() {
        pigTexture.getTexture().dispose();
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
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean isinboundary(){
        if (dynamicFallingBody.getPosition().x >=1610 || dynamicFallingBody.getPosition().x <=-10||dynamicFallingBody.getPosition().y<=-10) {
            return true;
        } else {
            return false;
        }
    }
//    public boolean isAlive() {
//        return alive;
//    }
}
