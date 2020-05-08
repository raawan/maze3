package uk.gov.dwp.maze;


import static java.lang.Thread.sleep;

import java.util.Random;

public class DeleteThisFile {

    /*
    MAZE :
        0 0 2 1 0
        0 1 1 0 1
        0 1 3 0 1
        0 0 0 1 0

     DIRECTION :
            N
          W +  E
            S
     */

    static int[][] newMaze = {
            {0, 0, 0, 1, 0},
            {0, 1, 3, 0, 1},
            {0, 1, 1, 0, 1},
            {0, 0, 2, 1, 0}
    };

    public static void main(String[] args) throws InterruptedException {
        int x = 1;
        int y = 2;

        System.out.println(newMaze[x][y]);

        System.out.println(newMaze[x+1][y]); //   //up
        System.out.println(newMaze[x-1][y]); //  //down

        System.out.println(newMaze[x][y+1]); // right
        System.out.println(newMaze[x][y-1]); // left
    }
}
