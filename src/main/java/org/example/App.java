package org.example;

import org.example.bowlinggame.BowlingGame;
import org.example.bowlinggame.Frame;
import org.example.bowlinggame.LastFrame;
import org.example.bowlinggame.exceptions.BowlingGameException;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws BowlingGameException {
        System.out.println("The Bowling Game for developers!");
        Scanner sc = new Scanner(System.in);

        BowlingGame game = new BowlingGame();
        Frame frame = null;
        for (int i = 0; i < 9; i++) {
            do {
                System.out.println();
                System.out.println("Frame " + i);
                System.out.print("Enter the first roll:");
                int roll1 = sc.nextInt();
                int roll2 = -1;
                if (roll1 != 10) {
                    System.out.print("Enter the second roll:");
                    roll2 = sc.nextInt();
                }
                frame = new Frame(roll1, roll2);
                if (!frame.isValid()) {
                    System.out.println("Invalid frame!!!");
                }
            } while (!frame.isValid());
            game.addFrame(frame);
        }
        do {
            System.out.println();
            System.out.println("Last frame ");
            System.out.print("Enter the first roll:");
            int roll1 = sc.nextInt();
            System.out.print("Enter the second roll:");
            int roll2 = sc.nextInt();
            int roll3 = -1;
            if (roll1 == 10 || roll2 + roll1 == 10) {
                System.out.print("Enter the third roll:");
                roll3 = sc.nextInt();
            }
            frame = new LastFrame(roll1, roll2, roll3);
            if (!frame.isValid()) {
                System.out.println("Invalid frame!!!");
            }
        } while (!frame.isValid());
        game.addFrame(frame);

        System.out.println("Game result: " + game.getGameScore());
    }
}
