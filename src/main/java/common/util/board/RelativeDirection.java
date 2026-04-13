package common.util.board;

import java.util.Arrays;

/**
 * Represents a move on a two-dimensional game board specified by the changes in
 * the row and column coordinates.
 */
public interface RelativeDirection {

    /**
     * {@return the change in the row coordinate when moving to the direction}
     */
    int getRowChange();

    /**
     * {@return the change in the column coordinate when moving to the
     * direction}
     */
    int getColChange();

    /**
     * {@return the direction that corresponds to the coordinate changes
     * specified}
     *
     * @param enumType the class of the direction enum
     * @param rowChange the change in the row coordinate
     * @param colChange the change in the column coordinate
     * @throws IllegalArgumentException if the coordinate changes specified do
     *                                  not correspond to any direction
     * @param <T> the type of the direction
     */
    static <T extends Enum<T> & RelativeDirection> T of(Class<T> enumType, int rowChange, int colChange) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(direction -> direction.getRowChange() == rowChange && direction.getColChange() == colChange)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
