
import java.util.Iterator;

/**
 * Knight.java
 * 
 */

public class Knight implements Application {

    // fields

    protected byte[][] grid;
    protected Position start;
    protected static int finish;
    private byte counter;

    // methods

    /**
     * Constructor initiates grid, start, finish and counter fields
     * 
     * @param grid size and starting position
     */
    public Knight(int x, int y, Position pos) {
        grid = new byte[x][y];
        start = pos;
        finish = (x * y);
        counter = 0;

    }

    /**
     * getStart() method retrieves starting position
     * 
     * @return starting position as object of type Position
     */
    public Position getStart() {
        return start;
    }

    /**
     * getGrid() method returns a copy of the current grid as an array of bytes
     * 
     * @return 2-dimensional array that holds copy of the maze configuration
     */
    public byte[][] getGrid() {
        byte[][] gridCopy = new byte[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                gridCopy[i][j] = grid[i][j];

        return gridCopy;
    }

    /**
     * isOK() method determines if a given position is legal and not a dead end
     *
     * @param pos - the given position
     *
     * @return true if pos is a legal position and not a dead end
     */
    public boolean isOK(Position pos) {

        return ((pos.getRow() >= 0 && pos.getRow() < grid.length)
                && (pos.getColumn() >= 0 && pos.getColumn() < grid[0].length)
                && (grid[pos.getRow()][pos.getColumn()] == 0));

    }

    /**
     * Indicates that a given position is possibly on a path to a goal
     *
     * @param pos the position that has been marked as possibly being on a path to a
     *            goal
     */
    public void markAsPossible(Position pos) {

        counter++;
        grid[pos.getRow()][pos.getColumn()] = counter;

    }

    /**
     * isGoal() method indicates whether a given position has reached the goal
     *
     * @param pos the position that may or may not be a goal position
     *
     * @return true if pos is a goal position; false otherwise
     */
    public boolean isGoal(Position pos) {

        return grid[pos.getRow()][pos.getColumn()] == finish;
    }

    /**
     * markAsDeadEnd() method indicates whether a given position is not on any path
     * to a goal position.
     *
     * @param pos the position that has been marked as not being on any path to a
     *            goal position.
     */
    public void markAsDeadEnd(Position pos) {

        counter--;
        grid[pos.getRow()][pos.getColumn()] = 0;

    }

    /**
     * Converts this Application object into a String object
     *
     * @return the String representation of the grid
     */
    public String toString() {
        String result = "\n";

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++)
                if (grid[row][column] < 10)
                    result += String.valueOf("0" + grid[row][column]) + ' ';
                else
                    result += String.valueOf(grid[row][column]) + ' ';
            result += "\n";
        }

        return result;
    }

    /**
     * Produces an Iterator object, over elements of type Position, that starts at a
     * given position.
     *
     * @param pos - the position the Iterator object starts at
     *
     * @return an Iterator object
     */
    public Iterator<Position> iterator(Position pos) {
        return new KnightIterator(pos);
    }

    // inner class KnightIterator implements Iterator
    protected class KnightIterator implements Iterator<Position> {

        // fields

        protected int row, column, count;
        protected static final int MAX_MOVES = 8;

        // methods

        /**
         * Inner class Constructor initializes this MazeIterator object to start at a
         * given position.
         *
         * @param pos the position the Iterator objects starts at.
         */
        public KnightIterator(Position pos) {
            row = pos.getRow();
            column = pos.getColumn();
            count = 0;
        }

        /**
         * hasNext() method determines if this MazeIterator object can advance to
         * another position
         *
         * @return true if the MazeIterator object can advance and false otherwise
         */
        public boolean hasNext() {

            return count < MAX_MOVES;
        }

        /**
         * Advances this MazeIterator object to the next position
         *
         * @return the position advanced to.
         */
        public Position next() {
            Position nextPosition = new Position();
            switch (count++) {
            case 0:
                nextPosition = new Position(row - 2, column + 1); // move up 2 rows 1 column to the right
                break;
            case 1:
                nextPosition = new Position(row - 1, column + 2); // move up 1 row and 2 columns to the right
                break;
            case 2:
                nextPosition = new Position(row + 1, column + 2); // move down 1 row and 2 columns to the right
                break;
            case 3:
                nextPosition = new Position(row + 2, column + 1); // move down 2 rows and 1 column to the right
                break;
            case 4:
                nextPosition = new Position(row + 2, column - 1); // move down 2 rows and 1 column to the left
                break;
            case 5:
                nextPosition = new Position(row + 1, column - 2); // move down 1 row and 2 columns to the left
                break;
            case 6:
                nextPosition = new Position(row - 1, column - 2); // move up 1 row and 2 columns to the left
                break;
            case 7:
                nextPosition = new Position(row - 2, column - 1); // move up 2 rows and 1 column to the left
                break;
            }

            return nextPosition;
        }

    }
}
