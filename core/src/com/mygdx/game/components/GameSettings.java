package com.mygdx.game.components;

public class GameSettings {

    public int    players;
    public double startMoney;
    public boolean doubleCash;
    public double gocash;

    public GameSettings(int players,double startMoney, boolean doubleCash, double gocash){
        this.players = players;
        this.startMoney = startMoney;
        this.doubleCash = doubleCash;
        this.gocash = gocash;
    }
}
