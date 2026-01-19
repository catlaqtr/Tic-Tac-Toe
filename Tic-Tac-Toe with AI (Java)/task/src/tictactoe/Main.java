package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            System.out.print("Input command: ");
            String line = scanner.nextLine().trim();

            if (line.equals("exit")) {
                break;
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 3 || !parts[0].equals("start")) {
                System.out.println("Bad parameters!");
                continue;
            }

            String playerX = parts[1];
            String playerO = parts[2];

            if (!isValidPlayer(playerX) || !isValidPlayer(playerO)) {
                System.out.println("Bad parameters!");
                continue;
            }

            playGame(scanner, random, playerX, playerO);
        }


    }


    public static void printGrid(char[][] cells) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static boolean isDraw(char[][] cells) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == ' ') return false;
            }
        }
        return true;
    }
    static boolean isWin(char[][] cells, char p) {
        return (cells[0][0] == p && cells[0][1] == p && cells[0][2] == p) ||
                (cells[1][0] == p && cells[1][1] == p && cells[1][2] == p) ||
                (cells[2][0] == p && cells[2][1] == p && cells[2][2] == p) ||
                (cells[0][0] == p && cells[1][0] == p && cells[2][0] == p) ||
                (cells[0][1] == p && cells[1][1] == p && cells[2][1] == p) ||
                (cells[0][2] == p && cells[1][2] == p && cells[2][2] == p) ||
                (cells[0][0] == p && cells[1][1] == p && cells[2][2] == p) ||
                (cells[0][2] == p && cells[1][1] == p && cells[2][0] == p);
    }
    static void computerMoveEasy(Random random, char[][] cells, char symbol) {
        int r, c;
        do {
            r = random.nextInt(3);
            c = random.nextInt(3);
        } while (cells[r][c] != ' ');

        cells[r][c] = symbol;
    }
    static void computerMoveMedium(Random random, char[][] cells, char symbol) {
        // 1) winning move
        if (tryCompleteLine(cells, symbol)) return;

        // 2) blocking move
        char opponent = (symbol == 'X') ? 'O' : 'X';
        if (tryBlockOpponent(cells, symbol, opponent)) return;

        // 3) random
        int r, c;
        do {
            r = random.nextInt(3);
            c = random.nextInt(3);
        } while (cells[r][c] != ' ');
        cells[r][c] = symbol;
    }
    static void computerMoveHard(Random random, char[][] cells, char symbol){
        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        // Try all possible moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == ' ') {
                    // Make the move
                    cells[i][j] = symbol;
                    // Evaluate the move using minimax
                    int score = minimax(cells, false, symbol);
                    // Undo the move
                    cells[i][j] = ' ';

                    // Update best move
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }

        // Make the best move
        cells[bestRow][bestCol] = symbol;
    }

    static int minimax(char[][] cells, boolean isMaximizing, char aiSymbol) {
        char opponent = (aiSymbol == 'X') ? 'O' : 'X';

        // Check terminal states
        if (isWin(cells, aiSymbol)) {
            return 10;
        }
        if (isWin(cells, opponent)) {
            return -10;
        }
        if (isDraw(cells)) {
            return 0;
        }

        if (isMaximizing) {
            // AI's turn - maximize score
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cells[i][j] == ' ') {
                        cells[i][j] = aiSymbol;
                        int score = minimax(cells, false, aiSymbol);
                        cells[i][j] = ' ';
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else {
            // Opponent's turn - minimize score
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cells[i][j] == ' ') {
                        cells[i][j] = opponent;
                        int score = minimax(cells, true, aiSymbol);
                        cells[i][j] = ' ';
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }

    static boolean tryCompleteLine(char[][] cells, char symbol) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == ' ') {
                    cells[i][j] = symbol;
                    if (isWin(cells, symbol)) return true;
                    cells[i][j] = ' ';
                }
            }
        }
        return false;
    }

    static boolean tryBlockOpponent(char[][] cells, char mySymbol, char opponent) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == ' ') {
                    cells[i][j] = opponent;
                    boolean oppWins = isWin(cells, opponent);
                    cells[i][j] = ' ';

                    if (oppWins) {
                        cells[i][j] = mySymbol;
                        return true;
                    }
                }
            }
        }
        return false;
    }



    static void userMove(Scanner scanner, char[][] cells, char symbol) {
        while (true) {
            System.out.println("Enter the coordinates:");

            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scanner.next();
                continue;
            }
            int row = scanner.nextInt();

            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scanner.next();
                continue;
            }
            int col = scanner.nextInt();

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            row--;
            col--;

            if (cells[row][col] != ' ') {

                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            cells[row][col] = symbol;
            scanner.nextLine();

            break;
        }

    }
    static boolean isValidPlayer(String p) {
        return p.equals("user") || p.equals("easy") || p.equals("medium") || p.equals("hard");
    }
    static void playGame(Scanner scanner, Random random, String playerX, String playerO) {
        char[][] board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        printGrid(board);

        char turn = 'X';
        while (true) {
            String currentPlayer = (turn == 'X') ? playerX : playerO;

            if (currentPlayer.equals("user")) {
                userMove(scanner, board, turn);
            } else {
                if (currentPlayer.equals("easy")) {
                    System.out.println("Making move level \"easy\"");
                    computerMoveEasy(random, board, turn);
                } else if (currentPlayer.equals("medium")) {
                    System.out.println("Making move level \"medium\"");
                    computerMoveMedium(random, board, turn);

                }else if (currentPlayer.equals("hard")) {
                    System.out.println("Making move level \"hard\"");
                    computerMoveHard(random,board,turn);
                }
            }

            printGrid(board);

            if (isWin(board, 'X')) {
                System.out.println("X wins");
                break;
            }
            if (isWin(board, 'O')) {
                System.out.println("O wins");
                break;
            }
            if (isDraw(board)) {
                System.out.println("Draw");
                break;
            }

            turn = (turn == 'X') ? 'O' : 'X';
        }
    }

}
