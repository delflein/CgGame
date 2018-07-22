package com.mygdx.game.stages.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface GameUiElement<T extends Actor> {

    public T create();
}
