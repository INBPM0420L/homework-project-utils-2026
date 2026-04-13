package puzzle;

/**
 * Represents the state of a puzzle to be solved.
 *
 * @param <T> represents the moves that can be applied to the states
 * @param <S> the type of the state itself
 */
public interface State<T, S extends State<T, S>> extends common.State<T, S> {

    /**
     * {@return whether the puzzle is solved}
     */
    boolean isSolved();

}
