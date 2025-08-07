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

    boolean isWhitePiece = Character.isUpperCase(piece.charAt(0));
    if (isWhitePiece != isWhiteTurn) {
        System.out.println("It's " + (isWhiteTurn ? "White" : "Black") + "'s turn!");
        return false;
    }

    String destination = board[toRow][toCol];

    // Basic rule: can't capture your own piece
    if (!destination.equals(".") && Character.isUpperCase(destination.charAt(0)) == isWhiteTurn) {
        System.out.println("You can't capture your own piece!");
        return false;
    }

    // Basic pawn movement
    if (piece.equals("P") || piece.equals("p")) {
        int direction = piece.equals("P") ? -1 : 1; // white moves up, black moves down
        int startRow = piece.equals("P") ? 6 : 1;

        // move forward 1
        if (toCol == fromCol && toRow == fromRow + direction && destination.equals(".")) {
            board[toRow][toCol] = piece;
            board[fromRow][fromCol] = ".";
            return true;
        }

        // move forward 2 from starting row
        if (toCol == fromCol && fromRow == startRow && toRow == fromRow + 2 * direction && board[fromRow + direction][fromCol].equals(".") && destination.equals(".")) {
            board[toRow][toCol] = piece;
            board[fromRow][fromCol] = ".";
            return true;
        }

        // diagonal capture
        if (Math.abs(toCol - fromCol) == 1 && toRow == fromRow + direction && !destination.equals(".") && Character.isUpperCase(destination.charAt(0)) != isWhiteTurn) {
            board[toRow][toCol] = piece;
            board[fromRow][fromCol] = ".";
            return true;
        }

        System.out.println("Invalid pawn move!");
        return false;
    }

    // For now, allow all other pieces to move freely
    board[toRow][toCol] = piece;
    board[fromRow][fromCol] = ".";
    return true;
}
