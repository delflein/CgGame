package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.stages.UI.Dice;

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

    // Jail Stats
    private int jail_counter = 0;

    public PlayerComponent() {
        this.id = numOfPlayers++;
        this.currentStreet = Street.getStreets().get(0);

        this.money = 10000;
        this.owned_streets = new ArrayList<Street>();
    }

    public Vector3 move(int numOfFields) {
        currentStreet = currentStreet.move(numOfFields);
        currentStreet.effect(this);
        return currentStreet.getPosition(this);
    }

    public void moveSmooth(int numOfFields) {
        List<Vector3> path = currentStreet.getPath(numOfFields, this);
        currentStreet = currentStreet.move(numOfFields);
        MovingComponent mov = GameController.getCurrentPlayer().getComponent(MovingComponent.class);
        mov.setPath(path);
    }

    public void buyStreet() {
        if (money > currentStreet.getCost()) {
            money -= currentStreet.getCost();
            owned_streets.add(currentStreet);
            currentStreet.setSold();
        }
    }

    public void payRent() {
        PlayerComponent enemy = currentStreet.getOwner();
        boolean hasAll = currentStreet.hasAllOfType(enemy);
        int rent = 0;
        int multiplier = 0;
        switch (currentStreet.getType()) {
            case FACILITY:
                multiplier = (hasAll) ? 10 : 4;
                rent = multiplier * Dice.getRollSum();
            case PROPERTY:
                if (currentStreet.getHouses() < 1) {
                    multiplier = (hasAll) ? 2 : 1;
                    rent = getCurrentStreet().getBase_rent() * multiplier;
                } else {
                    rent = currentStreet.getRents()[currentStreet.getHouses() - 1];
                }
            case STATION:

        }
        this.money -= rent;
        enemy.money += rent;
    }



    public boolean isInJail() {
        return jail_counter > 0;
    }

    public void lowerJailCount() {
        jail_counter--;
    }

    public void freeFromJail() {
        jail_counter = 0;
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
