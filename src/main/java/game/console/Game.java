package game.console;

import game.State;

import java.util.function.Function;

/**
 * Conducts a two-player game whose moves are described by a single object.
 *
 * @param <T> represents the moves that can be applied to the states
 * @param <S> represents the states of the game
 */
public class Game<T, S extends State<T, S>> extends AbstractGame<T, S> {

    /**
     * Creates a {@code Game} instance to conduct a two-player game whose moves
     * are described by a single object on the console.
     *
     * @param state the state from which the game is started
     * @param parser a function that converts a line read from the console to a
     *               move
     * @throws AssertionError if the console is not available
     */
    public Game(S state, Function<String, T> parser) {
        super(state, parser);
    }

    @Override
    protected void makeMoveIfPossible(T move) {
        if (state.isLegalMove(move)) {
            state.makeMove(move);
            printState();
        }
    }

}
