package com.mygdx.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class House {

    private static Map<Street, House> houses = new HashMap<>();

    public static void init() {
        JSONParser parser = new JSONParser();
        File file = Gdx.files.internal("houses.json").file();

        try {
            JSONArray streets = (JSONArray) parser.parse(new FileReader(file));
            for (Object obj : streets) {
                JSONObject street = (JSONObject) obj;
                try{
                    JSONObject h1 = (JSONObject) street.get("house1");
                    double x1double = (double) h1.get("x");
                    int x1 = (int) x1double;
                    double y1double = (double) h1.get("y");
                    int y1 = (int) y1double;
                    double z1double = (double) h1.get("z");
                    int z1 = (int) z1double;

                    JSONObject h2 = (JSONObject) street.get("house2");
                    double x2double = (double) h2.get("x");
                    int x2 = (int) x2double;
                    double y2double = (double) h2.get("y");
                    int y2 = (int) y2double;
                    double z2double = (double) h2.get("z");
                    int z2 = (int) z2double;

                    JSONObject h3 = (JSONObject) street.get("house3");
                    double x3double = (double) h3.get("x");
                    int x3 = (int) x3double;
                    double y3double = (double) h3.get("y");
                    int y3 = (int) y3double;
                    double z3double = (double) h3.get("z");
                    int z3 = (int) z3double;

                    JSONObject h4 = (JSONObject) street.get("house4");
                    double x4double = (double) h4.get("x");
                    int x4 = (int) x4double;
                    double y4double = (double) h4.get("y");
                    int y4 = (int) y4double;
                    double z4double = (double) h4.get("z");
                    int z4 = (int) z4double;
                    
                    Vector3 house1 = new Vector3(x1, y1, z1);
                    Vector3 house2 = new Vector3(x2, y2, z2);
                    Vector3 house3 = new Vector3(x3, y3, z3);
                    Vector3 house4 = new Vector3(x4, y4, z4);

                    long rotationLong = (long) street.get("rotation");
                    int rotation = (int) rotationLong;

                    long streetIdLong = (long) street.get("streetId");
                    int streetId = (int) streetIdLong;

                    houses.put(Street.getStreets().get(streetId), new House(house1, house2, house3, house4, new Vector3((house2.x + house3.x) / 2, 0, (house2.z + house3.z) / 2), rotation));
                } catch (ClassCastException e) {
                    System.out.println(street.toJSONString());
                    e.printStackTrace();
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private Vector3 house1_pos;
    private Vector3 house2_pos;
    private Vector3 house3_pos;
    private Vector3 house4_pos;
    private Vector3 hotel_pos;
    private int rotation;

    public House(Vector3 house1_pos, Vector3 house2_pos, Vector3 house3_pos, Vector3 house4_pos, Vector3 hotel_pos, int rotation) {
        this.house1_pos = house1_pos;
        this.house2_pos = house2_pos;
        this.house3_pos = house3_pos;
        this.house4_pos = house4_pos;
        this.hotel_pos = hotel_pos;
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    public Vector3 getHousePosition(int number){
        switch (number){
            case 1:
                return house1_pos;
            case 2:
                return house2_pos;
            case 3:
                return house3_pos;
            case 4:
                return house4_pos;
            case 5:
                return hotel_pos;
        }
        return null;
    }

    public static House getHouse(Street street) {
        return houses.get(street);
    }
}
