package common.util.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides static helper methods to work with game boards. The rows and columns
 * of the game board are indexed starting from zero.
 */
public class Board {

    /**
     * {@return the list of all positions of a game board of the given size in
     * row-major order}
     *
     * @param rows the total number of rows
     * @param cols the total number of columns
     */
    public static List<Position> generatePositions(int rows, int cols) {
        var positions = new ArrayList<Position>(rows * cols);
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < cols; j++) {
                positions.add(new Position(i, j));
            }
        }
        return positions;
    }

    /**
     * {@return the neighbors of the specified position that are on the board,
     * in all directions of the type specified}
     *
     * @param position the position whose neighbors are returned
     * @param rows the total number of rows
     * @param cols the total number of columns
     * @param enumType the class of the direction enum
     * @param <T> the type of the direction
     */
    public static <T extends Enum<T> & RelativeDirection> List<Position> getNeighbors(Position position, int rows, int cols, Class<T> enumType) {
        var neighbors = new ArrayList<Position>();
        for (var direction : enumType.getEnumConstants()) {
            var neighbor = move(position, direction);
            if (isOnBoard(neighbor, rows, cols)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    /**
     * {@return the cardinal neighbors of the specified position that are on the
     * board}
     *
     * @param position the position whose neighbors are returned
     * @param rows the total number of rows
     * @param cols the total number of columns
     */
    public static List<Position> getCardinalNeighbors(Position position, int rows, int cols) {
        return getNeighbors(position, rows, cols, CardinalDirection.class);
    }

    /**
     * {@return the diagonal neighbors of the specified position that are on the
     * board}
     *
     * @param position the position whose neighbors are returned
     * @param rows the total number of rows
     * @param cols the total number of columns
     */
    public static List<Position> getDiagonalNeighbors(Position position, int rows, int cols) {
        return getNeighbors(position, rows, cols, DiagonalDirection.class);
    }

    /**
     * {@return the compass neighbors of the specified position that are on the
     * board}
     *
     * @param position the position whose neighbors are returned
     * @param rows the total number of rows
     * @param cols the total number of columns
     */
    public static List<Position> getCompassNeighbors(Position position, int rows, int cols) {
        return getNeighbors(position, rows, cols, CompassDirection.class);
    }

    /**
     * {@return whether the two specified positions are neighbors in any
     * direction of the type specified}
     *
     * @param p1 the first position
     * @param p2 the second position
     * @param enumType the class of the direction enum
     * @param <T> the type of the direction
     */
    public static <T extends Enum<T> & RelativeDirection> boolean isNeighbor(Position p1, Position p2, Class<T> enumType) {
        return Arrays.stream(enumType.getEnumConstants())
                .anyMatch(direction -> p1.row() + direction.getRowChange() == p2.row()
                        && p1.col() + direction.getColChange() == p2.col()
        );
    }

    /**
     * {@return whether the two specified positions are cardinal neighbors}
     *
     * @param p1 the first position
     * @param p2 the second position
     */
    public static boolean isCardinalNeighbor(Position p1, Position p2) {
        return isNeighbor(p1, p2, CardinalDirection.class);
    }

    /**
     * {@return whether the two specified positions are diagonal neighbors}
     *
     * @param p1 the first position
     * @param p2 the second position
     */
    public static boolean isDiagonalNeighbor(Position p1, Position p2) {
        return isNeighbor(p1, p2, DiagonalDirection.class);
    }

    /**
     * {@return whether the two specified positions are compass neighbors}
     *
     * @param p1 the first position
     * @param p2 the second position
     */
    public static boolean isCompassNeighbor(Position p1, Position p2) {
        return isNeighbor(p1, p2, CompassDirection.class);
    }

    /**
     * {@return whether the specified position is on the game board}
     *
     * @param position the position to check
     * @param rows the total number of rows
     * @param cols the total number of columns
     */
    public static boolean isOnBoard(Position position, int rows, int cols) {
        return 0 <= position.row() && position.row() < rows
                && 0 <= position.col() && position.col() < cols;
    }

    /**
     * {@return the position obtained by moving from the specified position in
     * the given direction} The method does not check whether the resulting
     * position is on the game board.
     *
     * @param position the starting position
     * @param direction the direction into which a move is made
     */
    public static Position move(Position position, RelativeDirection direction) {
        return new Position(position.row() + direction.getRowChange(), position.col() + direction.getColChange());
    }

}
