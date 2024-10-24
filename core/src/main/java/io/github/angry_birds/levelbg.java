package io.github.angry_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class levelbg implements Screen {
    private Main game;
    private Texture levelbgsand;
    private Texture levelbgdunes;
    private Texture purple1;
    private Texture purple2;
    private Texture purple3;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture backbutton;
    private Viewport viewport;
    private Sound sound;



    public levelbg(Main game,Sound asound) {
        this.game = game;
        sound = asound;
        sound.stop();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        levelbgsand = new Texture("ui/levelbgsand.png");
       levelbgdunes = new Texture("ui/dunes.png");
       purple1 = new Texture("ui/purple1.png");
       purple2 = new Texture("ui/purple2.png");
       purple3 = new Texture("ui/purple3.png");
       backbutton = new Texture("ui/back_button.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        batch.draw(purple1, 0, 400,400 , 300);
        batch.draw(purple1, 350, 400,400 , 300);
        batch.draw(purple1, 700, 400,400 , 300);
        batch.draw(purple1, 1050, 400,400 , 300);
        batch.draw(purple1, 1400, 400,400 , 300);
        batch.draw(purple1, 1750, 400,400 , 300);

        batch.draw(purple2, 0, 550,400 , 300);
        batch.draw(purple2, 350, 550,400 , 300);
        batch.draw(purple2, 700, 550,400 , 300);
        batch.draw(purple2, 1050, 550,400 , 300);
        batch.draw(purple2, 1400, 550,400 , 300);
        batch.draw(purple2, 1750, 550,400 , 300);

        batch.draw(purple3, 0, 750,400 , 400);
        batch.draw(purple3, 350, 750,400 , 400);
        batch.draw(purple3, 700, 750,400 , 400);
        batch.draw(purple3, 1050, 750,400 , 400);
        batch.draw(purple3, 1400, 750,400 , 400);
        batch.draw(purple3, 1750, 750,400 , 400);

        batch.draw(levelbgdunes, 0, 200,1920 , 300);
        batch.draw(levelbgsand, 0, 0,1920 , 200);
        batch.draw(levelbgsand, 0, 90,1920 , 200);
        batch.draw(backbutton, 1700, 880, 75, 75);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {dispose();}

    @Override
    public void dispose() {
        levelbgsand.dispose();
        levelbgdunes.dispose();
        purple1.dispose();
        batch.dispose();
        sound.dispose();
    }



}
