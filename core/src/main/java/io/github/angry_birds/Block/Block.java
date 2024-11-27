package io.github.angry_birds.Block;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import io.github.angry_birds.CustomWorld;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private static final float PIXELS_TO_METERS = 50f;
    private float x;
    private float y;
    private Sprite blockTexture;
    private float scaleX;
    private float scaleY;
    private float rotationAngle;
    private CustomWorld world;
    private Body dynamicFallingBody;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private float health;
    private float isalive;
    private float density ;
    public Block(float x, float y, String texturePath, CustomWorld world, SpriteBatch batch, ShapeRenderer shapeRenderer, float health,float density,float width, float height ,float rotationAngle) {
        this.x = x;
        this.y = y;
        this.blockTexture = new Sprite(new Texture(texturePath));
        this.world = world;
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
        this.health=health;
        this.density=density;
        this.scaleX = width;
        this.scaleY = height;
        blockTexture.setSize(scaleX, scaleY);
        blockTexture.setOriginCenter();
        this.rotationAngle=rotationAngle;
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

    public void render(SpriteBatch batch) {
        if (dynamicFallingBody != null) {
            Vector2 position = dynamicFallingBody.getPosition();
            float angle = dynamicFallingBody.getAngle();
            float x = position.x * PIXELS_TO_METERS;
            float y = position.y * PIXELS_TO_METERS;
            blockTexture.setPosition(x - blockTexture.getWidth() / 2, y - blockTexture.getHeight() / 2);
            blockTexture.setRotation(angle * MathUtils.radiansToDegrees);
            blockTexture.draw(batch);
        }
    }
    public void createRectangulardynamicBody() {
        float width=this.scaleX;
        float height=this.scaleY;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        bodyDef.angle = rotationAngle;
        Body body = world.createBody(bodyDef);
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width / (2 * PIXELS_TO_METERS), height / (2 * PIXELS_TO_METERS));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = density;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.2f;
        body.createFixture(fixtureDef);
        rectangle.dispose() ;
        this.dynamicFallingBody=body;
    }

    public void isinboundary(List<Block> blocks) {
        if (dynamicFallingBody.getPosition().x >=1610 || dynamicFallingBody.getPosition().x <=-10||dynamicFallingBody.getPosition().y<=-10) {
           // world.destroyBody(dynamicFallingBody);
        }
    }

}
