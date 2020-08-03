import java.util.Scanner;

public class Main {

    public static int movesMade = 0;
    static String[][][] matrix = {{{" ", "13"}, {" ", "23"}, {" ", "33"}},
            {{" ", "12"}, {" ", "22"}, {" ", "32"}},
            {{" ", "11"}, {" ", "21"}, {" ", "31"}}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean drawCounter = true;
        board(matrix);
        do {
            String player = (movesMade % 2 == 0) ? "X" : "O";
            validateAndAppend(player, scanner);
            board(matrix);    // Display board
            movesMade++;
            if (isTheWinner(player)) {
                drawCounter = false;
                System.out.println(player + " wins");
                break;
            }
        } while (movesMade < 9);

        if (drawCounter) {
            System.out.println("Draw");
        }
    }

    public static void board(String[][][] arr) {
        System.out.println("---------");
        System.out.println("| " + arr[0][0][0] + " " + arr[0][1][0] + " " + arr[0][2][0] + " |");
        System.out.println("| " + arr[1][0][0] + " " + arr[1][1][0] + " " + arr[1][2][0] + " |");
        System.out.println("| " + arr[2][0][0] + " " + arr[2][1][0] + " " + arr[2][2][0] + " |");
        System.out.println("---------");
    }

    public static void validateAndAppend(String player, Scanner scanner) {
        boolean cont = true;
        do {
            System.out.print("Enter coordinates: ");
            String inp = scanner.nextLine();  // Input move
            int coordinates;
            String input = "0" + inp;
            input = input.replace(" ", "");
            try {
                coordinates = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int unit = coordinates % 10;
            int tens = coordinates / 10;

            if (unit < 1 || unit > 3 || tens < 1 || tens > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
                // break;
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matrix[i][j][1].toString().equals(String.valueOf(coordinates))) {
                        if (matrix[i][j][0] == " ") {
                            matrix[i][j][0] = player;
                            cont = false;
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    }
                }
            }
        } while (cont);
    }

    public static Boolean isTheWinner(String player) {
        if (movesMade < 5) {
            return false;
        }

        for (int i = 0; i <= 2; i++) {
            if (matrix[i][0][0] == player && matrix[i][1][0] == player && matrix[i][2][0] == player) {
                return true;    // Row check
            }

            if (matrix[0][i][0] == player && matrix[1][i][0] == player && matrix[2][i][0] == player) {
                return true;    // Column check
            }
        }

        if (matrix[0][0][0] == player && matrix[1][1][0] == player && matrix[2][2][0] == player) {
            return true;    // "\" diagonal check
        }

        if (matrix[0][2][0] == player && matrix[1][1][0] == player && matrix[2][0][0] == player) {
            return true;    // "/" check
        }
        return false;
    }
}