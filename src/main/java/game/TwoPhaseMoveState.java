package game;

import common.TwoPhaseMoveState.TwoPhaseMove;

/**
 * Represents the state of a game whose moves are described by two objects,
 * i.e., {@code from} and {@code to}. From a user interface-centric perspective,
 * it is suitable for games where moves are made from a source location to a
 * target location. Thus, a move is specified in two phases, each requiring a
 * selection, e.g., two subsequent mouse clicks.
 *
 * @param <T> represents the moves that can be applied to the states
 * @param <S> the type of the state itself
 */
public interface TwoPhaseMoveState<T, S extends TwoPhaseMoveState<T, S>> extends State<TwoPhaseMove<T>, S>,
        common.TwoPhaseMoveState<T, S> {
}
