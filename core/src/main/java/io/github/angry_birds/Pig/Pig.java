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
                dynamicFallingBody.getPosition().x * PIXELS_TO_METERS - 25f,
                dynamicFallingBody.getPosition().y * PIXELS_TO_METERS - 25f,
                25f, 25f,  // Origin of rotation (center of the sprite)
                50f, 50f,  // Width and height
                1, 1,  // Scale
                dynamicFallingBody.getAngle() * (180f / (float)Math.PI),  // Rotation angle in degrees
                0, 0,  // Source X and Y
                pigTexture.getRegionWidth(), pigTexture.getRegionHeight(),  // Source width and height
                false, false); // Flip X and Y
        }
    }

    private void createDynamicFallingBody() {
        // Create the body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        Body body = world.createBody(bodyDef);
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(25f / PIXELS_TO_METERS, 25f / PIXELS_TO_METERS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boxShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;
        body.createFixture(fixtureDef);
        boxShape.dispose();
        this.dynamicFallingBody = body;
        body.setUserData(this);
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
}
