
import java.util.*;

/**
 * KnightUser.java
 * 
 */
public class KnightUser {
    // main method invokes the run() method
    public static void main(String[] args) {

        new KnightUser().run();

    }

    // run() method reads input from keyboard and runs search
    public void run() {

        // String describe state of search
        final String INITIAL_STATE = "\nThe initial grid is as follows:\n";

        final String START_INVALID = "The starting position is invalid.";

        final String FINAL_STATE = "The final grid is as follows:\n";

        final String SUCCESS = "\nA solution has been found:\n";

        final String FAILURE = "\nThere is no solution:";

        // initialize variables to hold grid size and starting location
        int gridX = 0;
        int gridY = 0;
        int startX = 0;
        int startY = 0;

        // create a scanner object to collect keyboard input
        Scanner input = new Scanner(System.in);

        // ask user to enter size of grid and starting positions
        while (true) {
            try {

                System.out.println("Enter grid size ");
                System.out.print("Rows: ");
                gridX = input.nextInt();
                System.out.print("Columns: ");
                gridY = input.nextInt();
                System.out.println("Enter initial position ");
                System.out.print("Row: ");
                startX = input.nextInt();
                System.out.print("Column: ");
                startY = input.nextInt();
                break;

            } catch (InputMismatchException e) {
                System.out.println("That does not look like a number ");
                input.nextLine();
            }
        }
        // create Position and Knight objects with the user input as parameter
        Position pos = new Position(startX, startY);
        Knight game = new Knight(gridX, gridY, pos);

        try {
            // print inital state of grid
            System.out.println(INITIAL_STATE + game);

            // if initial position is valid begin search
            if (!game.isOK(game.getStart())) {
                System.out.println(START_INVALID);

            } else {
                if (searchKnight(game)) {
                    System.out.println(SUCCESS);
                    System.out.println(FINAL_STATE + game);
                } else {
                    System.out.println(FAILURE);
                    System.out.println(FINAL_STATE + game);
                }
            }
        } catch (RuntimeException e) {
            System.out.print("\n" + e);
            System.out.println(FINAL_STATE + game);

        }
    }

    // searchKnight() method runs backtracking
    public boolean searchKnight(Knight k) {
        Position start = k.getStart();
        k.markAsPossible(start);
        BackTrack backTrack = new BackTrack(k);
        if (k.isGoal(start) || backTrack.tryToReachGoal(start))
            return true;
        k.markAsDeadEnd(start);
        return false;
    }

}
