package io.github.angry_birds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Catapult extends Actor {
    private Sprite catapultTexture;
    private Sprite catapultboundary;
    private Sprite newball;
    private float x;
    private float y;

    public Catapult(String imagePath, float x, float y) {
        catapultTexture = new Sprite(new Texture(Gdx.files.internal(imagePath)));
        catapultboundary = new Sprite(new Texture(Gdx.files.internal("ui/catapultboundary.png")));
        newball = new Sprite(new Texture(Gdx.files.internal("ui/newball.png")));
        this.x = x;
        this.y = y;
    }


    public void render(SpriteBatch batch, float delta, Stage stage, Vector3 touchPos) {
        batch.draw(catapultTexture, x, y, 75, 100);
        batch.draw(catapultboundary, 326, 340, catapultboundary.getWidth(), catapultboundary.getHeight());
    }


    public void dispose() {
        catapultTexture.getTexture().dispose();
        catapultboundary.getTexture().dispose();

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

    public static boolean isinregion(float x, float y) {
        if ((x <= 436.0) && (Math.pow(x - 436, 2) + Math.pow(y - 450, 2) < Math.pow(110, 2))) {
            return true;
        } else {
            return false;
        }
    }

    public static void drawThickLine(float x1, float y1, float x2, float y2, float thickness, ShapeRenderer shapeRenderer) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        float length = (float) Math.sqrt(dx * dx + dy * dy);

        float nx = -dy / length;
        float ny = dx / length;

        float px1 = x1 + nx * thickness / 2;
        float py1 = y1 + ny * thickness / 2;
        float px2 = x1 - nx * thickness / 2;
        float py2 = y1 - ny * thickness / 2;
        float px3 = x2 - nx * thickness / 2;
        float py3 = y2 - ny * thickness / 2;
        float px4 = x2 + nx * thickness / 2;
        float py4 = y2 + ny * thickness / 2;

        // Draw the rectangle
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.3f, 0.1f, 0.1f, 1f);
        shapeRenderer.triangle(px1, py1, px2, py2, px3, py3);
        shapeRenderer.triangle(px1, py1, px3, py3, px4, py4);
        shapeRenderer.end();
    }


    public void idle(ShapeRenderer shapeRenderer) {
        drawThickLine(413, 485, 459, 485, 5, shapeRenderer);
    }
    public void drawTrajectory(SpriteBatch batch, Vector3 touchPos, float gravity) {
        Vector2 dragDistance = new Vector2(436,485).sub(new Vector2(touchPos.x, touchPos.y));
        float angle = dragDistance.angleRad();
        float power = dragDistance.len() * 1.5f;
        Vector2 launchVelocity = new Vector2(power * (float) Math.cos(angle), power * (float) Math.sin(angle));
        float t = 0.4f;
        int pointCount = 22;
        float timeStep = 0.4f;
        for (int i = 0; i < pointCount; i++) {
            float x = 422 + launchVelocity.x * t;
            float y = 475 + launchVelocity.y * t + 0.5f * gravity * t * t;
            if (y < 0) {
                break;
            }
            batch.draw(this.newball, x, y, this.newball.getWidth(), this.newball.getHeight());
            t += timeStep;
        }
    }
    public Vector2 getvelocity(Vector3 touchPos) {
        Vector2 dragDistance = new Vector2(436,485).sub(new Vector2(touchPos.x, touchPos.y));
        float angle = dragDistance.angleRad();
        float power = dragDistance.len() *0.16f;
        Vector2 launchVelocity = new Vector2(power * (float) Math.cos(angle), power * (float) Math.sin(angle));
        return launchVelocity;
    }
}
