package com.seafight.my_first_game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    public static void main(String[] args) {
        try {
            startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void startGame() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Пожалуйста введите имя игрока: ");
        Player user = new Player(reader.readLine());
        Robot enemy = new Robot("Противник");
        System.out.println("Старт игры!");
//        System.out.println("Корабли игрока " + user.getPlayerName() + ":");
//        user.getField().showShips();
//        System.out.println("");
        System.out.println("Корабли противника:");
        enemy.getField().showShips();
        System.out.println("Игрок " + user.getPlayerName() + " стреляет первым: ");

        game(user, enemy);
        reader.close();
    }

    static void game(Player user, Robot enemy) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(user.getPlayerName() + " ваш ход: ");
            String shotOfUser = reader.readLine();
            user.getField().getAvailableShots().remove(shotOfUser);
            enemy.checkShot(shotOfUser);

            String shotOfEnemy = enemy.shotOfRobot();
            System.out.println("Теперь стреляет противник, ячейка " + shotOfEnemy);
            enemy.getField().getAvailableShots().remove(shotOfEnemy);
            user.getField().checkHit(shotOfEnemy);

            if (user.getField().getAmountOfShip() == 0) {
                System.out.println("Игра окончена! Противник победил!");
                break;
            }

            if (enemy.getField().getAmountOfShip() == 0) {
                System.out.println("Игра окончена! Игрок " + user.getPlayerName() + " победил!");
                break;
            }
        }
    }
}
