package com.mygdx.game.stages.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.settings.GraphicsSettings;
import com.mygdx.game.settings.PreferenceStrings;

class GraphicsSettingsTable extends Table {

    private TextButton tbCancel;

    /**
     * Options dialog to let the user choose their display resolution, whether
     * to run in fullscreen/windowed mode, and whether to enable vsync.
     *
     * @param skin
     */
    GraphicsSettingsTable(Skin skin) {

        GraphicsSettings mGraphicsSettings = new GraphicsSettings();

        final Preferences pref = Gdx.app.getPreferences("Monopoly");

        final SelectBox<GraphicsSettings.CustomDisplayMode> sbResolution = new SelectBox<GraphicsSettings.CustomDisplayMode>(skin);
        final CheckBox cbFullscreen = new CheckBox("", skin);
        final CheckBox cbVSync = new CheckBox("", skin);
        tbCancel = new TextButton("Cancel", skin);
        TextButton tbAccept = new TextButton("Accept? (requires restart)", skin);

        add(new Label("GraphicsSettings", skin)).colspan(2).center();
        row().fill();

        add(new Label("Resolution", skin)).left();
        add(sbResolution).left();
        row().fill();

        add(new Label("Fullscreen", skin)).left();
        add(cbFullscreen).left();
        row().fill();

        add(new Label("VSync", skin)).left();
        add(cbVSync).left();
        row().fill();

        add(tbCancel).left();
        add(tbAccept).left();

        sbResolution.setItems(mGraphicsSettings.getDisplayModes());
        sbResolution.setSelected(mGraphicsSettings.getLaunchDisplayMode());
        if (Gdx.graphics.isFullscreen()) {
            cbFullscreen.setChecked(true);
        }
        if (pref.getBoolean(PreferenceStrings.VSYNC)) {
            cbVSync.setChecked(true);
        }

        tbAccept.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pref.putInteger(PreferenceStrings.DISPLAY_WIDTH, sbResolution.getSelected().width);
                pref.putInteger(PreferenceStrings.DISPLAY_HEIGHT, sbResolution.getSelected().height);
                pref.putBoolean(PreferenceStrings.FULLSCREEN, cbFullscreen.isChecked());
                pref.putBoolean(PreferenceStrings.VSYNC, cbVSync.isChecked());
                pref.flush();
                MyGdxGame.restart();
            }
        });

        pack();
        this.setFillParent(true);
    }

    public TextButton getTbCancel() {
        return tbCancel;
    }
}
