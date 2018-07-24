package com.mygdx.game.components;

import com.badlogic.gdx.graphics.Color;

public class MonopolyColors {

    public static final Color BROWN = new Color(134 / 255f,73 / 255f, 28 / 255f, 1);
    public static final Color LIGHT_BLUE = new Color(170 / 255f,224 / 255f, 250 / 255f, 1);
    public static final Color PINK = new Color(217 / 255f,58 / 255f, 150 / 255f, 1);
    public static final Color ORANGE = new Color(246 / 255f,148 / 255f, 29 / 255f, 1);
    public static final Color RED = new Color(237 / 255f,27 / 255f, 36 / 255f, 1);
    public static final Color YELLOW = new Color(254 / 255f,242 / 255f, 0 / 255f, 1);
    public static final Color GREEN = new Color(31 / 255f,178 / 255f, 90 / 255f, 1);
    public static final Color DARK_BLUE = new Color(170 / 255f,224 / 255f, 250 / 255f, 1);
    public static final Color WHITE = Color.WHITE;
    public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;

    public static Color getColorByIndex(int i){
        switch (i){
            case 0:
                return BROWN;
            case 1:
                return LIGHT_BLUE;
            case 2:
                return PINK;
            case 3:
                return ORANGE;
            case 4:
                return RED;
            case 5:
                return YELLOW;
            case 6:
                return GREEN;
            case 7:
                return DARK_BLUE;
            case 8:
                return WHITE;
            case 9:
                return LIGHT_GRAY;
        }
        return Color.CLEAR;
    }


}
