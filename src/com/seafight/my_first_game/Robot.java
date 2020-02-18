package com.seafight.my_first_game;

import java.io.BufferedReader;

public class Robot extends Player {

    /**
     * Create a robot, it can shoot random
     * @param name Player name
     */
    public Robot(String name) {
        super(name);
    }

    /**
     * Random shoot
     * @return random cell
     */
    public String shotOfRobot() {
        int randomCell = (int) (Math.random() * getField().getAvailableShots().size());
        return getField().getAvailableShots().get(randomCell);
    }

    @Override
    public void shot(Player player, BufferedReader reader) {
        String enemyShot = shotOfRobot();
        System.out.println("Shoots the enemy, cell " + enemyShot);
        getField().getAvailableShots().remove(enemyShot);
        getField().getAvailableShots().remove(enemyShot);
        player.getField().checkHit(enemyShot);
        player.getField().printShips(getField().getAvailableShots());
    }
}
