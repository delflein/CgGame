package com.mygdx.game.utils;

public enum GameAssets {
    BOARD("Board2/Board2_New.g3db"),
    //DICE("Dice/Dice_New.obj"),
    TOKEN_1("Token/Token_New.g3db"),
    TOKEN_2("Token2/Token2_New.g3db"),
    TOKEN_3("Token3/Token3_New.g3db"),
    TOKEN_4("Token4/Token4_New.g3db");

    String filepath;

    GameAssets(String filepath) {
        this.filepath = filepath;
    }
}
