package puzzle;

import common.TwoPhaseMoveState.TwoPhaseMove;

/**
 * Represents the state of a puzzle whose moves are described by two objects,
 * i.e., {@code from} and {@code to}. From a user interface-centric perspective,
 * it is suitable for puzzles where moves are made from a source to a
 * target location. Thus, a move is specified in two phases, each requiring a
 * selection, e.g., two subsequent mouse clicks.
 *
 * @param <T> represents the type of the source and the target of the moves
 * @param <S> the type of the state itself
 */
public interface TwoPhaseMoveState<T, S extends TwoPhaseMoveState<T, S>> extends State<TwoPhaseMove<T>, S>,
        common.TwoPhaseMoveState<T, S> {
}
