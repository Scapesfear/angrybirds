package io.github.angry_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

public class Bird {
    private Body dynamicFallingBody;
    private Sprite birdTexture;
    private float x;
    private float y;
    private float angle;  // Angle for rotation in degrees
    private final float PIXELS_TO_METERS = 50f;
    private SpriteBatch batch;
    private World world;
    private ShapeRenderer shapeRenderer;

    public Bird(String imagePath, World world, ShapeRenderer shapeRenderer, SpriteBatch batch) {
        birdTexture = new Sprite(new Texture(imagePath));
        this.world = world;
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;
    }

    // Render method that applies rotation
    public void renderafterlaunch(SpriteBatch batch) {
        batch.draw(birdTexture,
            dynamicFallingBody.getPosition().x * PIXELS_TO_METERS - 22.5f,
            dynamicFallingBody.getPosition().y * PIXELS_TO_METERS - 22.5f,
            45, 45);
        // Flip flags
    }

    public void renderbeforelaunch(SpriteBatch batch, Vector3 touchPos) {
        batch.draw(birdTexture,
            touchPos.x -5,
            touchPos.y -15,
            45, 45);
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
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;
        body.createFixture(fixtureDef);
        circleShape.dispose();
        this.dynamicFallingBody = body;
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
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }


    public void launch(int velocity, int x, int y, float angle) {

    }
}
