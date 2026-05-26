public class Result {
    private static final char EMPTY = ' ';
    private static final int[][] WINNING_COMBINATIONS = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
        {0, 4, 8}, {2, 4, 6}
    };
    private final char[] board = new char[9];
    private char currentPlayer = 'X';

    public void initBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = EMPTY;
        }
    }

    public void setCurrentPlayer(char player) {
        currentPlayer = player;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean placerPion(int index) {
        if (board[index] == EMPTY) {
            board[index] = currentPlayer;
            return true;
        }
        return false;
    }

    public void changerJoueur() {
        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    }

    public boolean verifierVictoire() {
        for (int[] combinaison : WINNING_COMBINATIONS) {
            if (board[combinaison[0]] == currentPlayer &&
                board[combinaison[1]] == currentPlayer &&
                board[combinaison[2]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    public boolean estMatchNul() {
        for (char c : board) {
            if (c == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public String afficherBoard() {
        return String.format(
            " %s | %s | %s%n---+---+---%n %s | %s | %s%n---+---+---%n %s | %s | %s%n",
            afficherCellule(0), afficherCellule(1), afficherCellule(2),
            afficherCellule(3), afficherCellule(4), afficherCellule(5),
            afficherCellule(6), afficherCellule(7), afficherCellule(8)
        );
    }

    private String afficherCellule(int index) {
        return board[index] == EMPTY ? String.valueOf(index + 1) : String.valueOf(board[index]);
    }
}
