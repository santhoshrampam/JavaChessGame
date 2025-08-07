import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

        while (true) {
            board.printBoard();

            System.out.print("Enter move (e.g., e2 e4) or 'exit': ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            board.movePiece(parts[0], parts[1]);
        }

        scanner.close();
    }
}
