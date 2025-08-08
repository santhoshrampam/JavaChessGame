public class Board {
    private String[][] board;

    public Board() {
        board = new String[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Fill empty squares
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board[r][c] = ".";
            }
        }

        // Black pieces
        board[0][0] = board[0][7] = "r";
        board[0][1] = board[0][6] = "n";
        board[0][2] = board[0][5] = "b";
        board[0][3] = "q";
        board[0][4] = "k";
        for (int j = 0; j < 8; j++) board[1][j] = "p";

        // White pieces
        board[7][0] = board[7][7] = "R";
        board[7][1] = board[7][6] = "N";
        board[7][2] = board[7][5] = "B";
        board[7][3] = "Q";
        board[7][4] = "K";
        for (int j = 0; j < 8; j++) board[6][j] = "P";
    }

    public void printBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    public boolean movePiece(String from, String to, boolean isWhiteTurn) {
        // Basic validation of input format
        if (from == null || to == null || from.length() < 2 || to.length() < 2) {
            System.out.println("Invalid input format.");
            return false;
        }

        int fromCol = from.charAt(0) - 'a';
        int toCol = to.charAt(0) - 'a';
        int fromRow = 8 - Character.getNumericValue(from.charAt(1));
        int toRow = 8 - Character.getNumericValue(to.charAt(1));

        // Check bounds
        if (fromCol < 0 || fromCol > 7 || toCol < 0 || toCol > 7 ||
            fromRow < 0 || fromRow > 7 || toRow < 0 || toRow > 7) {
            System.out.println("Invalid coordinates!");
            return false;
        }

        String piece = board[fromRow][fromCol];
        String target = board[toRow][toCol];

        if (piece == null || piece.equals(".")) {
            System.out.println("No piece at source square!");
            return false;
        }

        boolean isWhitePiece = Character.isUpperCase(piece.charAt(0));
        if (isWhitePiece != isWhiteTurn) {
            System.out.println("It's " + (isWhiteTurn ? "White" : "Black") + "'s turn!");
            return false;
        }

        // Can't capture own piece
        if (!target.equals(".") && (Character.isUpperCase(target.charAt(0)) == isWhitePiece)) {
            System.out.println("You can't capture your own piece!");
            return false;
        }

        int rowDiff = toRow - fromRow;
        int colDiff = toCol - fromCol;

        // ---- Pawn logic ----
        if (piece.equals("P") || piece.equals("p")) {
            int direction = piece.equals("P") ? -1 : 1; // white moves up (-1), black moves down (+1)
            int startRow = piece.equals("P") ? 6 : 1;

            // forward 1
            if (colDiff == 0 && rowDiff == direction && target.equals(".")) {
                board[toRow][toCol] = piece;
                board[fromRow][fromCol] = ".";
                return true;
            }

            // forward 2 from starting position (ensure intermediate empty)
            if (colDiff == 0 && fromRow == startRow && rowDiff == 2 * direction &&
                board[fromRow + direction][fromCol].equals(".") && target.equals(".")) {
                board[toRow][toCol] = piece;
                board[fromRow][fromCol] = ".";
                return true;
            }

            // diagonal capture
            if (Math.abs(colDiff) == 1 && rowDiff == direction && !target.equals(".")
                && Character.isUpperCase(target.charAt(0)) != isWhitePiece) {
                board[toRow][toCol] = piece;
                board[fromRow][fromCol] = ".";
                return true;
            }

            System.out.println("Invalid pawn move!");
            return false;
        }

        // ---- Knight logic ----
        if (piece.equalsIgnoreCase("n")) {
            int ar = Math.abs(rowDiff), ac = Math.abs(colDiff);
            if (!((ar == 2 && ac == 1) || (ar == 1 && ac == 2))) {
                System.out.println("Invalid knight move.");
                return false;
            }
            board[toRow][toCol] = piece;
            board[fromRow][fromCol] = ".";
            return true;
        }

        // ---- Bishop logic ----
        if (piece.equalsIgnoreCase("b")) {
            if (Math.abs(rowDiff) != Math.abs(colDiff)) {
                System.out.println("Invalid bishop move.");
                return false;
            }
            int stepR = rowDiff > 0 ? 1 : -1;
            int stepC = colDiff > 0 ? 1 : -1;
            int r = fromRow + stepR, c = fromCol + stepC;
            while (r != toRow && c != toCol) {
                if (!board[r][c].equals(".")) {
                    System.out.println("Path is blocked.");
                    return false;
                }
                r += stepR; c += stepC;
            }
            board[toRow][toCol] = piece;
            board[fromRow][fromCol] = ".";
            return true;
        }

        // ---- Rook logic ----
        if (piece.equalsIgnoreCase("r")) {
            if (fromRow != toRow && fromCol != toCol) {
                System.out.println("Invalid rook move.");
                return false;
            }
            int stepR = Integer.compare(toRow, fromRow);
            int stepC = Integer.compare(toCol, fromCol);
            int r = fromRow + stepR;
            int c = fromCol + stepC;
            while (r != toRow || c != toCol) {
                if (!board[r][c].equals(".")) {
                    System.out.println("Path is blocked.");
                    return false;
                }
                if (r != toRow) r += stepR;
                if (c != toCol) c += stepC;
            }
            board[toRow][toCol] = piece;
            board[fromRow][fromCol] = ".";
            return true;
        }

        // ---- Queen logic (rook + bishop) ----
        if (piece.equalsIgnoreCase("q")) {
            // diagonal
            if (Math.abs(rowDiff) == Math.abs(colDiff)) {
                int stepR = rowDiff > 0 ? 1 : -1;
                int stepC = colDiff > 0 ? 1 : -1;
                int r = fromRow + stepR, c = fromCol + stepC;
                while (r != toRow && c != toCol) {
                    if (!board[r][c].equals(".")) {
                        System.out.println("Path is blocked.");
                        return false;
                    }
                    r += stepR; c += stepC;
                }
            } else if (fromRow == toRow || fromCol == toCol) {
                int stepR = Integer.compare(toRow, fromRow);
                int stepC = Integer.compare(toCol, fromCol);
                int r = fromRow + stepR;
                int c = fromCol + stepC;
                while (r != toRow || c != toCol) {
                    if (!board[r][c].equals(".")) {
                        System.out.println("Path is blocked.");
                        return false;
                    }
                    if (r != toRow) r += stepR;
                    if (c != toCol) c += stepC;
                }
            } else {
                System.out.println("Invalid queen move.");
                return false;
            }
            board[toRow][toCol] = piece;
            board[fromRow][fromCol] = ".";
            return true;
        }

        // ---- King logic ----
        if (piece.equalsIgnoreCase("k")) {
            if (Math.abs(rowDiff) <= 1 && Math.abs(colDiff) <= 1) {
                // Note: no check/checkmate or castling logic here
                board[toRow][toCol] = piece;
                board[fromRow][fromCol] = ".";
                return true;
            } else {
                System.out.println("Invalid king move.");
                return false;
            }
        }

        // Fallback: allow move for any other symbol (shouldn't happen)
        board[toRow][toCol] = piece;
        board[fromRow][fromCol] = ".";
        return true;
    }
}
