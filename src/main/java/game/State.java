package game;

/**
 * Represents the state of a two-player game whose moves are described by a
 * single object. From a user interface-centric perspective, it is suitable for
 * games whose moves can be specified with a single selection, such as a single
 * mouse click.
 *
 * @param <T> represents the moves that can be applied to the states
 * @param <S> the type of the state itself
 */
public interface State<T, S extends State<T, S>> extends common.State<T, S> {

    /**
     * {@return the player who moves next}
     */
    Player getNextPlayer();

    /**
     * {@return whether the game is over}
     */
    boolean isGameOver();

    /**
     * {@return the status of the game}
     */
    Status getStatus();

    /**
     * {@return whether the player specified has won the game}
     *
     * @param player the player to be tested for win
     */
    default boolean isWinner(Player player) {
        return switch (getStatus()) {
            case PLAYER_1_WINS -> player == Player.PLAYER_1;
            case PLAYER_2_WINS -> player == Player.PLAYER_2;
            default -> false;
        };
    }

    /**
     * Represents the two players.
     */
    enum Player {
        PLAYER_1,
        PLAYER_2;

        /**
         * {@return the opponent of the player}
         */
        public Player opponent() {
            return switch (this) {
                case PLAYER_1 -> PLAYER_2;
                case PLAYER_2 -> PLAYER_1;
            };
        }

    }

    /**
     * Represents the status of the game.
     */
    enum Status {
        IN_PROGRESS,
        PLAYER_1_WINS,
        PLAYER_2_WINS,
        DRAW
    }

}
