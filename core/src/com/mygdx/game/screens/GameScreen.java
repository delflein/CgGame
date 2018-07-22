package com.mygdx.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.components.GameSettings;
import com.mygdx.game.components.ModelComponent;
import com.mygdx.game.components.SelectableComponent;
import com.mygdx.game.components.Street;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.GameInput;
import com.mygdx.game.stages.UI.GameUI;
import com.mygdx.game.systems.PlayerSystem;
import com.mygdx.game.systems.RenderSystem;
import com.mygdx.game.utils.EntityFactory;
import com.mygdx.game.utils.ModelFactory;

public class GameScreen implements Screen {

    private ModelBatch batch;
    private PerspectiveCamera cam;
    private Environment environment;
    private Engine engine;
    private Game game;
    private GameSettings settings;

    // GUI
    private Stage stage;
    private Label label;
    private StringBuilder stringBuilder;

    //Sound
    private Music music;

    //GameController
    private GameController gameController;

    public GameScreen(Game game, GameSettings settings) {
        this.game = game;
        this.settings = settings;
        batch = new ModelBatch();
        selectionMaterial = new Material();
        selectionMaterial.set(ColorAttribute.createDiffuse(Color.ORANGE));
        originalMaterial = new Material();
        Street.init();
        initCam();
        initEnvironment();
        ModelFactory.init();
        initEngine();
        this.gameController = new GameController(engine,  settings);
        initGui();
        initInput();
        startBGM();
        //Gdx.input.setCursorCatched(true);
    }

    private void startBGM() {
            music =  Gdx.audio.newMusic(Gdx.files.internal("BGM/game_theme.mp3"));
            music.setLooping(true);
            music.play();
    }

    private void initGui() {
        setStage(new GameUI(this,this.engine));
        BitmapFont font = new BitmapFont();
        label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        stage.addActor(label);
        stringBuilder = new StringBuilder();
    }

    private void initCam() {
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0, 100, 0);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 500f;
        cam.update();
    }

    private void initInput() {
        InputMultiplexer multi = new InputMultiplexer();

        GameInput gameInput = new GameInput(this);
        CameraInputController cameraInputController = new CameraInputController(cam);

        multi.addProcessor(stage);
        multi.addProcessor(gameInput);
        multi.addProcessor(cameraInputController);

        Gdx.input.setInputProcessor(multi);
    }

    private void initEnvironment() {
        environment = new Environment();
        environment.set(new
                ColorAttribute(ColorAttribute.AmbientLight,
                0.65f, 0.65f, 0.65f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -
                1f, -0.8f, -0.2f));
    }

    private void initEngine() {
        engine = new Engine();
        engine.addSystem(new RenderSystem(batch, environment, cam));
        engine.addSystem(new PlayerSystem());

        //Add Entities

        Color[] colors = {Color.GREEN,Color.YELLOW,Color.RED,Color.BLUE};
        engine.addEntity(EntityFactory.createGameBoard(0, 0, 0));
        for (int i = 0; i < settings.players ; i++) {
            engine.addEntity(EntityFactory.createPlayer(2, 10, 2, colors[i]));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |
                GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin(cam);
        engine.update(delta);
        batch.end();
        gameController.update(delta);
        stringBuilder.setLength(0);
        stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());
        stringBuilder.append(" Visible: ").append(engine.getSystem(RenderSystem.class).getVisibleCount());
        stringBuilder.append(" Selected: ").append(selected);
        label.setText(stringBuilder);
        stage.act();
        stage.draw();
    }

    public Entity selected = null, selecting = null;
    private Material selectionMaterial;
    private Material originalMaterial;

    public void setSelected(Entity entity) {
        if (selected == entity) return;
        if (selected != null) {
            ModelInstance instance = selected.getComponent(ModelComponent.class).getInstance();
            Material mat = instance.materials.get(0);
            mat.clear();
            mat.set(originalMaterial);
        }
        selected = entity;
        if (selected != null) {
            ModelInstance instance = entity.getComponent(ModelComponent.class).getInstance();
            Material mat = instance.materials.get(0);
            originalMaterial.clear();
            originalMaterial.set(mat);
            mat.clear();
            mat.set(selectionMaterial);
        }
    }

    public Entity getObject(int screenX, int screenY) {
        Ray ray = cam.getPickRay(screenX, screenY);
        ImmutableArray<Entity> entities = engine.getSystem(RenderSystem.class).getEntities();
        Entity result = null;
        float distance = -1;

        for (Entity entity : entities) {
            SelectableComponent sel = entity.getComponent(SelectableComponent.class);
            if (sel != null) {
                final ModelComponent mod = sel.getModelComponent();
                Vector3 position = new Vector3();
                mod.getInstance().transform.getTranslation(position);
                position.add(mod.center);

                float dist2 = ray.origin.dst2(position);
                if (distance >= 0f && dist2 > distance)
                    continue;

                if (sel.getModelComponent().intersects(ray)) {
                    result = entity;
                    distance = dist2;
                }
            }
        }

        return result;
    }

    public PerspectiveCamera getCam() {
        return this.cam;
    }

    public Engine getEngine() {
        return engine;
    }

    public GameController getGameController() { return gameController; }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = width;
        cam.viewportHeight = height;
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
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
