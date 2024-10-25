package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class pausemenue implements Screen {
    private final Main game;
    private Texture background;
    private Texture backbutton;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private Vector3 touchPos;
    private static final float WORLD_WIDTH = 1600;
    private static final float WORLD_HEIGHT = 900;
    private final levelbg alevelbg;

    public pausemenue(Main game,levelbg levelbg) {
        this.game = game;
        alevelbg=levelbg;}

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("ui/screen.png"));
        backbutton= new Texture(Gdx.files.internal("ui/backnew.png"));
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        shapeRenderer = new ShapeRenderer();
        touchPos=new Vector3();
    }

    @Override
    public void render(float delta) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        float playButtonX = (WORLD_WIDTH - background.getWidth()) / 2;
        float playButtonY = (WORLD_HEIGHT - background.getHeight()) / 2;
        batch.draw(background, playButtonX, playButtonY, background.getWidth(), background.getHeight());
        batch.draw(backbutton, playButtonX - 230 + background.getWidth()/2f, playButtonY + backbutton.getHeight()/2f, backbutton.getWidth()/2f,backbutton.getHeight()/2f);
        batch.draw(backbutton, playButtonX  + background.getWidth()/2f, playButtonY + backbutton.getHeight()/2f, backbutton.getWidth()/2f,backbutton.getHeight()/2f);
        batch.draw(backbutton, playButtonX + 230 + background.getWidth()/2f, playButtonY + backbutton.getHeight()/2f, backbutton.getWidth()/2f,backbutton.getHeight()/2f);
        batch.end();
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            System.out.println(x+"\t"+y);
            camera.unproject(touchPos.set(x, y, 0f), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
            if (touchPos.x >=50  && touchPos.x <= 150 && touchPos.y >= 50 && touchPos.y <= 150) {
                game.setScreen(alevelbg);
            }
        }
    }

    @Override
    public void resize(int width, int height) {viewport.update(width, height);}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {dispose();}

    @Override
    public void dispose() {
        background.dispose();
        batch.dispose();
        shapeRenderer.dispose();
        background.dispose();
        backbutton.dispose();
    }


}



