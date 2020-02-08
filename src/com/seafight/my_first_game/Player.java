package com.seafight.my_first_game;

public class Player {
    private String playerName;
    private FieldOfPlayer field = new FieldOfPlayer();
    private String alphabet = "АБВГДЕЖЗИК";

    public String getPlayerName() {
        return playerName;
    }

    public FieldOfPlayer getField() {
        return field;
    }

    public Player(String name) {
        this.playerName = name;
    }

    public void checkShot(String shot) {
        shot = shot.toUpperCase();
        String letter = shot.substring(0, 1);
        int number = Integer.parseInt(shot.substring(1));
        if (alphabet.contains(letter) || number < 0 || number > 10) {
            field.checkHit(shot);
        }
    }
}
