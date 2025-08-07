private void initializeBoard() {
    // Set up empty squares with "."
    for (int i = 2; i < 6; i++) {
        for (int j = 0; j < 8; j++) {
            board[i][j] = ".";
        }
    }

    // Set black pawns
    for (int j = 0; j < 8; j++) {
        board[1][j] = "p";
    }

    // Set white pawns
    for (int j = 0; j < 8; j++) {
        board[6][j] = "P";
    }

    // Set black pieces
    board[0][0] = board[0][7] = "r";
    board[0][1] = board[0][6] = "n";
    board[0][2] = board[0][5] = "b";
    board[0][3] = "q";
    board[0][4] = "k";

    // Set white pieces
    board[7][0] = board[7][7] = "R";
    board[7][1] = board[7][6] = "N";
    board[7][2] = board[7][5] = "B";
    board[7][3] = "Q";
    board[7][4] = "K";
}
