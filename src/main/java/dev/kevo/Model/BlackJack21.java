package dev.kevo.Model;


import dev.kevo.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

import static dev.kevo.Main.clearConsole;
import static dev.kevo.Main.scanner;
import static dev.kevo.Model.Dice.getTotal;

public class BlackJack21 {

    public int[] firstRoll = new int[2];
    public static String player;
    public static String computer;
    public static int currentScore;
    public static int computerScore;
    public static int numOfDice;
    public static int compPlayCount = 1;


    // game logic
    public static void playBlackJack() {
        player = Main.getName();
        System.out.println("Welcome to Blackjack!");
        System.out.println("You will play first and the Computer will play second");
        System.out.println("GOOD LUCK!");
        currentScore = 0;
        System.out.print("Press Enter for your first roll... ");
        scanner.nextLine();
        numOfDice = 2;
        gamePlay(numOfDice);

        while (true) {
            try {
                System.out.println("What would you like to do?");
                System.out.println("1 - hold | 2 - Hit with BOTH dice | 3 - Hit with ONE dice");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        clearConsole();
                        System.out.println("good luck! The computer must beat: " + currentScore);
                        clearConsole();
                        eP();
                        numOfDice = 2;
                        computerPlay(numOfDice);
                        String winner = winnerCalc(currentScore, computerScore);
                        if (winner == "TIE") {
                            System.out.println("The game is tied! The computer scored: " + computerScore + " and your score is: " + currentScore);
                            eP();
                        } else if (winner.equals(computer)) {
                            System.out.println("So SORRY you LOST! The computer scored: " + computerScore + " and your score is: " + currentScore);
                            eP();
                        } else {
                            System.out.println("YAY!!! You WON!!! your score is: " + currentScore + "and the computer score is: " + computerScore);
                            eP();
                        }
                        return;
                    case 2:
                        if (currentScore < 21) {
                            numOfDice = 2;
                            gamePlay(numOfDice);

                            if (currentScore > 21) {
                                System.out.println("So sorry " + player + ", You're BUSTED!");
                                System.out.println("please press enter");
                                scanner.nextLine();
                                currentScore = 0;
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
                                System.out.println("please press enter");
                                scanner.nextLine();
                                currentScore = 0;
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

        for (int i = 0; i < playerRoll.length; i++) {
            System.out.println("Roll #" + (i + 1) + " is: " + playerRoll[i]);
        }
        currentScore = currentScore + getTotal(playerRoll);
        System.out.println(player + ", your current total is: " + currentScore);

    }
    private static void computerPlay(int numOfDice) {

        computer = "computer";
        int[] compRoll = Dice.rollTheDice(numOfDice);
        System.out.println("This is the computer play #" + compPlayCount);

        diceArray(compRoll);
        computerScore = computerScore + getTotal(compRoll);
        compPlayCount++;
        System.out.println("The computers current score is: " + computerScore);
        eP();

        // additional rolls
        while (true) {
            if (computerScore < 10) {
                compRoll = Dice.rollTheDice(numOfDice);
                System.out.println("This is the computer play #" + compPlayCount);
                diceArray(compRoll);
                computerScore = computerScore + getTotal(compRoll);
                compPlayCount++;
                System.out.println("The computers current score is: " + computerScore);
                eP();
            } else if (computerScore < 16) {
                numOfDice = 1;
                compRoll = Dice.rollTheDice(numOfDice);
                System.out.println("This is the computer play #" + compPlayCount);
                diceArray(compRoll);
                computerScore = computerScore + getTotal(compRoll);
                compPlayCount++;
                System.out.println("The computers current score is: " + computerScore);
                eP();
            }
            if (computerScore > 15) {
                return;
            }
        }
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

    private static void diceArray(int[] compRoll) {
        for (int i = 0; i < compRoll.length; i++) {
            System.out.println("Roll #" + (i + 1) + " is: " + compRoll[i]);
        }
    }

    public static void eP() {
            System.out.println("Please press Enter to continue ... ");
            scanner.nextLine();
        }
    }
