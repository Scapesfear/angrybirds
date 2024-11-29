
package io.github.angry_birds.Bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.BodyEditorLoader;
import io.github.angry_birds.Catapult;
import io.github.angry_birds.CustomWorld;

public class Bird {
    private Body dynamicFallingBody;
    private Sprite birdTexture;
    private float x;
    private float y;
    private String name;
    private final float PIXELS_TO_METERS = 50f;
    private SpriteBatch batch;
    private CustomWorld world;
    public int damage;
    private ShapeRenderer shapeRenderer;
    private Catapult catapult;
    private float density ;
    private Vector2 origin;

    public Bird(String imagePath, CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch,Catapult catapult,float density,String name,int damage) {
        birdTexture = new Sprite(new Texture(imagePath));
        this.world = world;
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;
        this.damage = damage;
        this.catapult = catapult;
        this.density = density;
        this.name=name;
    }



    public void renderafterlaunch(SpriteBatch batch) {
        if (dynamicFallingBody != null && !world.getBodiesToDestroy().contains(dynamicFallingBody)) {
            Vector2 bottlePos = dynamicFallingBody.getPosition().sub(origin);
            birdTexture.setPosition(bottlePos.x*PIXELS_TO_METERS, bottlePos.y*PIXELS_TO_METERS);
            birdTexture.setOriginCenter();
            if(name=="redbird"){
                birdTexture.setSize(45, 45);
            }
            else{
            birdTexture.setSize(50, 50);}
            birdTexture.setOrigin(origin.x*PIXELS_TO_METERS, origin.y*PIXELS_TO_METERS);
            birdTexture.setRotation(dynamicFallingBody.getAngle() * MathUtils.radiansToDegrees);
            birdTexture.draw(batch);
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
        BodyEditorLoader loader;
        loader = new BodyEditorLoader(Gdx.files.internal("data/bird.json"));
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(touchPos.x / PIXELS_TO_METERS, touchPos.y / PIXELS_TO_METERS);
        Body body = world.createBody(bd);
        FixtureDef fd = new FixtureDef();
        fd.density = density;
        fd.friction = 0.5f;
        fd.restitution = 0.2f;
        if(name=="redbird"){
            loader.attachFixture(body, name, fd, 0.8f);
            this.origin=loader.getOrigin(name, 0.8f);
        }
        else {
            loader.attachFixture(body, name, fd, 1f);
            this.origin = loader.getOrigin(name, 1f);
        }
        body.setAngularDamping(2f);
        body.setUserData(this);
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
        return catapult.getCatapultAngle();
    }

    public void idle(boolean bool,SpriteBatch batch) {
        if(bool) {
            batch.draw(birdTexture.getTexture(),416,465,40,40);
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
        dynamicFallingBody.setActive(false);
        birdTexture.setAlpha(0);

    }

}
