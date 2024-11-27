package com.mygdx;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.angry_birds.Bird.Bird;
import io.github.angry_birds.Block.Block;
import io.github.angry_birds.Block.Ice;
import io.github.angry_birds.Block.Stone;
import io.github.angry_birds.Block.Wood;
import io.github.angry_birds.Catapult;
import io.github.angry_birds.CustomWorld;
import io.github.angry_birds.FileManager;
import io.github.angry_birds.Pig.Pig;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static io.github.angry_birds.Catapult.drawThickLine;
import static io.github.angry_birds.Catapult.isinregion;

public class    MyGame implements Screen {
    private int level;
    private final Main game;
    private Body staticCircleBody;
    private Body staticCircleBody2;
    private Body bird=null;
    private float gravity = -9.8f;
    private final Texture Background=new Texture(Gdx.files.internal("ui/screen.png"));
    private final Texture levelbgsand=new Texture("ui/menu_background.png");
    private final Texture menubutton = new Texture("ui/pause.png");
    private final SpriteBatch batch=new SpriteBatch();
    private OrthographicCamera camera;
    private Viewport viewport;
    //private final Sound sound;
    private final Vector3 touchPos=new Vector3();
    private Stage stage;
    private Window window;
    private Window windowwin;
    private Window windowlose;
    private final Catapult catapult=new Catapult("ui/catapult.png", 400, 400);
    private Stack<Bird> birds;
    private int bird_idx=0;
    private boolean isDragging = false;
    private boolean isMouseHeld = false;
    private boolean launchactivated = false;
    private boolean launchrender = false;
    private List<Block> blocks;
    private List<Pig> pigs;
    private boolean isPaused = false;
    private final CustomWorld world= new CustomWorld(new Vector2(0, -9.8f), true);
    private ShapeRenderer shapeRenderer;
    private final float PIXELS_TO_METERS = 50f;
    private final Texture circleTexture = new Texture(Gdx.files.internal("ui/planet.png"));
    private Texture newball=(new Texture(Gdx.files.internal("ui/wood.png")));
    private boolean freeze=false;
    private Body staticCircleBody3;
    private Body staticCircleBody4;
    private Body staticCircleBody5;
    private Body staticCircleBody6;
    private Box2DDebugRenderer debugRenderer;

    public MyGame(Main game) {
////        Sound sound1;
      this.game = game;
//        sound1 = asound;
//        sound1.stop();
//        sound1.dispose();
//        sound1 =Gdx.audio.newSound(Gdx.files.internal("ui/space1.mp3"));
//        sound = sound1;
//        touchPos = new Vector3();
        Box2D.init();
    }

    @Override
    public void show() {
        Texture mainbutton = new Texture("ui/menuescreen.png");
        Texture reload = new Texture("ui/restart.png");
        Texture play = new Texture("ui/newball.png");

        birds = new Stack<>();
        birds = FileManager.getInstance().loadBirds(world, shapeRenderer, batch, catapult, 1);
        blocks = new ArrayList<>();
        blocks.add(new Wood(1177, 500, world, shapeRenderer, batch,0));
        blocks.add(new Ice(1177, 600, world, shapeRenderer, batch,45));
        blocks.add(new Stone(1177, 700, world, shapeRenderer, batch,45));

        //pigs = new ArrayList<>();
        //pigs = FileManager.getInstance().loadPigs(world, shapeRenderer, batch, 1);

        camera = new OrthographicCamera();
        //viewport = new FitViewport(1600, 900, camera);
        camera.setToOrtho(false, 1600 / PIXELS_TO_METERS, 900 / PIXELS_TO_METERS);
        camera.position.set(1600 / 100f, 900 / 100f, 0);
        camera.update();
//        stage = new Stage(viewport, batch);
//        Gdx.input.setInputProcessor(stage);
//        Window.WindowStyle windowStyle = new Window.WindowStyle();
//        windowStyle.background = new TextureRegionDrawable(new TextureRegion(Background));
//        windowStyle.titleFont = new BitmapFont();
//
//        window = new Window("", windowStyle);
//        window.setSize(618, 436);
//        window.setVisible(false);
//        window.setPosition((1600 - 618) / 2f, (900 - 436) / 2f);
//
//        ImageButton.ImageButtonStyle resumeButtonStyle = new ImageButton.ImageButtonStyle();
//        resumeButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(mainbutton));
//        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
//        playButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(play));
//        ImageButton.ImageButtonStyle quitButtonStyle = new ImageButton.ImageButtonStyle();
//        quitButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(reload));
//        ImageButton resumeButton = new ImageButton(resumeButtonStyle);
//        ImageButton quitButton = new ImageButton(quitButtonStyle);
//        ImageButton playbutton = new ImageButton(playButtonStyle);
//        window.add(resumeButton).padTop(200).padLeft(20).size(reload.getWidth(), reload.getHeight());
//        window.add(quitButton).padTop(200).padLeft(20);
//        window.add(playbutton).padTop(200).padLeft(20).size(reload.getWidth(), reload.getHeight());
//
//        resumeButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new MenuScreen(game));
//            }
//        });
//
//        quitButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new levelbg(game,level));
//            }
//        });
//
//        playbutton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                window.setVisible(false);
//                Gdx.input.setInputProcessor(null);
//            }
//        });
//
//        stage.addActor(window);
//
//        Texture win = new Texture(Gdx.files.internal("ui/LevelCleared.png"));
//        Window.WindowStyle windowStylewin = new Window.WindowStyle();
//        windowStylewin.background = new TextureRegionDrawable(new TextureRegion(win));
//        windowStylewin.titleFont = new BitmapFont();
//
//        windowwin = new Window("", windowStylewin);
//        windowwin.setSize(500, 900);
//        windowwin.setVisible(false);
//        windowwin.setPosition((1600 - 500) / 2f, (0) / 2f);
//
//        ImageButton resumeButton1 = new ImageButton(resumeButtonStyle);
//        ImageButton quitButton1 = new ImageButton(quitButtonStyle);
//        ImageButton playbutton1 = new ImageButton(playButtonStyle);
//
//        resumeButton1.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new MenuScreen(game));
//            }
//        });
//
//        quitButton1.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new levelbg(game,level));
//            }
//        });
//
//        playbutton1.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                windowwin.setVisible(false);
//                Gdx.input.setInputProcessor(null);
//            }
//        });
//
//        windowwin.add(resumeButton1).padTop(500).size(reload.getWidth(), reload.getHeight());
//        windowwin.row();
//        windowwin.add(quitButton1).padTop(18);
//        windowwin.row();
//        windowwin.add(playbutton1).padTop(18).size(reload.getWidth(), reload.getHeight());
//        stage.addActor(windowwin);
//
//
//        Texture lose = new Texture(Gdx.files.internal("ui/Levelfailed.png"));
//        Window.WindowStyle windowStylelose = new Window.WindowStyle();
//        windowStylelose.background = new TextureRegionDrawable(new TextureRegion(lose));
//        windowStylelose.titleFont = new BitmapFont();
//
//        windowlose = new Window("", windowStylelose);
//        windowlose.setSize(500, 900);
//        windowlose.setVisible(false);
//        windowlose.setPosition((1600 - 500) / 2f, (0) / 2f);
//
//        ImageButton resumeButton2 = new ImageButton(resumeButtonStyle);
//        ImageButton quitButton2 = new ImageButton(quitButtonStyle);
//        ImageButton playbutton2 = new ImageButton(playButtonStyle);
//
//        resumeButton2.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new MenuScreen(game));
//            }
//        });
//
//        quitButton2.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new levelbg(game,level));
//            }
//        });
//
//        playbutton2.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                windowlose.setVisible(false);
//                Gdx.input.setInputProcessor(null);
//            }
//        });
//
//        windowlose.add(resumeButton2).padTop(500).size(reload.getWidth(), reload.getHeight());
//        windowlose.row();
//        windowlose.add(quitButton2).padTop(18);
//        windowlose.row();
//        windowlose.add(playbutton2).padTop(18).size(reload.getWidth(), reload.getHeight());
//        stage.addActor(windowlose);
//        long soundId = sound.play(1);
//        sound.setLooping(soundId, true);
        shapeRenderer = new ShapeRenderer();
        staticCircleBody = planet(453f, 300f, 100f);
        staticCircleBody2 = planet(1177f, 240f, 171f);
        staticCircleBody3=createRectangularStaticBody(1073f, 365f, 208f, 93f);
        staticCircleBody4=createRectangularStaticBody(1281f, 365f, 208f, 93f);
        staticCircleBody5=createTriangularStaticBody(960, 318f,true);
        staticCircleBody6=createTriangularStaticBody(1180, 318f,false);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);

    }

    @Override
    public void render(float delta) {
        if (isPaused){
            stage.draw();
            return;
        }
        //shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
        touchPos.set(x,y,0);
        //camera.unproject(touchPos.set(x, y, 0f), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
        batch.begin();
//
//
        //batch.draw(levelbgsand, 0, 0, 1790f, viewport.getWorldHeight());
        catapult.render(batch,delta,stage,touchPos);
        //batch.draw(menubutton, 50, 900 - 40 - menubutton.getHeight(), menubutton.getWidth(), menubutton.getHeight());
        //batch.draw(circleTexture,
            //staticCircleBody.getPosition().x * PIXELS_TO_METERS - 100f,
           // staticCircleBody.getPosition().y * PIXELS_TO_METERS - 100f,
            //200, 200);
        //batch.draw(circleTexture,
            //staticCircleBody2.getPosition().x * PIXELS_TO_METERS - 171f,
           // staticCircleBody2.getPosition().y * PIXELS_TO_METERS - 171f,
           // 342, 342);

        for (Block block : blocks) {
            block.render(batch);
            block.isinboundary(blocks);
        }
//        for (Pig pig : pigs) {
//            pig.render(batch);
//        }


        if(bird!=null&&(((Bird) bird.getUserData()).isinboundary()||((Bird) bird.getUserData()).stationary())){
            world.destroyBody(bird);
            bird=null;
            freeze=false;
        }
        if (Gdx.input.justTouched()) {
            if (touchPos.x >= 50 && touchPos.x <= 150 && touchPos.y >= 900 - 40 - menubutton.getHeight() && touchPos.y <= 900 - 40) {
                window.setVisible(true);
                Gdx.input.setInputProcessor(stage);
            }
            if (isinregion(touchPos.x, touchPos.y)) {
                isMouseHeld = true;
                isDragging = true;
            }
        }
        batch.end();
        if(Gdx.input.isTouched() && isMouseHeld && isinregion(touchPos.x, touchPos.y)) {
            drawThickLine(413, 485, touchPos.x, touchPos.y, 6, shapeRenderer);

        }
        if(!isMouseHeld||(!isinregion(touchPos.x, touchPos.y)&&isMouseHeld)&&freeze){
            catapult.idle(shapeRenderer);
        }
        batch.begin();
        if((!isMouseHeld||(!isinregion(touchPos.x, touchPos.y)&&isMouseHeld))&&!birds.isEmpty()&&!freeze){
            birds.peek().idle(true,batch);
        }
        if(Gdx.input.isTouched() && isMouseHeld && isinregion(touchPos.x, touchPos.y)&&!freeze) {
            catapult.drawTrajectory(batch, touchPos,gravity);
            if (!birds.isEmpty()) {
                birds.peek().renderbeforelaunch(batch, touchPos);
            }
        }
        batch.end();
        if(Gdx.input.isTouched() && isMouseHeld && isinregion(touchPos.x, touchPos.y)) {
            drawThickLine(459, 485 , touchPos.x , touchPos.y,6 ,shapeRenderer);
        }
        batch.begin();
        if (!Gdx.input.isTouched()&&!freeze&&!isinregion(touchPos.x, touchPos.y)){isDragging=false;
            isMouseHeld=false;}
        if (!Gdx.input.isTouched()&&!freeze&&isinregion(touchPos.x, touchPos.y)&&isMouseHeld) {
            isMouseHeld = false;
            if (isDragging && !birds.isEmpty()) {
                isDragging = false;
                freeze = true;
                Bird currentBird = birds.pop();
                bird = currentBird.createDynamicFallingBody(touchPos);
                bird.setLinearVelocity(catapult.getvelocity(touchPos));
                bird.setGravityScale(0.5f);
                launchactivated = true;
            }
        }
        else if (!Gdx.input.isTouched()&&freeze&&isinregion(touchPos.x, touchPos.y)){isMouseHeld=false;}
        if (launchactivated && bird != null) {
            if (bird.getUserData() != null) {
                ((Bird) bird.getUserData()).renderafterlaunch(batch);
            }
        }
        if (birds.isEmpty()&&bird==null) {
            windowlose.setVisible(true);
            Gdx.input.setInputProcessor(stage);
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
//            windowwin.setVisible(true);
//            Gdx.input.setInputProcessor(stage);
//        }
//
//        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
//            windowlose.setVisible(true);
//            Gdx.input.setInputProcessor(stage);
//        }
        batch.draw(newball,960,318,2,2);
        batch.draw(newball,1180,318,2,2);
        batch.end();
        world.step(1/60f, 6, 2);
        System.out.println("Contact count: " + world.getWorld().getBodyCount());
        System.out.println("Contact count: " + world.getWorld().getContactCount());
        debugRenderer.render(world.getWorld(), camera.combined);

    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width, height);
        //stage.getViewport().update(width, height, true);
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
        batch.dispose();
        menubutton.dispose();
        Background.dispose();
        stage.dispose();
        shapeRenderer.dispose();
        circleTexture.dispose();
        birds.clear();
        debugRenderer.dispose();
//        for (Body body : world.getBodiesToDestroy()) {
//            world.destroyBody(body);
//        }
//        world.getBodiesToDestroy().clear();
    }

    public Body planet(float x, float y, float radius) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        Body body = world.createBody(bodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius / PIXELS_TO_METERS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.3f;
        body.createFixture(fixtureDef);
        circleShape.dispose();
        return body;
    }
    private Body createRectangularStaticBody(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        Body body = world.createBody(bodyDef);
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width / (2 * PIXELS_TO_METERS), height / (2 * PIXELS_TO_METERS));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;
        body.createFixture(fixtureDef);
        rectangle.dispose();
        return body;
    }
    private Body createTriangularStaticBody(float x, float y, boolean bool) {
        BodyEditorLoader loader;
        if(bool) {
            loader = new BodyEditorLoader(Gdx.files.internal("data/load1.json"));
        }
        else{ loader = new BodyEditorLoader(Gdx.files.internal("data/load2.json"));
        }
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.KinematicBody;
        bd.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        Body body = world.createBody(bd);
// 2. Create a FixtureDef, as usual.
        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;
// 3. Create a Body, as usual.
            loader.attachFixture(body, "Name", fd, 4.3f);
        return body;
    }






    public int countStaticBodies(World world) {
        int staticBodyCount = 0;
        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);
        // Iterate through all the bodies in the world
        for (Body body : bodies) {
            // Check if the body's type is static
            if (body.getType() == BodyDef.BodyType.StaticBody) {
                staticBodyCount++;
            }
        }
        return staticBodyCount;
    }

}

