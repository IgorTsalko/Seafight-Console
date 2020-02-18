package com.seafight.my_first_game;

import java.util.ArrayList;
import java.util.List;

public class FieldOfPlayer {
    private String alphabet = "ABCDEFGHIJ";
    private ArrayList<String> availableShots = initializeCells();
    private Ship[] ships = initializeShips();
    private String deletedCells = "";
    private int amountOfShip = 10;

    public ArrayList<String> getAvailableShots() {
        return availableShots;
    }

    public int getAmountOfShip() {
        return amountOfShip;
    }

    void checkHit(String shot) {
        String value = "Miss!";
        for (Ship ship : ships) {
            if (ship.getCellsOfShip().contains(shot)) {
                ship.getCellsOfShip().remove(shot);
                if (ship.getCellsOfShip().size() == 0) {
                    value = "Hit! " + ship.getName() + " is sunk!";
                    amountOfShip--;
                } else {
                    value = "Hit! " + ship.getName() + " is wrecked!";
                }
                deletedCells += shot + " ";
                break;
            }
        }
        System.out.println(value);
    }

    private ArrayList<String> initializeCells() {
        ArrayList<String> arrayList = new ArrayList<>();
        int sizeField = 10;
        for (int i = 0; i < alphabet.length(); i++) {
            for (int j = 1; j <= sizeField; j++) {
                String letter = String.valueOf(alphabet.charAt(i));
                arrayList.add(letter + j);
            }
        }
        return arrayList;
    }

    private Ship[] initializeShips() {
        Ship[] ships = new Ship[10];
        String[] names = {"single deck ship", "double deck ship", "three deck ship", "four deck ship"};
        int count = 0;
        for (int i = 4; i > 0; i--) {
            for (int j = i; j < 5; j++) {
                ships[count++] = (new Ship(names[i-1], i));
            }
        }
        placementShips(ships);
        return ships;
    }

    private void placementShips(Ship[] ships) {
        ArrayList<String> availableCells = initializeCells();

        for (int i = 0; i < ships.length; i++) {
            String randomPoint = availableCells.get((int) (Math.random() * availableCells.size()));
            int shipSize = ships[i].getSize();
            ArrayList<ArrayList<String>> availablePositions = new ArrayList<>();
            ArrayList<String> optionOne = new ArrayList<>();
            ArrayList<String> optionTwo = new ArrayList<>();
            ArrayList<String> optionThree = new ArrayList<>();
            ArrayList<String> optionFour = new ArrayList<>();

            if (shipSize == 1) {
                removeInaccessibleCell(randomPoint, availableCells);
                ships[i].setCellOfShip(randomPoint);
                continue;
            }

            for (int j = 1; j < shipSize; j++) {
                String cell = moveCellUp(randomPoint, j);
                if (availableCells.contains(cell)) optionOne.add(cell);

                cell = moveCellRight(randomPoint, j);
                if (availableCells.contains(cell)) optionTwo.add(cell);

                cell = moveCellDown(randomPoint, j);
                if (availableCells.contains(cell)) optionThree.add(cell);

                cell = moveCellLeft(randomPoint, j);
                if (availableCells.contains(cell)) optionFour.add(cell);
            }

            if (optionOne.size() == shipSize - 1) availablePositions.add(optionOne);
            if (optionTwo.size() == shipSize - 1) availablePositions.add(optionTwo);
            if (optionThree.size() == shipSize - 1) availablePositions.add(optionThree);
            if (optionFour.size() == shipSize - 1) availablePositions.add(optionFour);

            if (availablePositions.size() != 0) {
                int randomPosition = (int) (Math.random() * (availablePositions.size()));
                ArrayList<String> tempList = availablePositions.get(randomPosition);
                tempList.add(randomPoint);

                for (String cell : tempList) {
                    removeInaccessibleCell(cell, availableCells);
                    ships[i].setCellOfShip(cell);
                }
            } else
                i--;
        }
    }

    private void removeInaccessibleCell(String cell, ArrayList<String> availableCells) {
        int indexLetter = alphabet.indexOf(cell.substring(0,1));
        int indexNum = Integer.parseInt(cell.substring(1));
        availableCells.remove(cell);

        if (indexLetter > 0) {
            availableCells.remove(alphabet.charAt(indexLetter - 1) + String.valueOf(indexNum));
            availableCells.remove(alphabet.charAt(indexLetter - 1) + String.valueOf(indexNum - 1));
            availableCells.remove(alphabet.charAt(indexLetter - 1) + String.valueOf(indexNum + 1));
        }

        if (indexLetter < 9) {
            availableCells.remove(alphabet.charAt(indexLetter + 1) + String.valueOf(indexNum));
            availableCells.remove(alphabet.charAt(indexLetter + 1) + String.valueOf(indexNum - 1));
            availableCells.remove(alphabet.charAt(indexLetter + 1) + String.valueOf(indexNum + 1));
        }

        if (indexNum > 1) {
            availableCells.remove(alphabet.charAt(indexLetter) + String.valueOf(indexNum - 1));
        }

        if (indexNum < 10) {
            availableCells.remove(alphabet.charAt(indexLetter) + String.valueOf(indexNum + 1));
        }
    }

    private String moveCellUp(String cell, int quantity) {
        int numberOfCells = Integer.parseInt(cell.substring(1));
        cell = cell.substring(0, 1) + (numberOfCells - quantity);
        return cell;
    }

    private String moveCellRight(String cell, int quantity) {
        String value = cell;
        int letterOfCells = alphabet.indexOf(value.substring(0, 1));
        if (9 - letterOfCells >= quantity) {
            value = alphabet.charAt(letterOfCells + quantity) + cell.substring(1);
        } else
            value = "null";
        return value;
    }

    private String moveCellDown(String cell, int quantity) {
        int numberOfCells = Integer.parseInt(cell.substring(1));
        cell = cell.substring(0, 1) + (numberOfCells + quantity);
        return cell;
    }

    private String moveCellLeft(String cell, int quantity) {
        String value = cell;
        int letterOfCells = alphabet.indexOf(value.substring(0, 1));
        if (letterOfCells - quantity > 0) {
            value = alphabet.charAt(letterOfCells - quantity) + cell.substring(1);
        } else
            value = "null";
        return value;
    }

    public void printShips() {
        printShips(availableShots);
    }

    public void printShips(List<String> availableShots) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j < 10; j++) {
                String cell = String.valueOf(alphabet.charAt(j)) + i;
                String value;

                if (!availableShots.contains(cell) && deletedCells.contains(cell)) {
                    value = "[X]";
                } else if (!availableShots.contains(cell) && !deletedCells.contains(cell)) {
                    value = ".X.";
                } else {
                    value = "...";
                    for (Ship ship : ships) {
                        if (ship.getCellsOfShip().contains(cell)) {
                            value = "[ ]";
                            break;
                        }
                    }
                }
                System.out.print(value);
            }
            System.out.println();
        }
//        showNumbersOfAllCells();

        System.out.println();
    }

    private void showNumbersOfAllCells() {
        for (Ship sh : ships) {
            System.out.print(sh.getName() + " with cells: ");
            System.out.print(sh + " Size: ");
            System.out.println(sh.getSize());
        }
    }
}
