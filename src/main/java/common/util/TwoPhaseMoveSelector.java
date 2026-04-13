package common.util;

import common.TwoPhaseMoveState;

/**
 * Utility class to determine the next move in two-phase move-type puzzles or
 * two-player games. It serves to select a source and a target that together
 * make up a move.
 *
 * @param <T> represents the moves that can be applied to the states
 */
public class TwoPhaseMoveSelector<T> {

    /**
     * Represents the current selection phase.
     */
    public enum Phase {
        SELECT_FROM,
        SELECT_TO,
        READY_TO_MOVE
    }

    private final TwoPhaseMoveState<T, ?> state;
    protected Phase phase;
    private boolean invalidSelection;
    private T from;
    private T to;

    /**
     * Creates a {@code TwoPhaseMoveSelector} object to determine the next move
     * in the state specified.
     *
     * @param state the state in which the next move is to be made
     */
    public TwoPhaseMoveSelector(TwoPhaseMoveState<T, ?> state) {
        this.state = state;
        phase = Phase.SELECT_FROM;
        invalidSelection = false;
    }

    /**
     * {@return the current selection phase}
     */
    public final Phase getPhase() {
        return phase;
    }

    /**
     * Sets the current selection phase. The method is provided to be overridden
     * by subclasses.
     *
     * @param phase the current selection phase
     */
    protected void setPhase(Phase phase) {
        this.phase = phase;
    }

    /**
     * {@return whether the move is ready to be made}
     */
    public final boolean isReadyToMove() {
        return phase == Phase.READY_TO_MOVE;
    }

    /**
     * Selects the action specified to be either the source or the target of the
     * move, respectively. However, no selection is made if the selection is
     * invalid.
     *
     * @param action the action to be selected
     */
    public final void select(T action) {
        switch (phase) {
            case SELECT_FROM -> selectFrom(action);
            case SELECT_TO -> selectTo(action);
            case READY_TO_MOVE -> throw new IllegalStateException();
        }
    }

    protected void selectFrom(T from) {
        if (state.isLegalToMoveFrom(from)) {
            this.from = from;
            setPhase(Phase.SELECT_TO);
            invalidSelection = false;
        } else {
            invalidSelection = true;
        }
    }

    protected void selectTo(T to) {
        if (state.isLegalMove(new TwoPhaseMoveState.TwoPhaseMove<>(from, to))) {
            this.to = to;
            setPhase(Phase.READY_TO_MOVE);
            invalidSelection = false;
        } else {
            invalidSelection = true;
        }
    }

    /**
     * {@return the source selected} If the move is not yet ready to be made,
     * then an {@link IllegalStateException} is thrown.
     */
    public final T getFrom() {
        if (phase == Phase.SELECT_FROM) {
            throw new IllegalStateException();
        }
        return from;
    }

    /**
     * {@return the target selected} If the move is not yet ready to be made,
     * then an {@link IllegalStateException} is thrown.
     */
    public final T getTo() {
        if (phase == Phase.SELECT_FROM || phase == Phase.SELECT_TO) {
            throw new IllegalStateException();
        }
        return to;
    }

    /**
     * {@return whether the last selection (i.e, for the source or the target,
     * respectively) was invalid}
     */
    public final boolean isInvalidSelection() {
        return invalidSelection;
    }

    /**
     * Makes the move selected. If the move is not yet ready to be made, then an
     * {@link IllegalStateException} is thrown.
     */
    public final void makeMove() {
        if (phase != Phase.READY_TO_MOVE) {
            throw new IllegalStateException();
        }
        state.makeMove(new TwoPhaseMoveState.TwoPhaseMove<>(from, to));
        reset();
    }

    /**
     * Resets the selection, i.e., resets both the source and the target
     * selected.
     */
    public final void reset() {
        from = null;
        to = null;
        setPhase(Phase.SELECT_FROM);
        invalidSelection = false;
    }

}
