package com.seafight.my_first_game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    private static Game seaFight = new Game();

    public static void main(String[] args) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.print("Enter player name, please: ");
            Player user = new Player(reader.readLine());
            Robot enemy = new Robot("Enemy");
            System.out.println("Start game!\n");

            System.out.println("Ships player " + user.getPlayerName() + ":");
            user.getField().printShips();

            System.out.println("Enemy ships:");
            enemy.getField().printShips();

            System.out.println("Player " + user.getPlayerName() + " shoots first: ");
            seaFight.startGame(user, enemy, reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startGame(Player user, Robot enemy, BufferedReader reader) {
        while (true) {
            user.shot(enemy, reader);
            enemy.shot(user, reader);

            if (user.getField().getAmountOfShip() == 0) {
                System.out.println("Game over! The enemy won!");
                break;
            }

            if (enemy.getField().getAmountOfShip() == 0) {
                System.out.println("Game over! Player " + user.getPlayerName() + " won!");
                break;
            }
        }
    }
}
