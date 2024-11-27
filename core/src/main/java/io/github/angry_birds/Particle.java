package io.github.angry_birds;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
public class Particle implements Screen {
    private World world;
    private Body staticCircleBody;
    private Body dynamicFallingBody;
    private Texture circleTexture = new Texture(Gdx.files.internal("ui/planet.png"));
    private Texture boxTexture = new Texture(Gdx.files.internal("ui/wood.png"));
    Batch batch;
    Game game;
    private final float PIXELS_TO_METERS = 100f;

    public Particle(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.8f), true);

        // Create static circle body
        staticCircleBody = createStaticCircleBody(453.5f, 300.5f, 107.5f);

        // Create dynamic falling body
        dynamicFallingBody = createDynamicFallingBody(453.5f, 600f, 45f, 45f);
    }

    private Body createStaticCircleBody(float x, float y, float radius) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
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

        return body;
    }

    private Body createDynamicFallingBody(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        batch = new SpriteBatch();
        Body body = world.createBody(bodyDef);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(width / 2 / PIXELS_TO_METERS, height / 2 / PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boxShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

        body.createFixture(fixtureDef);
        boxShape.dispose();

        return body;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 6, 2);

        // Render code for static and dynamic bodies
        batch.begin();
        // Render static circle body
        batch.draw(circleTexture,
            staticCircleBody.getPosition().x * PIXELS_TO_METERS - 107.5f,
            staticCircleBody.getPosition().y * PIXELS_TO_METERS - 107.5f,
            215, 215);

        // Render dynamic falling body
        batch.draw(boxTexture,
            dynamicFallingBody.getPosition().x * PIXELS_TO_METERS - 22.5f,
            dynamicFallingBody.getPosition().y * PIXELS_TO_METERS - 22.5f,
            45, 45);
        batch.end();
    }
    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        circleTexture.dispose();
        boxTexture.dispose();
        batch.dispose();
    }
}
