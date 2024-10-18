package dev.kevo.Model;


import dev.kevo.Main;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

import static dev.kevo.Main.*;
import static dev.kevo.Model.Dice.getTotal;

public class BlackJack21 {

    public int[] firstRoll = new int[2];
    public static String player;
    public static String computer;
    public static int currentScore;
    public static int computerScore;
    public static int numOfDice;
    public static int compPlayCount = 1;
    public static String input;


    // game logic
    public static void playBlackJack() {
        player = Main.getName();
        clearConsole();
        System.out.println(border);
        System.out.println(spacer);
        System.out.println(center("Welcome to Blackjack!"));
        System.out.println(spacer);
        System.out.println(center("You will play first and the Computer will play second"));
        System.out.println(spacer);
        System.out.println(center("GOOD LUCK " + player + "!"));
        System.out.println(spacer);
        System.out.println(border);
        clearConsole();
        currentScore = 0;
        System.out.print("Press Enter for your first roll... ");
        scanner.nextLine();
        clearConsole();
        numOfDice = 2;
        gamePlay(numOfDice);

        while (true) {
            try {
                System.out.println(leftJustify("What would you like to do?"));
                System.out.println(spacer);
                System.out.println(center("1 = hold | 2 = Hit with BOTH dice | 3 = Hit with ONE dice"));
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        clearConsole();
                        System.out.println("good luck! The computer must beat: " + currentScore);
                        clearConsole();
                        waitForComputerRoll();
                        numOfDice = 2;
                        computerPlay(numOfDice);
                        String winner = winnerCalc(currentScore, computerScore);
                        if (winner == "TIE") {
                            System.out.println("The game is tied! The computer scored: " + computerScore + " and your score is: " + currentScore);
                            exitOption();
                        } else if (winner.equals(computer)) {
                            System.out.println("So SORRY you LOST! The computer scored: " + computerScore + " and your score is: " + currentScore);
                            exitOption();
                        } else {
                            System.out.println("YAY!!! You WON!!! your score is: " + currentScore + "and the computer score is: " + computerScore);
                            exitOption();
                        }
                        return;
                    case 2:
                        if (currentScore < 21) {
                            numOfDice = 2;
                            gamePlay(numOfDice);

                            if (currentScore > 21) {
                                System.out.println("So sorry " + player + ", You're BUSTED!");
                                currentScore = 0;
                                exitOption();
                                return;
                            }
                            break;
                        }
                    case 3:
                        if  (currentScore < 21) {
                            numOfDice = 1;
                            gamePlay(numOfDice);

                            if (currentScore > 21) {
                                System.out.println("So sorry " + player + ", You're BUSTED!");
                                currentScore = 0;
                                exitOption();
                                return;
                            }
                            break;
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number 1-4");
                scanner.next();
            }
        }
    }

    private static void gamePlay(int numOfDice) {

        int[] playerRoll = Dice.rollTheDice(numOfDice);
        System.out.println(border);
        System.out.println(spacer);
        for (int i = 0; i < playerRoll.length; i++) {
            System.out.println(leftJustify("Roll #" + (i + 1) + " is: " + playerRoll[i]));
        }
        currentScore = currentScore + getTotal(playerRoll);
        System.out.println(spacer);
        System.out.println(leftJustify(player + ", your current total is: " + currentScore));
        System.out.println(spacer);
        System.out.println(border);
        System.out.println(spacer);

    }
    private static void computerPlay(int numOfDice) {

        computer = "computer";
        computerScore = 0;
        int[] compRoll = Dice.rollTheDice(numOfDice);
        computerTurn(compRoll);

        // additional rolls
        while (true) {
            if (computerScore < 10) {
                if (computerScore > currentScore) {
                    return;
                }
                compRoll = Dice.rollTheDice(numOfDice);
                computerTurn(compRoll);
            } else if (computerScore < 16) {
                if (computerScore > currentScore) {
                    return;
                }
                numOfDice = 1;
                compRoll = Dice.rollTheDice(numOfDice);
                computerTurn(compRoll);
            }
            if (computerScore > 15) {
                return;
            }
        }
    }

    private static void computerTurn(int[] compRoll) {
        System.out.println("This is the computer play #" + compPlayCount);
        System.out.println(border);
        System.out.println(spacer);
        computerDiceArrayRoll(compRoll);
        computerScore = computerScore + getTotal(compRoll);
        compPlayCount++;
        System.out.println(spacer);
        System.out.println(leftJustify("The computers current score is: " + computerScore));
        System.out.println(spacer);
        System.out.println(border);
        waitForComputerRoll();
    }

    public static String winnerCalc(int currentScore, int computerScore) {
        String winner;
        if (currentScore > computerScore) {
            winner = player;
        } else if (currentScore < computerScore) {
            winner = computer;
        } else {
            winner = "TIE";
        }
        return winner;
    }

    private static void computerDiceArrayRoll(int[] compRoll) {
        for (int i = 0; i < compRoll.length; i++) {
            System.out.println(leftJustify("Roll #" + (i + 1) + " is: " + compRoll[i]));
        }
    }

    public static void exitOption() {

        while (true) {
            Pattern pattern = Pattern.compile("^[yYnN]$");
            input = scanner.nextLine();
            if (pattern.matcher(input).matches()) {
                System.out.println(input);
                break;
            } else {
                System.out.println("please enter (y)es or (n)o");
            }
        }

        }
    public static void waitForComputerRoll() {
        System.out.println("Please press enter for computer play...");
        scanner.nextLine();
    }
    }
