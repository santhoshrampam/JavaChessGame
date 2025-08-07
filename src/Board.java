public boolean movePiece(String from, String to, boolean isWhiteTurn) {
    int fromRow = 8 - Character.getNumericValue(from.charAt(1));
    int fromCol = from.charAt(0) - 'a';

    int toRow = 8 - Character.getNumericValue(to.charAt(1));
    int toCol = to.charAt(0) - 'a';

    // Invalid input check
    if (fromRow < 0 || fromRow > 7 || fromCol < 0 || fromCol > 7 ||
        toRow < 0 || toRow > 7 || toCol < 0 || toCol > 7) {
        System.out.println("Invalid coordinates!");
        return false;
    }

    String piece = board[fromRow][fromCol];
    String target = board[toRow][toCol];

    if (piece.equals(".")) {
        System.out.println("No piece at source square!");
        return false;
    }

    boolean isWhitePiece = Character.isUpperCase(piece.charAt(0));
    if (isWhitePiece != isWhiteTurn) {
        System.out.println("It's " + (isWhiteTurn ? "White" : "Black") + "'s turn!");
        return false;
    }

    // Can't capture own piece
    if (!target.equals(".") && Character.isUpperCase(target.charAt(0)) == isWhiteTurn) {
        System.out.println("You can't capture your own piece!");
        return false;
    }

    // Simplified pawn move logic (you can expand later)
    if (piece.equals("P")) {
        if (fromRow - 1 == toRow && fromCol == toCol && target.equals(".")) {
            // move forward 1
        } else if (fromRow == 6 && toRow == 4 && fromCol == toCol && target.equals(".")) {
            // move forward 2 on first move
        } else if (fromRow - 1 == toRow && Math.abs(fromCol - toCol) == 1 && !target.equals(".") && Character.isLowerCase(target.charAt(0))) {
            // diagonal capture
        } else {
            System.out.println("Invalid pawn move!");
            return false;
        }
    } else if (piece.equals("p")) {
        if (fromRow + 1 == toRow && fromCol == toCol && target.equals(".")) {
            // move forward 1
        } else if (fromRow == 1 && toRow == 3 && fromCol == toCol && target.equals(".")) {
            // move forward 2 on first move
        } else if (fromRow + 1 == toRow && Math.abs(fromCol - toCol) == 1 && !target.equals(".") && Character.isUpperCase(target.charAt(0))) {
            // diagonal capture
        } else {
            System.out.println("Invalid pawn move!");
            return false;
        }
    }

    board[toRow][toCol] = piece;
    board[fromRow][fromCol] = ".";
    return true;
}
