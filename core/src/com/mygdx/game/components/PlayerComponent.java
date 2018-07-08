package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class PlayerComponent implements Component {

    private static int numOfPlayers = 0;

    // Used to keep track of Player Turn/Ownership etc.
    private int id;
    private Street currentStreet;

    // Needed Stats
    private int money;
    private List<Street> owned_streets;

    public PlayerComponent() {
        this.id = numOfPlayers++;
        this.currentStreet = Street.getStreets().get(1);

        this.money = 10000;
        this.owned_streets = new ArrayList<Street>();
    }

    public Vector3 move(int numOfFields) {
        currentStreet = currentStreet.move(numOfFields);
        return currentStreet.getPosition(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Street getCurrentStreet() {
        return currentStreet;
    }

    public void setCurrentStreet(Street currentStreet) {
        this.currentStreet = currentStreet;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<Street> getOwned_streets() {
        return owned_streets;
    }

    public void setOwned_streets(List<Street> owned_streets) {
        this.owned_streets = owned_streets;
    }
}
