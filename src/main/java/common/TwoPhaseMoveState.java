package common;

/**
 * Represents the states of puzzles or two-player games whose moves are
 * described by two objects, i.e., {@code from} and {@code to}. From a user
 * interface-centric perspective, it is suitable for puzzles or two-player games
 * where moves are made from a source to a target location. Thus, a move is
 * specified in two phases, each requiring a selection, e.g., two subsequent
 * mouse clicks.
 *
 * @param <T> represents the type of the source and the target of the moves
 * @param <S> the type of the state itself
 */
public interface TwoPhaseMoveState<T, S extends TwoPhaseMoveState<T, S>>
        extends State<TwoPhaseMoveState.TwoPhaseMove<T>, S> {

    /**
     * {@return whether it is possible to make a move from the argument
     * specified}
     *
     * @param from represents where to move from
     */
    boolean isLegalToMoveFrom(T from);

    /**
     * Represents moves that are described by two objects, i.e., {@code from}
     * and {@code to}.
     *
     * @param from represents where to move from
     * @param to represents where to move to
     * @param <T> represents the type of the source and the target of the moves
     */
    record TwoPhaseMove<T>(T from, T to) {}

}
