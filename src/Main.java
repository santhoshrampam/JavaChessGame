import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

        boolean isWhiteTurn = true;

        while (true) {
            board.printBoard();
            System.out.println((isWhiteTurn ? "White" : "Black") + "'s turn");

            System.out.print("Enter move (e.g., e2 e4) or 'exit': ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = input.trim().split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid input. Format: e2 e4");
                continue;
            }

            boolean moved = board.movePiece(parts[0], parts[1], isWhiteTurn);
            if (moved) {
                isWhiteTurn = !isWhiteTurn;  // Switch turn only if move was valid
            }
        }

        scanner.close();
    }
}
