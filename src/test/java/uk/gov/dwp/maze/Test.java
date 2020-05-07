package uk.gov.dwp.maze;


import static java.lang.Thread.sleep;

import java.util.Random;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        int[][] maze = new int[10][19];

        System.out.printf(String.valueOf(maze.length));
        System.out.printf(String.valueOf(maze[0].length));
    }
}
