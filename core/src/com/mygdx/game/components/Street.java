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
        streets.add(new Street("Start", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Old Kent Road", "Brown", 0, new Vector3(49, 5, 69), new Vector3(49, 5, 75), new Vector3(55, 5, 69), new Vector3(55, 5, 75)));
        streets.add(new Street("Community Chest", "neutral", 0, new Vector3(36, 5, 69), new Vector3(36, 5, 75), new Vector3(42, 5, 69), new Vector3(42, 5, 75)));
        streets.add(new Street("Whitechapel Road", "Brown", 0, new Vector3(23, 5, 69), new Vector3(23, 5, 75), new Vector3(29, 5, 69), new Vector3(29, 5, 75)));
        streets.add(new Street("Income Tax", "neutral", 0, new Vector3(10, 5, 69), new Vector3(10, 5, 75), new Vector3(16, 5, 69), new Vector3(16, 5, 75)));
        streets.add(new Street("Kings Cross Station", "neutral", 0, new Vector3(-3, 5, 69), new Vector3(-3, 5, 75), new Vector3(3, 5, 69), new Vector3(3, 5, 75)));
        streets.add(new Street("The Angel Islington", "LightBlue", 0, new Vector3(-16, 5, 69), new Vector3(-16, 5, 75), new Vector3(-10, 5, 69), new Vector3(-10, 5, 75)));
        streets.add(new Street("Chance", "neutral", 0, new Vector3(-29, 5, 69), new Vector3(-29, 5, 75), new Vector3(-23, 5, 69), new Vector3(-23, 5, 75)));
        streets.add(new Street("Euston Road", "LightBlue", 0, new Vector3(-42, 5, 69), new Vector3(-42, 5, 75), new Vector3(-36, 5, 69), new Vector3(-36, 5, 75)));
        streets.add(new Street("Pentonville Road", "LightBlue", 0, new Vector3(-55, 5, 69), new Vector3(-55, 5, 75), new Vector3(-49, 5, 69), new Vector3(-49, 5, 75)));
        streets.add(new Street("Jail", "neutral", 0, new Vector3(-68, 5, 69), new Vector3(-68, 5, 75), new Vector3(-62, 5, 69), new Vector3(-62, 5, 75)));
        streets.add(new Street("Pall Mall", "Pink", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Electric Company", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Whitehall", "Pink", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Northumrl'd Avenuea", "Pink", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Marylebone Station", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Bow Street", "Orange", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Community Chest", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Marlborough Street", "Orange", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Vine Street", "Orange", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Free Parking", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Strand", "Red", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Chance", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Fleet Street", "Red", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Trafalgar Square", "Red", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Fenchurch St. Station", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Leicester Square", "Yellow", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Coventry Street", "Yellow", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Water Works", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Piccadilly", "Yellow", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Go to Jail", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Regent Street", "Green", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Oxford Street", "Green", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Community Chest", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Bond Street", "Green", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Liverpool St. Station", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Chance", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Park Lane", "DarkBlue", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Super Tax", "neutral", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
        streets.add(new Street("Mayfair", "DarkBlue", 0, new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0), new Vector3(0, 5, 0)));
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
        if (id + 1 > streets.size()) {
            return streets.get(0);
        } else {
            return streets.get(id + 1);
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
