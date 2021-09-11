package com.company;

import java.util.*;

public class Main {

    // Global lists to hold all the cpu and players positions.
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creates the gameboard by using a multidimensional array.
        char[][] gameBoard =
                {{' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameBoard);


        while (true) {
            System.out.print("Enter your placement (1-9): ");
            int playerPosition = getInt();

            // WHILE player tries to add position to an already taken place don't let the player replace the CPU's position.
            while (playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
                System.out.println("Position is taken.");
                playerPosition = scanner.nextInt();
            }

            // Places the players position on the gameboard and IF the player wins or breaks tie the game will break out of the loop.
            placeholder(gameBoard, playerPosition, "PLAYER");
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            // Randomizes the position of the CPU AND doesn't try to replace the players taken position.
            Random randomizer = new Random();
            int cpuPosition = randomizer.nextInt(9) + 1;
            while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                cpuPosition = randomizer.nextInt(9) + 1;
            }
            System.out.println("CPU enters its placement!");

            // Places the CPU's position on the gameboard and IF the CPU wins or breaks tie the game will break out of the loop.
            placeholder(gameBoard, cpuPosition, "CPU");
            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }
    // Checks to see who the winner is by checking which rows/columns are connected.
    public static String checkWinner() {
        // Adds winning combinations to the list.
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

        List lefColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);

        List crossOne = Arrays.asList(1, 5, 9);
        List crossTwo = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);

        winning.add(lefColumn);
        winning.add(middleColumn);
        winning.add(rightColumn);

        winning.add(crossOne);
        winning.add(crossTwo);

        // For-loop to see if the player or the CPU's positions contain any of the combinations above IF not, then it's a tie.
        for (List list : winning) {
            if (playerPositions.containsAll(list)) {
                return "Congratulations you won!";
            } else if (cpuPositions.containsAll(list)) {
                return "CPU wins.";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "It's a tie!";
            }
        }
        return "";
    }

    // Places the players and the CPU's placement into the gameboard.
    public static void placeholder(char[][] gameBoard, int position, String user) {
        // Switch statements that saves the users input into the gameboard.
        char symbol = ' ';

        if (user.equals("PLAYER")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("CPU")) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;

            default:
                break;
        }
        printGameBoard(gameBoard);
    }

    // Handles wrong input from user.
    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException ignore) {
                scanner.next();
                System.out.println("Not a valid answer.");
                System.out.print("Try typing in an integer (1-9): ");
            }
        }
    }

    // Prints out the gameboard using for-each loops.
    public static void printGameBoard(char[][] gameBoard) {

        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
