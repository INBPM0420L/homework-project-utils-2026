package common.util.board;

/**
 * Represents the four cardinal directions.
 */
public enum CardinalDirection implements RelativeDirection {

    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);

    private final int rowChange;
    private final int colChange;

    CardinalDirection(int rowChange, int colChange) {
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
    public static CardinalDirection of(int rowChange, int colChange) {
        return RelativeDirection.of(CardinalDirection.class, rowChange, colChange);
    }

}
