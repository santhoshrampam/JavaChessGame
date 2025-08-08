public class Piece {
    private String name;
    private String color;

    public Piece(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getSymbol() {
        switch (name.toLowerCase()) {
            case "king":
                return color.equalsIgnoreCase("white") ? "♔" : "♚";
            case "queen":
                return color.equalsIgnoreCase("white") ? "♕" : "♛";
            case "rook":
                return color.equalsIgnoreCase("white") ? "♖" : "♜";
            case "bishop":
                return color.equalsIgnoreCase("white") ? "♗" : "♝";
            case "knight":
                return color.equalsIgnoreCase("white") ? "♘" : "♞";
            case "pawn":
                return color.equalsIgnoreCase("white") ? "♙" : "♟";
            default:
                return ".";
        }
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
