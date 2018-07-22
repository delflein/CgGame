package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.settings.GraphicsSettings;
import com.mygdx.game.utils.ModelFactory;

public class MyGdxGame extends Game {

    private static Runnable rebootHook;

    /**
     * @param rebootHook game restart hook
     */
    public MyGdxGame(final Runnable rebootHook) {
        MyGdxGame.rebootHook = rebootHook;
    }

    /**
     * Restarts game client
     */
    public static void restart() {
        Gdx.app.postRunnable(MyGdxGame.rebootHook);
    }

    @Override
    public void render() {
        if (screen != null) {
            screen.render(Gdx.graphics.getDeltaTime());
        }
    }

    public void resize(int width, int height) {
        screen.resize(width, height);
    }

    public Screen getScreen(){ return this.screen;}

    public void setScreen(Screen screen) {
        if (this.screen != null) {
            this.screen.hide();
            this.screen.dispose();
        }
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    @Override
    public void dispose() {
        if (screen != null) {
            screen.dispose();
        }
    }

    @Override
    public void create() {
        ModelFactory.init();
        GraphicsSettings.setGraphics();
        Gdx.input.setCatchBackKey(true);
        setScreen(new MainMenuScreen(this));
    }
}
