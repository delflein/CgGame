package com.mygdx.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.stages.Menus.MainMenuStage;
import com.mygdx.game.systems.RenderSystem;
import com.mygdx.game.utils.EntityFactory;
import com.mygdx.game.utils.ModelFactory;

public class MainMenuScreen implements Screen {

    //Background
    private MyGdxGame game;
    private ModelBatch batch;
    private Camera cam;
    private Environment environment;
    private Engine engine;
    private ShapeRenderer shapeRenderer;


    //UI
    private Stage stage;
    private Skin skin;
    private Music music;

    public MainMenuScreen(final MyGdxGame game) {
        this.game = game;
        this.skin = new Skin(Gdx.files.internal("Skins/default/uiskin.json"));
        this.shapeRenderer = new ShapeRenderer();
        setBGM();
        createBackground();
        setStage(new MainMenuStage(this,game,skin));
        music.play();
    }

    public void setStage(Stage stage){
        this.stage = stage;
        Gdx.input.setInputProcessor(stage);
    }

    private void createBackground() {
        batch = new ModelBatch();
        ModelFactory.init();
        cam = new PerspectiveCamera(90, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(100f, 100f, 100f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 500f;
        cam.update();

        environment = new Environment();
        environment.set(new
                ColorAttribute(ColorAttribute.AmbientLight,
                0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -
                1f, -0.8f, -0.2f));

        engine = new Engine();
        engine.addEntity(EntityFactory.createGameBoard(0, 50, 0));
        engine.addSystem(new RenderSystem(batch, environment, cam));



    }

    private void setBGM() {
        music = Gdx.audio.newMusic(Gdx.files.internal("BGM/main_menu.wav"));
        music.setLooping(true);
    }

    public void stopBGM(){
        this.music.stop();
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |
                GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin(cam);
        updateBoardCamera();
        engine.update(delta);
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.6f));
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();


    }

    private void updateBoardCamera(){

        //orbits slowly around centre point
        cam.lookAt(0, 0, 0);
        cam.rotateAround(new Vector3(0, 0, 0), new Vector3(0, 1, 0), 0.1f);

        cam.update();
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
    }
}
