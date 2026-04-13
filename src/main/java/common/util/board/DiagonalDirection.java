package common.util.board;

/**
 * Represents the four diagonal directions.
 */
public enum DiagonalDirection implements RelativeDirection {

    UP_LEFT(-1, -1),
    UP_RIGHT(-1, 1),
    DOWN_RIGHT(1, 1),
    DOWN_LEFT(1, -1);

    private final int rowChange;
    private final int colChange;

    DiagonalDirection(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    @Override
    public int getRowChange() {
        return rowChange;
    }

    @Override
    public int getColChange() {
        return colChange;
    }

    /**
     * {@return the direction that corresponds to the coordinate changes
     * specified}
     *
     * @param rowChange the change in the row coordinate
     * @param colChange the change in the column coordinate
     * @throws IllegalArgumentException if the coordinate changes specified do
     *                                  not correspond to any direction
     */
    public static DiagonalDirection of(int rowChange, int colChange) {
        return RelativeDirection.of(DiagonalDirection.class, rowChange, colChange);
    }

}
