public boolean movePiece(String from, String to, boolean isWhiteTurn) {
    int fromRow = 8 - Character.getNumericValue(from.charAt(1));
    int fromCol = from.charAt(0) - 'a';

    int toRow = 8 - Character.getNumericValue(to.charAt(1));
    int toCol = to.charAt(0) - 'a';

    String piece = board[fromRow][fromCol];

    if (piece.equals(".")) {
        System.out.println("No piece at source square!");
        return false;
    }

    // Check if it's the correct player's turn
    boolean isWhitePiece = Character.isUpperCase(piece.charAt(0));
    if (isWhitePiece != isWhiteTurn) {
        System.out.println("It's " + (isWhiteTurn ? "White" : "Black") + "'s turn!");
        return false;
    }

    board[toRow][toCol] = piece;
    board[fromRow][fromCol] = ".";
    return true;
}
