package com.mygdx.game.utils;

public enum GameAssets {
    BOARD("Board2/Board2_New.g3db"),
    DICE("Dice/Dice_New.obj"),
    TOKEN_1("Token/Token_New.g3db");

    String filepath;

    GameAssets(String filepath) {
        this.filepath = filepath;
    }
}
