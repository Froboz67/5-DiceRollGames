package dev.kevo;

import java.util.InputMismatchException;
import java.util.Scanner;

import static dev.kevo.Model.BlackJack21.*;
import static dev.kevo.Model.Dice.*;

public class Main {
    public static String name;
    public static String border = "=================================================================";
    public static String spacer = " ".repeat(65);
    public static Scanner scanner = new Scanner(System.in);
    public static String getName() {
        return name;
    }

    public static void main(String[] args) {
        // Greeting
        clearConsole();
        System.out.println("Hello! Welcome to the DICE app!");
        System.out.print("What's your name? ");
        name = scanner.nextLine();
        clearConsole();
        System.out.println(border);
        System.out.println(spacer);
        System.out.println(center("Hi " + name + ", it's nice to meet you."));
        System.out.println(spacer);

        while (true) {
            // Main Menu - 65 characters wide


            // Main Menu
            System.out.println(border);
            //System.out.println(spacer);
            System.out.println(center("Main Menu"));
            System.out.println(center("please make a selection " + name));
            System.out.println(border);
            System.out.println(spacer);
            System.out.println(leftJustify("option 1 - Roll Dice"));
            System.out.println(leftJustify("option 2 - Roll Two Dice"));
            System.out.println(leftJustify("option 3 - Choose How Many Dice to Roll"));
            System.out.println(leftJustify("option 4 - Play BlackJack 21"));
            System.out.println(leftJustify("option 5 - Exit"));
            System.out.println(spacer);
            System.out.println(border);
            System.out.print("Please enter your choice: ");

            try {
                int userOption = scanner.nextInt();
                scanner.nextLine();
                switch (userOption) {
                    case 1:
                        for (int i = 0; i < 10; i++) {
                            System.out.println(spacer);
                        }
                        int roll = getRoll();
                        System.out.println(center(name + ", you have selected option 1 - roll dice"));
                        System.out.println(spacer);
                        System.out.println(center("You have rolled " + roll));
                        System.out.println(spacer);
                        eP();
                        break;
                    case 2:
                        int roll1 = getRoll();
                        int roll2 = getRoll();
                        int rollTotal = roll1 + roll2;
                        for (int i = 0; i < 10; i++) {
                            System.out.println(spacer);
                        }
                        System.out.println(center(name + ", you selected Option 2 - roll two dice"));
                        System.out.println(spacer);
                        System.out.println(leftJustify("your first dice roll is: " + roll1));
                        System.out.println(spacer);
                        System.out.println(leftJustify("your second dice roll is: " + roll2));
                        System.out.println(spacer);
                        System.out.println(leftJustify("your total is: " + rollTotal));
                        System.out.println(spacer);
                        eP();
                        break;
                    case 3:
                        System.out.print("How many dice would you like to roll? - ");
                        int thisManyDice = scanner.nextInt();
                        int[] multiRoll = rollTheDice(thisManyDice);
                        int total = 0;
                        for (int i = 0; i < thisManyDice; i++) {
                            System.out.println("roll " + (i+1) + " is: " + multiRoll[i]);
                            total += multiRoll[i];
                        }
                        System.out.println("the total is: " + total);
                        int score = getTotal(multiRoll);
                        System.out.println(score);
                        break;
                    case 4:
                        playBlackJack();
                        break;
                    case 5:
                        System.out.println("Thank you!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number 1-4");
                scanner.next();
            }
        }
    }
    static String center(String text) {
        int padding = (65 - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(padding);
    }
    static String leftJustify(String text) {
        int padding = 15;
        return " ".repeat(padding) + text;
    }
    public static void clearConsole() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }
}