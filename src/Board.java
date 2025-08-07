public class Board {
    private String[][] board;

    public Board() {
        board = new String[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Set up empty squares with dots
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = ".";
            }
        }

        // Example: set kings only for now
        board[0][4] = "♚"; // Black king
        board[7][4] = "♔"; // White king
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
