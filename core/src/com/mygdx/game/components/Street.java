package com.mygdx.game.components;

import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class Street {

    private static List<Street> streets = new ArrayList<Street>();

    private String name;
    private String colorCode;

    private int value;

    private Vector3 p1_position;
    private Vector3 p2_position;
    private Vector3 p3_position;
    private Vector3 p4_position;

    private Street(String name, String colorCode, int value, Vector3 p1_position, Vector3 p2_position, Vector3 p3_position, Vector3 p4_position) {
        this.name = name;
        this.colorCode = colorCode;
        this.value = value;
        this.p1_position = p1_position;
        this.p2_position = p2_position;
        this.p3_position = p3_position;
        this.p4_position = p4_position;
    }

    public static void init() {
        streets.add(new Street("Start", "neutral", 0, new Vector3(66f, 0f, 66f), new Vector3(66f, 0f, 72f), new Vector3(72f, 0f, 66f), new Vector3(72f, 0f, 72f)));
        streets.add(new Street("Mediterranean Avenue", "Brown", 0, new Vector3(50f, 0f, 69f), new Vector3(50f, 0f, 75f), new Vector3(56f, 0f, 69f), new Vector3(56f, 0f, 75f)));
        streets.add(new Street("Community Chest", "neutral", 0, new Vector3(37f, 0f, 69f), new Vector3(37f, 0f, 75f), new Vector3(43f, 0f, 69f), new Vector3(43f, 0f, 75f)));
        streets.add(new Street("Baltic Avenue", "Brown", 0, new Vector3(23f, 0f, 69f), new Vector3(23f, 0f, 75f), new Vector3(29f, 0f, 69f), new Vector3(29f, 0f, 75f)));
        streets.add(new Street("Income Tax", "neutral", 0, new Vector3(10f, 0f, 69f), new Vector3(10f, 0f, 75f), new Vector3(16f, 0f, 69f), new Vector3(16f, 0f, 75f)));
        streets.add(new Street("Reading Railroad", "neutral", 0, new Vector3(-3f, 0f, 69f), new Vector3(-3f, 0f, 75f), new Vector3(3f, 0f, 69f), new Vector3(3f, 0f, 75f)));
        streets.add(new Street("Oriental Avenue", "LightBlue", 0, new Vector3(-16f, 0f, 69f), new Vector3(-16f, 0f, 75f), new Vector3(-10f, 0f, 69f), new Vector3(-10f, 0f, 75f)));
        streets.add(new Street("Chance", "neutral", 0, new Vector3(-29f, 0f, 69f), new Vector3(-29f, 0f, 75f), new Vector3(-23f, 0f, 69f), new Vector3(-23f, 0f, 75f)));
        streets.add(new Street("Vermont Avenue", "LightBlue", 0, new Vector3(-42f, 0f, 69f), new Vector3(-42f, 0f, 75f), new Vector3(-36f, 0f, 69f), new Vector3(-36f, 0f, 75f)));
        streets.add(new Street("Connecticut Avenue", "LightBlue", 0, new Vector3(-55f, 0f, 69f), new Vector3(-55f, 0f, 75f), new Vector3(-49f, 0f, 69f), new Vector3(-49f, 0f, 75f)));
        streets.add(new Street("Jail", "neutral", 0, new Vector3(-72f, 0f, 66f), new Vector3(-72f, 0f, 72f), new Vector3(-66f, 0f, 66f), new Vector3(-66f, 0f, 72f)));
        streets.add(new Street("St. Charles Place", "Pink", 0, new Vector3(-75f, 0f, 49f), new Vector3(-75f, 0f, 55f), new Vector3(-69f, 0f, 49f), new Vector3(-69f, 0f, 55f)));
        streets.add(new Street("Electric Company", "neutral", 0, new Vector3(-75f, 0f, 37f), new Vector3(-75f, 0f, 43f), new Vector3(-69f, 0f, 37f), new Vector3(-69f, 0f, 43f)));
        streets.add(new Street("States Avenue", "Pink", 0, new Vector3(-75f, 0f, 23f), new Vector3(-75f, 0f, 29f), new Vector3(-69f, 0f, 23f), new Vector3(-69f, 0f, 29f)));
        streets.add(new Street("Virginia Avenue", "Pink", 0, new Vector3(-75f, 0f, 10f), new Vector3(-75f, 0f, 16f), new Vector3(-69f, 0f, 10f), new Vector3(-69f, 0f, 16f)));
        streets.add(new Street("Pennsylvania Railroad", "neutral", 0, new Vector3(-75f, 0f, -3f), new Vector3(-75f, 0f, 3f), new Vector3(-69f, 0f, -3f), new Vector3(-69f, 0f, 3f)));
        streets.add(new Street("St. James Place", "Orange", 0, new Vector3(-75f, 0f, -16f), new Vector3(-75f, 0f, -10f), new Vector3(-69f, 0f, -16f), new Vector3(-69f, 0f, -10f)));
        streets.add(new Street("Community Chest", "neutral", 0, new Vector3(-75f, 0f, -29f), new Vector3(-75f, 0f, -23f), new Vector3(-69f, 0f, -29f), new Vector3(-69f, 0f, -23f)));
        streets.add(new Street("Tennessee Avenue", "Orange", 0, new Vector3(-75f, 0f, -43f), new Vector3(-75f, 0f, -37f), new Vector3(-69f, 0f, -43f), new Vector3(-69f, 0f, -37f)));
        streets.add(new Street("New York Avenue", "Orange", 0, new Vector3(-75f, 0f, -55f), new Vector3(-75f, 0f, -49f), new Vector3(-69f, 0f, -55f), new Vector3(-69f, 0f, -49f)));
        streets.add(new Street("Free Parking", "neutral", 0, new Vector3(-72f, 0f, -72f), new Vector3(-72f, 0f, -66f), new Vector3(-66f, 0f, -72f), new Vector3(-66f, 0f, -66f)));
        streets.add(new Street("Kentucky Avenue", "Red", 0, new Vector3(-56f, 0f, -75f), new Vector3(-56f, 0f, -69f), new Vector3(-50f, 0f, -75f), new Vector3(-50f, 0f, -69f)));
        streets.add(new Street("Chance", "neutral", 0, new Vector3(-43f, 0f, -75f), new Vector3(-43f, 0f, -69f), new Vector3(-37f, 0f, -75f), new Vector3(-37f, 0f, -69f)));
        streets.add(new Street("Indiana Avenue", "Red", 0, new Vector3(-29f, 0f, -75f), new Vector3(-29f, 0f, -69f), new Vector3(-23f, 0f, -75f), new Vector3(-23f, 0f, -69f)));
        streets.add(new Street("Illinois Avenue", "Red", 0, new Vector3(-16f, 0f, -75f), new Vector3(-16f, 0f, -69f), new Vector3(-10f, 0f, -75f), new Vector3(-10f, 0f, -69f)));
        streets.add(new Street("B. & O. Railroad", "neutral", 0, new Vector3(-3f, 0f, -75f), new Vector3(-3f, 0f, -69f), new Vector3(3f, 0f, -75f), new Vector3(3f, 0f, -69f)));
        streets.add(new Street("Atlantic Avenue", "Yellow", 0, new Vector3(10f, 0f, -75f), new Vector3(10f, 0f, -69f), new Vector3(16f, 0f, -75f), new Vector3(16f, 0f, -69f)));
        streets.add(new Street("Ventnor Avenue", "Yellow", 0, new Vector3(23f, 0f, -75f), new Vector3(23f, 0f, -69f), new Vector3(29f, 0f, -75f), new Vector3(29f, 0f, -69f)));
        streets.add(new Street("Water Works", "neutral", 0, new Vector3(36f, 0f, -75f), new Vector3(36f, 0f, -69f), new Vector3(42f, 0f, -75f), new Vector3(42f, 0f, -69f)));
        streets.add(new Street("Marvin Gardens", "Yellow", 0, new Vector3(50f, 0f, -75f), new Vector3(50f, 0f, -69f), new Vector3(56f, 0f, -75f), new Vector3(56f, 0f, -69f)));
        streets.add(new Street("Go to Jail", "neutral", 0, new Vector3(66f, 0f, -73f), new Vector3(66f, 0f, -67f), new Vector3(72f, 0f, -73f), new Vector3(72f, 0f, -67f)));
        streets.add(new Street("Pacific Avenue", "Green", 0, new Vector3(69f, 0f, -56f), new Vector3(69f, 0f, -50f), new Vector3(75f, 0f, -56f), new Vector3(75f, 0f, -50f)));
        streets.add(new Street("North Carolina Avenue", "Green", 0, new Vector3(69f, 0f, -43f), new Vector3(69f, 0f, -37f), new Vector3(75f, 0f, -43f), new Vector3(75f, 0f, -37f)));
        streets.add(new Street("Community Chest", "neutral", 0, new Vector3(69f, 0f, -30f), new Vector3(69f, 0f, -24f), new Vector3(75f, 0f, -30f), new Vector3(75f, 0f, -24f)));
        streets.add(new Street("Pennsylvania Avenue", "Green", 0, new Vector3(69f, 0f, -16f), new Vector3(69f, 0f, -10f), new Vector3(75f, 0f, -16f), new Vector3(75f, 0f, -10f)));
        streets.add(new Street("Shortline", "neutral", 0, new Vector3(69f, 0f, -3f), new Vector3(69f, 0f, 3f), new Vector3(75f, 0f, -3f), new Vector3(75f, 0f, 3f)));
        streets.add(new Street("Chance", "neutral", 0, new Vector3(69f, 0f, 10f), new Vector3(69f, 0f, 16f), new Vector3(75f, 0f, 10f), new Vector3(75f, 0f, 16f)));
        streets.add(new Street("Park Place", "DarkBlue", 0, new Vector3(69f, 0f, 23f), new Vector3(69f, 0f, 29f), new Vector3(75f, 0f, 23f), new Vector3(75f, 0f, 29f)));
        streets.add(new Street("Luxury Tax", "neutral", 0, new Vector3(69f, 0f, 37f), new Vector3(69f, 0f, 43f), new Vector3(75f, 0f, 37f), new Vector3(75f, 0f, 43f)));
        streets.add(new Street("Boardwalk", "DarkBlue", 0, new Vector3(69f, 0f, 50f), new Vector3(69f, 0f, 56f), new Vector3(75f, 0f, 50f), new Vector3(75f, 0f, 56f)));
    }

    public List<Vector3> getPath(int numberOfFields, PlayerComponent component) {
        List<Vector3> path = new ArrayList<>();
        Street toReturn = this;
        while (numberOfFields > 0) {
            toReturn = toReturn.getNext();
            path.add(toReturn.getPosition(component));
            numberOfFields--;
        }
        return path;
    }

    public Street move(int numberOfFields) {
        Street toReturn = this;
        while (numberOfFields > 0) {
            toReturn = toReturn.getNext();
            numberOfFields--;
        }
        return toReturn;
    }

    private Street getNext() {
        int id = streets.indexOf(this);
        id++;
        if (id == streets.size()) {
            return streets.get(0);
        } else {
            return streets.get(id);
        }
    }

    public Vector3 getPosition(PlayerComponent comp) {
        switch (comp.getId()) {
            case 0:
                return p1_position;
            case 1:
                return p2_position;
            case 2:
                return p3_position;
            case 3:
                return p4_position;
        }
        return null;
    }

    public static List<Street> getStreets() {
        return streets;
    }

    public static void setStreets(List<Street> streets) {
        Street.streets = streets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
