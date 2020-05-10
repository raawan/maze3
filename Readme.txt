ASSUMPTIONS:

1)     The directions are visualised as follows by assuming North towards UP
        /*
               N
             W +  E
               S
        */

2)     /*
        There isn't a clear requirement on what move forward means.
        I am assuming moving a step ahead in a given direction where explorer is facing given
        an empty space or exit is available.
        */

3)    /*
       This availableMovementOptions() method determines only one step movement options instead of the whole path
       The assumption made here is that if its an exit point or a space present next its a valid move
       No diagonal movements are considered as movement options
        */

4)  Assuming maze is of rectangular size and all values are either filled with 0,1,2 or 3
    0 for space ; 1 for wall ; 2 for entry point; 3 for exit

5)  inFrontOfMe() method will return -1 if the explorer is on border and facing away from Maze

6)  The code to solve the maze is not written, the intent was to acheive the acceptance criteria as mentioned in the requirement

7) I have provided a utility method called ExplorerHelper.printMaze(), just in case if you want to see how a maze state looks like
    {This is not used anywhere, so should be removed before moving to production}