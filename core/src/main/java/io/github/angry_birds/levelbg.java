package io.github.angry_birds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
import com.mygdx.BodyEditorLoader;
import io.github.angry_birds.Bird.Bird;
import io.github.angry_birds.Block.*;
import io.github.angry_birds.Pig.Pig;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static io.github.angry_birds.Catapult.drawThickLine;
import static io.github.angry_birds.Catapult.isinregion;

public class levelbg implements Screen {
    private final int level;
    private final Main game;
    private Body staticCircleBody;
    private Body staticCircleBody2;
    private Body bird=null;
    private final float gravity = -9.8f;
    private final Texture Background=new Texture(Gdx.files.internal("ui/screen.png"));
    private final Texture levelbgsand=new Texture("ui/menu_background.png");
    private final Texture menubutton = new Texture("ui/pause.png");
    private final Texture PauseBackground =new Texture(Gdx.files.internal("ui/screen.png"));
    private final Texture Pausebutton = new Texture("ui/pause.png");
    private final SpriteBatch batch=new SpriteBatch();
    private OrthographicCamera camera;
    private Viewport viewport;
    private final Sound sound;
    private final Vector3 touchPos;
    private Stage stage;
    private Window window;
    private Window windowwin;
    private Window windowlose;
    private final Catapult catapult=new Catapult("ui/catapult.png", 400, 400);
    public Stack<Bird> birds;
    private final int bird_idx=0;
    private boolean isDragging = false;
    private boolean isMouseHeld = false;
    private boolean launchactivated = false;
    private final boolean launchrender = false;
    private List<Block> blocks;
    public List<Pig> pigs;
    private final boolean isPaused = false;
    private final CustomWorld world= new CustomWorld(new Vector2(0, -9.8f), true);
    private ShapeRenderer shapeRenderer;
    private final float PIXELS_TO_METERS = 50f;
    private final Texture circleTexture = new Texture(Gdx.files.internal("ui/planet.png"));
    private final Texture newball=(new Texture(Gdx.files.internal("ui/wood.png")));
    public boolean freeze=false;
    private Body staticCircleBody5;
    private Body staticCircleBody6;
    //private Box2DDebugRenderer debugRenderer;
    private final Texture land1= new Texture("ui/land1.png");
    private final Texture land2= new Texture("ui/land2.png");
    private boolean reset ;
    private final Texture mainmenubutton= new Texture("ui/menuescreen.png");;
    private final Texture reload =new Texture("ui/restart.png");
    private final Texture play= new Texture("ui/play_.png");
    private GameContactListener listener =new GameContactListener(this);
    public levelbg(Main game,int level, boolean reset, Sound asound) {

        Sound sound1;
        this.game = game;
        sound1 = asound;
        sound1.stop();
        sound1.dispose();
        sound1 =Gdx.audio.newSound(Gdx.files.internal("ui/space1.mp3"));
        sound = sound1;
        touchPos = new Vector3();
        this.level=level;
        this.reset=reset;
        Box2D.init();
    }

    public levelbg(Main game, int level, boolean reset) {
        this.game = game;
        this.level = level;
        sound = Gdx.audio.newSound(Gdx.files.internal("ui/space1.mp3"));
        touchPos = new Vector3();
        Box2D.init();
        this.reset = reset;
    }

    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        viewport = new FitViewport(1600, 900, camera);
        camera.setToOrtho(false, 1600 / PIXELS_TO_METERS, 900 / PIXELS_TO_METERS);
        camera.position.set(1600 / 100f, 900 / 100f, 0);
        camera.update();
        world.getWorld().setContactListener(listener);
        viewport = new FitViewport(1600, 900, camera);

        long soundId = sound.play(1);
        sound.setLooping(soundId, true);

        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        staticCircleBody = planet(453f, 300f, 100f);
        staticCircleBody2 = planet(1177f, 240f, 171f);
        staticCircleBody5=createTriangularStaticBody(960, 318f,true);
        staticCircleBody6=createTriangularStaticBody(1180, 318f,false);

        birds = new Stack<>();
        birds = FileManager.getInstance().loadBirds(world, shapeRenderer, batch, catapult, level,reset);

        blocks = new ArrayList<>();
         blocks = FileManager.getInstance().loadBlocks(world, shapeRenderer, batch, level,reset);

        pigs = new ArrayList<>();
        pigs = FileManager.getInstance().loadPigs(world, shapeRenderer, batch, level,reset);

        createPauseWindow();

        createWinWindow();

        createLoseWindow();

    }

    @Override
    public void render(float delta) {
        int cnt=0;
        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
        camera.unproject(touchPos.set(x, y, 0f), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(levelbgsand, 0, 0, 1790f, viewport.getWorldHeight());
        catapult.render(batch,delta,stage,touchPos);
        batch.draw(Pausebutton, 50, 900 - 40 - Pausebutton.getHeight(), Pausebutton.getWidth(), Pausebutton.getHeight());
        batch.draw(circleTexture,
            staticCircleBody.getPosition().x * PIXELS_TO_METERS - 100f,
            staticCircleBody.getPosition().y * PIXELS_TO_METERS - 100f,
            200, 200);

        batch.draw(land1,staticCircleBody5.getPosition().x*PIXELS_TO_METERS,staticCircleBody5.getPosition().y*PIXELS_TO_METERS,220,93);
        batch.draw(land2,staticCircleBody6.getPosition().x*PIXELS_TO_METERS,staticCircleBody6.getPosition().y*PIXELS_TO_METERS,220,93);
        batch.draw(circleTexture,
            staticCircleBody2.getPosition().x * PIXELS_TO_METERS - 171f,
            staticCircleBody2.getPosition().y * PIXELS_TO_METERS - 171f,
            342, 342);
        for (Block block : blocks) {
            block.render(batch);
        }

        for (Pig pig : pigs) {
            if(pig.alive){
            pig.render(batch);
        }else{cnt++;}
        }


        if(bird!=null&&(((Bird) bird.getUserData()).isinboundary()||((Bird) bird.getUserData()).stationary())){
            world.destroyBody(bird);
            bird=null;
            freeze=false;
            listener.reset();
        }
        if (Gdx.input.justTouched()) {
            if (touchPos.x >= 50 && touchPos.x <= 150 && touchPos.y >= 900 - 40 - menubutton.getHeight() && touchPos.y <= 900 - 40&& !freeze) {
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
        if (birds.isEmpty()&&bird==null&&cnt!=pigs.size()) {
            windowlose.setVisible(true);
            Gdx.input.setInputProcessor(stage);
        }else if (cnt==pigs.size()){
            windowwin.setVisible(true);
            Gdx.input.setInputProcessor(stage);
        }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            windowwin.setVisible(true);
            Gdx.input.setInputProcessor(stage);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            windowlose.setVisible(true);
            Gdx.input.setInputProcessor(stage);
        }

        if(bird!=null){

        }
        batch.end();
        stage.act(delta);
        stage.draw();
        world.step(1/60f, 6, 2);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {dispose();}

    @Override
    public void dispose() {
        Background.dispose();
        batch.dispose();
        sound.dispose();
        Pausebutton.dispose();
        PauseBackground.dispose();
        stage.dispose();
        shapeRenderer.dispose();
        circleTexture.dispose();
        birds.clear();
        world.dispose();
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
        body.setUserData(this);
        circleShape.dispose();
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
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS);
        Body body = world.createBody(bd);
        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.3f;
        fd.restitution = 0.3f;
        loader.attachFixture(body, "Name", fd, 4.3f);
        body.setUserData(this);
        return body;
    }

    private void createPauseWindow (){
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = new TextureRegionDrawable(new TextureRegion(PauseBackground));
        windowStyle.titleFont = new BitmapFont();

        window = new Window("", windowStyle);
        window.setSize(618, 436);
        window.setVisible(false);
        window.setPosition((1600 - 618) / 2f, (900 - 436) / 2f);

        ImageButton.ImageButtonStyle menuButtonStyle = new ImageButton.ImageButtonStyle();
        menuButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(mainmenubutton));
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(play));
        ImageButton.ImageButtonStyle reloadButtonStyle = new ImageButton.ImageButtonStyle();
        reloadButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(reload));

        ImageButton menuButton = new ImageButton(menuButtonStyle);
        ImageButton relaodButton = new ImageButton(reloadButtonStyle);
        ImageButton playbutton = new ImageButton(playButtonStyle);



        window.add(menuButton).padTop(200).padLeft(20).size(reload.getWidth(), reload.getHeight());
        window.add(relaodButton).padTop(200).padLeft(20);
        window.add(playbutton).padTop(200).padLeft(20).size(reload.getWidth(), reload.getHeight());

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                FileManager.getInstance().saveBirds(birds, level, false);
                FileManager.getInstance().savePigs((ArrayList<Pig>) pigs, level, false);
                FileManager.getInstance().saveBlocks((ArrayList<Block>) blocks, level, false);
                // FileManager.getInstance().saveBlocks(blocks, level, false);
                game.setScreen(new MenuScreen(game));

            }
        });

        relaodButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new levelbg(game,level,true));
            }
        });

        playbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.setVisible(false);
                Gdx.input.setInputProcessor(null);
            }
        });

        stage.addActor(window);
    }


    private void createWinWindow(){
        Texture win = new Texture(Gdx.files.internal("ui/LevelCleared.png"));
        Window.WindowStyle windowStylewin = new Window.WindowStyle();
        windowStylewin.background = new TextureRegionDrawable(new TextureRegion(win));
        windowStylewin.titleFont = new BitmapFont();

        windowwin = new Window("", windowStylewin);
        windowwin.setSize(500, 900);
        windowwin.setVisible(false);
        windowwin.setPosition((1600 - 500) / 2f, (0) / 2f);

        ImageButton.ImageButtonStyle menuButtonStyle = new ImageButton.ImageButtonStyle();
        menuButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(mainmenubutton));
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(play));
        ImageButton.ImageButtonStyle reloadButtonStyle = new ImageButton.ImageButtonStyle();
        reloadButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(reload));

        ImageButton menuButton = new ImageButton(menuButtonStyle);
        ImageButton reloadButton = new ImageButton(reloadButtonStyle);
        ImageButton playbutton = new ImageButton(playButtonStyle);

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                FileManager.setcompletedlevel(level);
                game.setScreen(new MenuScreen(game));
            }
        });

        reloadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new levelbg(game,level,true));
            }
        });



        windowwin.add(menuButton).padTop(500).size(reload.getWidth(), reload.getHeight());
        windowwin.row();
        windowwin.add(reloadButton).padTop(18);
        windowwin.row();
        stage.addActor(windowwin);
    }


    private void createLoseWindow(){
        Texture lose = new Texture(Gdx.files.internal("ui/Levelfailed.png"));
        Window.WindowStyle windowStylelose = new Window.WindowStyle();
        windowStylelose.background = new TextureRegionDrawable(new TextureRegion(lose));
        windowStylelose.titleFont = new BitmapFont();

        windowlose = new Window("", windowStylelose);
        windowlose.setSize(500, 900);
        windowlose.setVisible(false);
        windowlose.setPosition((1600 - 500) / 2f, (0) / 2f);

        ImageButton.ImageButtonStyle menuButtonStyle = new ImageButton.ImageButtonStyle();
        menuButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(mainmenubutton));
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(play));
        ImageButton.ImageButtonStyle reloadButtonStyle = new ImageButton.ImageButtonStyle();
        reloadButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(reload));

        ImageButton menuButton = new ImageButton(menuButtonStyle);
        ImageButton reloadButton = new ImageButton(reloadButtonStyle);
        ImageButton playButton = new ImageButton(playButtonStyle);

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                FileManager.getInstance().saveBirds(birds, level, true);
                FileManager.getInstance().savePigs((ArrayList<Pig>) pigs, level, true);
                 FileManager.getInstance().saveBlocks((ArrayList<Block>) blocks, level, true);
                game.setScreen(new MenuScreen(game));
            }
        });

        reloadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new levelbg(game,level,true));
            }
        });



        windowlose.add(menuButton).padTop(500).size(reload.getWidth(), reload.getHeight());
        windowlose.row();
        windowlose.add(reloadButton).padTop(18);
        windowlose.row();
        stage.addActor(windowlose);
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
