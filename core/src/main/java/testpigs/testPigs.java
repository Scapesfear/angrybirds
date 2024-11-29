package testpigs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.BodyEditorLoader;
import io.github.angry_birds.CustomWorld;

public class testPigs {
    private Body dynamicFallingBody;
    private Sprite pigTexture;
    private float x;
    private float y;
    private float angle;  // Angle for rotation in degrees
    private final float PIXELS_TO_METERS = 50f;
    private SpriteBatch batch;
    private CustomWorld world;
    public int Hp;
    private ShapeRenderer shapeRenderer;
    public boolean alive;
    private Vector2 origin;
    private String name;

    public testPigs(String imagePath, CustomWorld world, ShapeRenderer shapeRenderer, SpriteBatch batch, float x, float y, float angle, String name,int Hp) {
        //pigTexture = new Sprite(new Texture(imagePath));
        this.world = world;
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.Hp = Hp;
        this.name = name;
        this.alive = true;
        createDynamicFallingBody();
    }

    public void render(SpriteBatch batch) {
        if (dynamicFallingBody != null && !world.getBodiesToDestroy().contains(dynamicFallingBody)) {
            Vector2 bottlePos = dynamicFallingBody.getPosition().sub(origin);
            pigTexture.setPosition(bottlePos.x*PIXELS_TO_METERS, bottlePos.y*PIXELS_TO_METERS);
            pigTexture.setOriginCenter();
            pigTexture.setSize(45, 45);
            pigTexture.setOrigin(origin.x*PIXELS_TO_METERS, origin.y*PIXELS_TO_METERS);
            pigTexture.setRotation(dynamicFallingBody.getAngle() * MathUtils.radiansToDegrees);
            pigTexture.draw(batch);
            isinboundary();
        }
    }

    public Body createDynamicFallingBody() {
//        BodyEditorLoader loader;
//       // loader = new BodyEditorLoader(Gdx.files.internal("data/pigs.json"));
//        BodyDef bd = new BodyDef();
//        bd.type = BodyDef.BodyType.DynamicBody;
//        bd.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
//        Body body = world.createBody(bd);
//        FixtureDef fd = new FixtureDef();
//        fd.density = 1f;
//        fd.friction = 0.5f;
//        fd.restitution = 0.2f;
//        loader.attachFixture(body,name, fd, 0.9f);
//        this.origin = loader.getOrigin(name, 0.9f);
//        body.setAngularDamping(4f);
//        body.setUserData(this);
//        this.dynamicFallingBody = body;
//        return body;
        return null;
    }

    public void dispose() {
        pigTexture.getTexture().dispose();
    }

    public float getX() {
        return dynamicFallingBody.getPosition().x * PIXELS_TO_METERS ;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return dynamicFallingBody.getPosition().y * PIXELS_TO_METERS ;
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

    public void isinboundary() {
        if (x >=1610 || x <=-10|| y<=-10||Hp<=0) {
            //world.destroyBody(dynamicFallingBody);
            alive=false;
            //dynamicFallingBody=null;
        }
        else{alive=true;}
    }
}
//    public boolean isAlive() {
//        return alive;
//    }
