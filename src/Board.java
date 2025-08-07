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
// Knight movement
if (piece.equalsIgnoreCase("n")) {
    if (!((Math.abs(rowDiff) == 2 && Math.abs(colDiff) == 1) || 
          (Math.abs(rowDiff) == 1 && Math.abs(colDiff) == 2))) {
        System.out.println("Invalid knight move.");
        return false;
    }

    // Cannot capture own piece
    if (!target.equals(".") && Character.isUpperCase(piece.charAt(0)) == Character.isUpperCase(target.charAt(0))) {
        System.out.println("Cannot capture own piece.");
        return false;
    }
}
// Bishop movement
if (piece.equalsIgnoreCase("b")) {
    if (Math.abs(rowDiff) != Math.abs(colDiff)) {
        System.out.println("Invalid bishop move.");
        return false;
    }

    // Check for obstacles in the diagonal path
    int rowStep = rowDiff / Math.abs(rowDiff);
    int colStep = colDiff / Math.abs(colDiff);

    int checkRow = fromRow + rowStep;
    int checkCol = fromCol + colStep;

    while (checkRow != toRow && checkCol != toCol) {
        if (!board[checkRow][checkCol].equals(".")) {
            System.out.println("Path is blocked.");
            return false;
        }
        checkRow += rowStep;
        checkCol += colStep;
    }

    // Cannot capture own piece
    if (!target.equals(".") && Character.isUpperCase(piece.charAt(0)) == Character.isUpperCase(target.charAt(0))) {
        System.out.println("Cannot capture own piece.");
        return false;
    }
}

