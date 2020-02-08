package com.seafight.my_first_game;

import java.util.ArrayList;

public class Ship {
    private String name;
    private ArrayList<String> cellsOfShip = new ArrayList<>();
    private int size;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void setCellOfShip(String cell) {
        cellsOfShip.add(cell);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<String> getCellsOfShip() {
        return cellsOfShip;
    }

    @Override
    public String toString() {
        return cellsOfShip.toString();
    }

}
