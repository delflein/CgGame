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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
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


   //UI
   private Stage stage;
   private Skin skin;
   private Music music;

    public MainMenuScreen(final MyGdxGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("Skins/default/uiskin.json"));

        createBackground();

        playBGM();
        buildStage();

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

    private void playBGM() {
        music = Gdx.audio.newMusic(Gdx.files.internal("BGM/main_menu.wav"));
        music.setLooping(true);
        music.play();
    }

    private void buildStage() {
        stage = new Stage(new ScreenViewport());


        Label title = new Label("Monopoly feat. CG-Superstars", skin);
        title.setColor(Color.GOLD);
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*7/8);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);

        TextButton playButton = new TextButton("Play!", skin);
        playButton.setWidth(Gdx.graphics.getWidth()/2);
        playButton.setPosition(Gdx.graphics.getWidth()/2-playButton.getWidth()/2,Gdx.graphics.getHeight()/6-playButton.getHeight()/2);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                music.stop();
                game.setScreen(new GameScreen(game));

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(playButton);

        TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.setWidth(Gdx.graphics.getWidth()/2);
        optionsButton.setPosition(Gdx.graphics.getWidth()/2-optionsButton.getWidth()/2,Gdx.graphics.getHeight()/10-optionsButton.getHeight()/2);
        optionsButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new OptionScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(optionsButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
        stage.act();
        stage.draw();
    }

    private void updateBoardCamera(){

        //orbits slowly around centre point
        cam.lookAt(0, 0, 0);
        cam.rotateAround(new Vector3(0, 0, 0), new Vector3(0, 1, 0), 0.1f);

        //amount to pan up/down along the Y axis
        //cam.position.add(0, panY * PAN_Y_SPEED, 0);
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
        stage.dispose();
        batch.dispose();

    }
}
