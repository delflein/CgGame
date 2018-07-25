package com.mygdx.game.settings;

public class GameSettings {

    public int    players;
    public int startMoney;
    public boolean doubleCash;
    public int gocash;

    public GameSettings(int players,int startMoney, boolean doubleCash, int gocash){
        this.players = players;
        this.startMoney = startMoney;
        this.doubleCash = doubleCash;
        this.gocash = gocash;
    }
}
