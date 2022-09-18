package org.example.bowlinggame;

import org.example.bowlinggame.exceptions.BowlingGameException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BowlingGameTest {

    @Test
    public void testAddFrameToGame() throws BowlingGameException {
        BowlingGame game = new BowlingGame();
        Frame lastFrame = new LastFrame(10, 10, 10);
        for (int i = 0; i < 9; i++) {
            game.addFrame(new Frame(10, -1));
        }
        game.addFrame(lastFrame);
        List<Integer> expectedResultMaximum = Arrays.asList(30, 60, 90, 120, 150, 180, 210, 240, 270, 300);
        Assert.assertEquals(expectedResultMaximum, game.getGameScore());

        game = new BowlingGame();
        lastFrame = new LastFrame(0, 0, -1);
        for (int i = 0; i < 9; i++) {
            game.addFrame(new Frame(0, 0));
        }
        game.addFrame(lastFrame);
        expectedResultMaximum = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(expectedResultMaximum, game.getGameScore());
    }
}
