package com.seafight.my_first_game;

import java.io.BufferedReader;
import java.io.IOException;

public class Player {
    private String playerName;
    private FieldOfPlayer field = new FieldOfPlayer();

    public Player(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public FieldOfPlayer getField() {
        return field;
    }

    public void shot(Player player, BufferedReader reader) {
        try {
            System.out.print(playerName + " your turn: ");
            String playerShot = reader.readLine().toUpperCase();
            String letter = playerShot.substring(0, 1);
            int number = -1;

            try {
                number = Integer.parseInt(playerShot.substring(1));
            } catch (Exception e) {
                System.out.println("Attention! Invalid cell!");
                shot(player, reader);
            }

            String alphabet = "ABCDEFGHIJ";
            if (alphabet.contains(letter) && number > 0 && number <= 10) {
                getField().getAvailableShots().remove(playerShot);
                player.checkShot(playerShot);
                player.getField().printShips(getField().getAvailableShots());
            } else {
                System.out.println("Attention! Invalid cell!");
                shot(player, reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkShot(String shot) {
        field.checkHit(shot);
    }
}
