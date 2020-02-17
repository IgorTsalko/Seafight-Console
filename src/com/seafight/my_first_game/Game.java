package com.seafight.my_first_game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    private static Game seaFight = new Game();

    public static void main(String[] args) {
        seaFight.startGame();
    }

    private void startGame() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.print("Enter player name, please: ");
            Player user = new Player(reader.readLine());
            Robot enemy = new Robot("Enemy");
            System.out.println("Start game!");

            System.out.println("Player ships " + user.getPlayerName() + ":");
            user.getField().showShips();

            System.out.println("Enemy ships:");
            enemy.getField().showShips();

            System.out.println("Player " + user.getPlayerName() + " shoots first: ");
            game(user, enemy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void game(Player user, Robot enemy) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(user.getPlayerName() + " your turn: ");
            String shotOfUser = reader.readLine();
            user.getField().getAvailableShots().remove(shotOfUser);
            enemy.checkShot(shotOfUser);

            String shotOfEnemy = enemy.shotOfRobot();
            System.out.println("Now shoots the enemy, cell " + shotOfEnemy);
            enemy.getField().getAvailableShots().remove(shotOfEnemy);
            user.getField().checkHit(shotOfEnemy);

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
