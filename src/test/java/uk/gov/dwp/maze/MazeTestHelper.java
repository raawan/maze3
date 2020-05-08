package uk.gov.dwp.maze;

import java.util.Random;

public class MazeTestHelper {

    private MazeTestHelper() {}

    /*
    these maze do not guarantee that a solution exist!
     */
    static int[][] createRandomMaze(int height, int width) {
        int maze[][] = new int[height][width];
        Random random = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = random.nextInt(2);
            }
        }
        createRandomEntryAndExitPoint(height,width,maze);
        return maze;
    }

    private static void createRandomEntryAndExitPoint(int height, int width, int[][] maze) {
        Random random = new Random();
        //add a random entry point
        final int entryPointXcoordinate = random.nextInt(height);
        final int entryPointYcoordinate = random.nextInt(width);
        maze[entryPointXcoordinate][entryPointYcoordinate] = 2;
        //add a random exit point
        while (true) {
            final int exitPointXcoordinate = random.nextInt(height);
            final int exitPointYcoordinate = random.nextInt(width);
            if (exitPointXcoordinate != entryPointXcoordinate ||
                    exitPointYcoordinate != entryPointYcoordinate) {
                maze[exitPointXcoordinate][exitPointYcoordinate] = 3;
                break;
            }
        }
    }

    static void printMaze(int[][] maze) {
        for (int i = maze.length - 1 ; i >= 0 ; i--) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.printf(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
}
