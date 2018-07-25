package com.mygdx.game.components;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.stages.UI.ActionCardView;
import com.mygdx.game.stages.UI.Dice;
import com.mygdx.game.stages.UI.StreetViewTable;
import com.mygdx.game.utils.EntityFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Street {

    public int getCost() {
        return cost;
    }

    private static List<Street> streets = new ArrayList<Street>();

    private String name;
    private Color colorCode;

    private int cost;
    private int cost_house;
    private int cost_hotel;
    private int base_rent;
    private int[] rents;
    private int hypothek;
    int houses;
    private List<Entity> houseEntities = new ArrayList<>();

    private Vector3 p1_position;
    private Vector3 p2_position;
    private Vector3 p3_position;
    private Vector3 p4_position;

    private StreetType type;
    int sold;
    boolean mortgaged;

    private Street(String name, Color colorCode, StreetType type, int cost, int cost_house, int cost_hotel, int base_rent, int[] rents, int hypothek, Vector3 p1_position, Vector3 p2_position, Vector3 p3_position, Vector3 p4_position) {
        this.name = name;
        this.colorCode = colorCode;
        this.base_rent = base_rent;
        this.cost = cost;
        this.cost_house = cost_house;
        this.cost_hotel = cost_hotel;
        this.rents = rents;
        this.hypothek = hypothek;
        this.type = type;
        this.p1_position = p1_position;
        this.p2_position = p2_position;
        this.p3_position = p3_position;
        this.p4_position = p4_position;
        this.sold = -1;
        this.mortgaged = false;
        this.houses = 0;
    }

    public static void init() {
        streets.add(new Street("Start", null, StreetType.GO,  0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(66f, 0f, 66f), new Vector3(66f, 0f, 72f), new Vector3(72f, 0f, 66f), new Vector3(72f, 0f, 72f)));
        streets.add(new Street("Mediterranean Avenue", MonopolyColors.BROWN, StreetType.PROPERTY, 60, 50, 50, 2, new int[] {10,30,90,160,250}, 30, new Vector3(50f, 0f, 69f), new Vector3(50f, 0f, 75f), new Vector3(56f, 0f, 69f), new Vector3(56f, 0f, 75f)));
        streets.add(new Street("Community Chest", null, StreetType.COMMUNITY_CHEST, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(37f, 0f, 69f), new Vector3(37f, 0f, 75f), new Vector3(43f, 0f, 69f), new Vector3(43f, 0f, 75f)));
        streets.add(new Street("Baltic Avenue", MonopolyColors.BROWN, StreetType.PROPERTY, 60, 50, 50, 4, new int[] {20,60,180,320,450}, 30, new Vector3(23f, 0f, 69f), new Vector3(23f, 0f, 75f), new Vector3(29f, 0f, 69f), new Vector3(29f, 0f, 75f)));
        streets.add(new Street("Income Tax", null, StreetType.TAX, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(10f, 0f, 69f), new Vector3(10f, 0f, 75f), new Vector3(16f, 0f, 69f), new Vector3(16f, 0f, 75f)));
        streets.add(new Street("Reading Railroad", Color.WHITE, StreetType.STATION, 200, 0, 0, 25, new int[]{25, 50, 100, 200, 0}, 100, new Vector3(-3f, 0f, 69f), new Vector3(-3f, 0f, 75f), new Vector3(3f, 0f, 69f), new Vector3(3f, 0f, 75f)));
        streets.add(new Street("Oriental Avenue", MonopolyColors.LIGHT_BLUE, StreetType.PROPERTY, 100, 50, 50, 6, new int[] {30,90,270,400,550}, 50, new Vector3(-16f, 0f, 69f), new Vector3(-16f, 0f, 75f), new Vector3(-10f, 0f, 69f), new Vector3(-10f, 0f, 75f)));
        streets.add(new Street("Chance", null, StreetType.CHANCE, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(-29f, 0f, 69f), new Vector3(-29f, 0f, 75f), new Vector3(-23f, 0f, 69f), new Vector3(-23f, 0f, 75f)));
        streets.add(new Street("Vermont Avenue", MonopolyColors.LIGHT_BLUE, StreetType.PROPERTY, 100, 50, 50, 6, new int[] {30,90,270,400,550}, 50, new Vector3(-42f, 0f, 69f), new Vector3(-42f, 0f, 75f), new Vector3(-36f, 0f, 69f), new Vector3(-36f, 0f, 75f)));
        streets.add(new Street("Connecticut Avenue", MonopolyColors.LIGHT_BLUE, StreetType.PROPERTY, 120, 50, 50, 8, new int[] {40,100,300,450,600}, 60, new Vector3(-55f, 0f, 69f), new Vector3(-55f, 0f, 75f), new Vector3(-49f, 0f, 69f), new Vector3(-49f, 0f, 75f)));
        streets.add(new Street("Jail", null, StreetType.JAIL, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(-72f, 0f, 66f), new Vector3(-66f, 0f, 66f), new Vector3(-66f, 0f, 72f), new Vector3(-72f, 0f, 72f)));
        streets.add(new Street("St. Charles Place", MonopolyColors.PINK, StreetType.PROPERTY, 140, 100, 100, 10, new int[] {50,150,450,625,750}, 70, new Vector3(-69f, 0f, 55f), new Vector3(-75f, 0f, 49f), new Vector3(-75f, 0f, 55f), new Vector3(-69f, 0f, 49f)));
        streets.add(new Street("Electric Company", Color.LIGHT_GRAY, StreetType.FACILITY, 150, 0, 0, 0, new int[] {0,0,0,0,0}, 75, new Vector3(-69f, 0f, 43f), new Vector3(-75f, 0f, 37f), new Vector3(-75f, 0f, 43f), new Vector3(-69f, 0f, 37f)));
        streets.add(new Street("States Avenue", MonopolyColors.PINK, StreetType.PROPERTY, 140, 100, 100, 10, new int[] {50,150,450,625,750}, 70, new Vector3(-69f, 0f, 29f), new Vector3(-75f, 0f, 23f), new Vector3(-75f, 0f, 29f), new Vector3(-69f, 0f, 23f)));
        streets.add(new Street("Virginia Avenue", MonopolyColors.PINK, StreetType.PROPERTY, 160, 100, 100, 12, new int[] {60,180,500,700,900}, 80, new Vector3(-69f, 0f, 16f), new Vector3(-75f, 0f, 10f), new Vector3(-75f, 0f, 16f), new Vector3(-69f, 0f, 10f)));
        streets.add(new Street("Pennsylvania Railroad", Color.WHITE, StreetType.STATION, 200, 0, 0, 25, new int[]{25, 50, 100, 200, 0}, 100, new Vector3(-69f, 0f, 3f), new Vector3(-75f, 0f, -3f), new Vector3(-75f, 0f, 3f), new Vector3(-69f, 0f, -3f)));
        streets.add(new Street("St. James Place", MonopolyColors.ORANGE, StreetType.PROPERTY, 180, 100, 100, 14, new int[] {70,200,550,750,950}, 90, new Vector3(-69f, 0f, -10f), new Vector3(-75f, 0f, -16f), new Vector3(-75f, 0f, -10f), new Vector3(-69f, 0f, -16f)));
        streets.add(new Street("Community Chest", null, StreetType.COMMUNITY_CHEST, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(-69f, 0f, -23f), new Vector3(-75f, 0f, -29f), new Vector3(-75f, 0f, -23f), new Vector3(-69f, 0f, -29f)));
        streets.add(new Street("Tennessee Avenue", MonopolyColors.ORANGE, StreetType.PROPERTY, 180, 100, 100, 14, new int[] {70,200,550,750,950}, 90, new Vector3(-69f, 0f, -37f), new Vector3(-75f, 0f, -43f), new Vector3(-75f, 0f, -37f), new Vector3(-69f, 0f, -43f)));
        streets.add(new Street("New York Avenue", MonopolyColors.ORANGE, StreetType.PROPERTY, 200, 100, 100, 16, new int[] {80,220,600,800,1000}, 100, new Vector3(-69f, 0f, -49f), new Vector3(-75f, 0f, -55f), new Vector3(-75f, 0f, -49f), new Vector3(-69f, 0f, -55f)));
        streets.add(new Street("Free Parking", null, StreetType.FREE_PARKING, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(-66f, 0f, -72f), new Vector3(-66f, 0f, -66f), new Vector3(-72f, 0f, -72f), new Vector3(-72f, 0f, -66f)));
        streets.add(new Street("Kentucky Avenue", MonopolyColors.RED, StreetType.PROPERTY, 220, 150, 150, 18, new int[] {90,250,700,875,1050}, 110, new Vector3(-50f, 0f, -75f), new Vector3(-50f, 0f, -69f), new Vector3(-56f, 0f, -75f), new Vector3(-56f, 0f, -69f)));
        streets.add(new Street("Chance", null, StreetType.CHANCE, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(-37f, 0f, -75f), new Vector3(-37f, 0f, -69f), new Vector3(-43f, 0f, -75f), new Vector3(-43f, 0f, -69f)));
        streets.add(new Street("Indiana Avenue", MonopolyColors.RED, StreetType.PROPERTY, 220, 150, 150, 18, new int[] {90,250,700,875,1050}, 110, new Vector3(-23f, 0f, -75f), new Vector3(-23f, 0f, -69f), new Vector3(-29f, 0f, -75f), new Vector3(-29f, 0f, -69f)));
        streets.add(new Street("Illinois Avenue", MonopolyColors.RED, StreetType.PROPERTY, 240, 150, 150, 20, new int[] {100,300,750,925,1100}, 120, new Vector3(-10f, 0f, -75f), new Vector3(-10f, 0f, -69f), new Vector3(-16f, 0f, -75f), new Vector3(-16f, 0f, -69f)));
        streets.add(new Street("B. & O. Railroad", Color.WHITE, StreetType.STATION, 200, 0, 0, 25, new int[]{25, 50, 100, 200, 0}, 100, new Vector3(3f, 0f, -75f), new Vector3(3f, 0f, -69f), new Vector3(-3f, 0f, -75f), new Vector3(-3f, 0f, -69f)));
        streets.add(new Street("Atlantic Avenue", MonopolyColors.YELLOW, StreetType.PROPERTY, 260, 150, 150, 22, new int[] {110,330,800,975,1150}, 130, new Vector3(16f, 0f, -75f), new Vector3(16f, 0f, -69f), new Vector3(10f, 0f, -75f), new Vector3(10f, 0f, -69f)));
        streets.add(new Street("Ventnor Avenue", MonopolyColors.YELLOW, StreetType.PROPERTY, 260, 150, 150, 22, new int[] {110,330,800,975,1150}, 130, new Vector3(29f, 0f, -75f), new Vector3(29f, 0f, -69f), new Vector3(23f, 0f, -75f), new Vector3(23f, 0f, -69f)));
        streets.add(new Street("Water Works", Color.LIGHT_GRAY, StreetType.FACILITY, 150, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(42f, 0f, -75f), new Vector3(42f, 0f, -69f), new Vector3(36f, 0f, -75f), new Vector3(36f, 0f, -69f)));
        streets.add(new Street("Marvin Gardens", MonopolyColors.YELLOW, StreetType.PROPERTY, 280, 150, 150, 24, new int[] {120,360,850,1025,1200}, 140, new Vector3(56f, 0f, -75f), new Vector3(56f, 0f, -69f), new Vector3(50f, 0f, -75f), new Vector3(50f, 0f, -69f)));
        streets.add(new Street("Go to Jail", null, StreetType.GOTOJAIL, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(66f, 0f, -67f), new Vector3(72f, 0f, -73f), new Vector3(72f, 0f, -67f), new Vector3(66f, 0f, -73f)));
        streets.add(new Street("Pacific Avenue", MonopolyColors.GREEN, StreetType.PROPERTY, 300, 200, 200, 26, new int[] {130,390,900,1100,1275}, 150, new Vector3(69f, 0f, -50f), new Vector3(75f, 0f, -56f), new Vector3(75f, 0f, -50f), new Vector3(69f, 0f, -56f)));
        streets.add(new Street("North Carolina Avenue", MonopolyColors.GREEN, StreetType.PROPERTY, 300, 200, 200, 26, new int[] {130,390,900,1100,1275}, 150, new Vector3(69f, 0f, -37f), new Vector3(75f, 0f, -43f), new Vector3(75f, 0f, -37f), new Vector3(69f, 0f, -43f)));
        streets.add(new Street("Community Chest", null, StreetType.COMMUNITY_CHEST, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(69f, 0f, -24f), new Vector3(75f, 0f, -30f), new Vector3(75f, 0f, -24f), new Vector3(69f, 0f, -30f)));
        streets.add(new Street("Pennsylvania Avenue", MonopolyColors.GREEN, StreetType.PROPERTY, 0, 0, 0, 0, new int[]{0, 0, 0, 0, 0}, 0, new Vector3(69f, 0f, -10f), new Vector3(75f, 0f, -16f), new Vector3(75f, 0f, -10f), new Vector3(69f, 0f, -16f)));
        streets.add(new Street("Shortline", Color.WHITE, StreetType.STATION, 200, 0, 0, 25, new int[]{25, 50, 100, 200, 0}, 100, new Vector3(69f, 0f, 3f), new Vector3(75f, 0f, -3f), new Vector3(75f, 0f, 3f), new Vector3(69f, 0f, -3f)));
        streets.add(new Street("Chance", null, StreetType.CHANCE, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(69f, 0f, 16f), new Vector3(75f, 0f, 10f), new Vector3(75f, 0f, 16f), new Vector3(69f, 0f, 10f)));
        streets.add(new Street("Park Place", MonopolyColors.DARK_BLUE, StreetType.PROPERTY, 350, 200, 200, 35, new int[] {175,500,1100,1300,1500}, 175, new Vector3(69f, 0f, 29f), new Vector3(75f, 0f, 23f), new Vector3(75f, 0f, 29f), new Vector3(69f, 0f, 23f)));
        streets.add(new Street("Luxury Tax", null, StreetType.SUPER_TAX, 0, 0, 0, 0, new int[] {0,0,0,0,0}, 0, new Vector3(69f, 0f, 43f), new Vector3(75f, 0f, 37f), new Vector3(75f, 0f, 43f), new Vector3(69f, 0f, 37f)));
        streets.add(new Street("Boardwalk", MonopolyColors.DARK_BLUE, StreetType.PROPERTY, 400, 200, 200, 50, new int[] {200,600,1400,1700,2000}, 200, new Vector3(69f, 0f, 56f), new Vector3(75f, 0f, 50f), new Vector3(75f, 0f, 56f), new Vector3(69f, 0f, 50f)));
        House.init();
    }

    public void effect(PlayerComponent component) {
        this.type.effect(component);
    }

    public List<Vector3> getPath(int numberOfFields, PlayerComponent component) {
        List<Vector3> path = new ArrayList<>();
        Street toReturn = this;
        while(numberOfFields < 0) {
            toReturn = toReturn.getPrior();
            path.add(toReturn.getPosition(component));
            numberOfFields++;
        }

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
            GameController.getCurrentPlayerComponent().go();
            return streets.get(0);
        } else {
            return streets.get(id);
        }
    }

    private Street getPrior() {
        int id = streets.indexOf(this);
        id--;
        if (id < 0) {
            return streets.get(streets.size() - 1);
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

    public Color getColorCode() {
        return colorCode;
    }

    public int getBaseRent() {
        return base_rent;
    }

    public int getCost_house() {
        return cost_house;
    }

    public int getCost_hotel() {
        return cost_hotel;
    }

    public int getBase_rent() {
        return base_rent;
    }

    public int[] getRents() {
        return rents;
    }

    public int getHypothek() {
        return hypothek;
    }

    public StreetType getType() {
        return type;
    }


    public PlayerComponent getOwner() {
        if (!isSold()) return null;
        Street currentStreet = GameController.getCurrentPlayerComponent().getCurrentStreet();
        for (Entity entity : GameController.getPlayers()) {
            if (entity.getComponent(PlayerComponent.class).getOwned_streets().contains(currentStreet)) {
                return entity.getComponent(PlayerComponent.class);
            }
        }
        return null;
    }

    public boolean hasAllOfType(PlayerComponent player) {
        List<Street> owned = player.getOwned_streets();
        List<Street> needed = streets.stream()
                .filter(a -> Objects.equals(a.colorCode, this.colorCode))
                .filter(a -> Objects.equals(a.type, this.type))
                .collect(Collectors.toList());
        needed.removeAll(owned);
        return needed.isEmpty();
    }

    public boolean isSold() {
        return this.sold != -1;
    }

    private boolean hasMortgage() {
        return mortgaged;
    }

    public boolean isBuildable() {
        List<Street> owned_streets = GameController.getCurrentPlayerComponent().getOwned_streets();
        List<Street> this_type = new ArrayList<>();
        for (Street owned_street : owned_streets) {
            if(owned_street.getColorCode() == getColorCode()){
                this_type.add(owned_street);
            }
        }

        List<Integer> house_counts = new ArrayList<>();
        for (Street street : this_type) {
            house_counts.add(street.getHouseCount());
        }

        //allEqual
        if(house_counts.stream().distinct().limit(2).count() <= 1){
            return true;
        }

        int threshold = getHouseCount();
        for (Street street : this_type) {
            if(street.getHouseCount() == threshold || street.getHouseCount() - 1 == threshold) {
                continue;
            }else {
                return false;
            }
        }

        return true;
    }

    public void buildHouse() {
        int buildcost = (getHouseCount() == 4) ? getCost_hotel() : getCost_house();
        PlayerComponent player = GameController.getCurrentPlayerComponent();
        player.setMoney(player.getMoney() - buildcost);
        this.houses++;

        House house = House.getHouse(this);

        if(houses == 5){
            for (Entity houseEntity : houseEntities) {
                GameController.getEngine().removeEntity(houseEntity);
            }
            Entity entity = EntityFactory.createHotel(house.getHousePosition(getHouseCount()), house.getRotation());
            houseEntities.add(entity);
            GameController.getEngine().addEntity(entity);
            return;
        }

        Entity entity = EntityFactory.createHouse(house.getHousePosition(getHouseCount()), house.getRotation());
        houseEntities.add(entity);
        GameController.getEngine().addEntity(entity);
    }

    public int getHouseCount() {
        return houses;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setSold() {
        this.sold = GameController.getCurrentPlayerComponent().getId();
    }

    public enum StreetType implements Effect {
        GO() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                if (GameController.getGameSettings().doubleCash) {
                    playerComponent.go();
                }
            }
        },
        PROPERTY() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                // IF owned by other Player
                //      Pay Rent to Other
                // ELSE IF Not owned
                if (!playerComponent.getCurrentStreet().isSold()) {
                    StreetViewTable dialog = new StreetViewTable(playerComponent.getCurrentStreet(), true).create();
                    if(playerComponent.getCurrentStreet().getCost() > playerComponent.getMoney()){
                        dialog.disableBuyOption();
                    }
                    GameController.getGameUi().addActor(dialog);
                } else {
                    if (!playerComponent.getCurrentStreet().hasMortgage()) {
                        playerComponent.payRent();
                    }
                }
            }
        },
        CHANCE() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                ActionCardView view = new ActionCardView(true, true).create();
                GameController.getGameUi().addActor(view);

            }
        },
        COMMUNITY_CHEST() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                ActionCardView view = new ActionCardView(true, false).create();
                GameController.getGameUi().addActor(view);
            }
        },
        TAX() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                playerComponent.setMoney(playerComponent.getMoney() - 100);
            }
        },
        SUPER_TAX() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                playerComponent.setMoney(playerComponent.getMoney() - 200);
            }
        },
        STATION() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                Street currentStreet = playerComponent.getCurrentStreet();

                if (currentStreet.hasMortgage() || playerComponent.getOwned_streets().contains(currentStreet)) {
                    return;
                }
                if (!currentStreet.isSold()) {
                    StreetViewTable dialog = new StreetViewTable(currentStreet, true).create();
                    if(currentStreet.getCost() > playerComponent.getMoney()) {
                        dialog.disableBuyOption();
                    }
                    GameController.getGameUi().addActor(dialog);
                } else {
                        playerComponent.payRent();
                    }
                }
        },
        FACILITY() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                Street currentStreet = playerComponent.getCurrentStreet();
                
                if (currentStreet.hasMortgage() || playerComponent.getOwned_streets().contains(currentStreet)) {
                    return;
                }
                if (!currentStreet.isSold()) {
                    StreetViewTable dialog = new StreetViewTable(currentStreet, true).create();
                    if(currentStreet.getCost() > playerComponent.getMoney()) {
                        dialog.disableBuyOption();
                    }
                    GameController.getGameUi().addActor(dialog);
                } else {
                    int amount = Dice.getRollSum();
                    playerComponent.payRent();
                }



                //      Count Eyes on Dice
                //      if other Player has both Facilites
                //          amount = eyes * 10
                //      else
                //          amount = eyes * 4
                //      Pay Amount x to Other Player
                // ELSE
                //      Show Buy Menu for Facility
            }
        },
        JAIL() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                // Nothing ?
            }
        },
        FREE_PARKING() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                // Nothing ?
            }
        },
        GOTOJAIL() {
            @Override
            public void effect(PlayerComponent playerComponent) {
                // Move Player to Jail
                // playerComponent.goToJail()
            }
        }
    }


}
