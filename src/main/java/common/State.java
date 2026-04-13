package common;

import java.util.Set;

import common.util.Copyable;

/**
 * Represents functionality shared between the state of puzzles and two-player
 * games.
 *
 * @param <T> represents the moves that can be applied to the states
 * @param <S> the type of the state itself
 */
public interface State<T, S extends State<T, S>> extends Copyable<S> {

    /**
     * {@return whether the move provided can be applied to the state}
     *
     * @param move represents the move to be made
     */
    boolean isLegalMove(T move);

    /**
     * Applies the move provided to the state. This method should be called if
     * and only if {@link #isLegalMove(Object)} returns {@code true}.
     *
     * @param move represents the move to be made
     */
    void makeMove(T move);

    /**
     * {@return the set of all moves that can be applied to the state}
     */
    Set<T> getLegalMoves();

    @Override
    S copy();

}
