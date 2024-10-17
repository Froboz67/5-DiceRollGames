package dev.kevo.Model;

public class Dice {

    public static int getRoll() {

        return (int) ((Math.random() * 6) + 1);
    }

    public static int[] rollTheDice(int numOfDice) {

        int[] dice = new int[numOfDice];
        for (int i = 0; i < numOfDice; i++) {
            dice[i] = getRoll();
        }
        return dice;
    }
    public static int getTotal(int[] dice) {

        int total = 0;
        for (int die: dice) {
            total+= die;
        }
        return total;
    }
}
