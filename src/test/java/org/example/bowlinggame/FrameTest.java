package org.example.bowlinggame;

import org.example.bowlinggame.Frame;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FrameTest {


    /**
     * Tests for isValid() method
     */
    @Test
    public void testIsValid() {
        // Invalid
        Assert.assertFalse(new Frame(11, 0).isValid());
        Assert.assertFalse(new Frame(0, 11).isValid());
        Assert.assertFalse(new Frame(-1, 0).isValid());
        Assert.assertFalse(new Frame(0, -1).isValid());
        Assert.assertFalse(new Frame(6, 5).isValid());
        Assert.assertFalse(new Frame(5, 6).isValid());
        Assert.assertFalse(new Frame(10, 0).isValid());

        // Valid
        Assert.assertTrue(new Frame(0, 0).isValid());
        Assert.assertTrue(new Frame(0, 5).isValid());
        Assert.assertTrue(new Frame(5, 0).isValid());
        Assert.assertTrue(new Frame(4, 4).isValid());

        // Valid Spare
        Assert.assertTrue(new Frame(0, 10).isValid());
        Assert.assertTrue(new Frame(5, 5).isValid());

        // Valid Strike
        Assert.assertTrue(new Frame(10, -1).isValid());
    }

    /**
     * Tests for isSpare() method
     */
    @Test
    public void testIsSpare() {
        // Invalid
        Assert.assertFalse(new Frame(0, 0).isSpare());
        Assert.assertFalse(new Frame(3, 0).isSpare());
        Assert.assertFalse(new Frame(0, 3).isSpare());
        Assert.assertFalse(new Frame(4, 5).isSpare());
        Assert.assertFalse(new Frame(10, -1).isSpare());

        Assert.assertTrue(new Frame(7, 3).isSpare());
        Assert.assertTrue(new Frame(5, 5).isSpare());
    }

    /**
     * Tests for isStrike() method
     */
    @Test
    public void testIsStrike() {
        // Invalid
        Assert.assertFalse(new Frame(0, 0).isStrike());
        Assert.assertFalse(new Frame(3, 0).isStrike());
        Assert.assertFalse(new Frame(0, 3).isStrike());
        Assert.assertFalse(new Frame(4, 5).isStrike());
        Assert.assertFalse(new Frame(5, 5).isStrike());
        Assert.assertFalse(new Frame(0, 10).isStrike());

        Assert.assertTrue(new Frame(10, 0).isStrike());
    }

    /**
     * Tests for isStrike() method
     */
    @Test
    public void testGetCurrentFrameScore() {
        // Invalid
        Assert.assertEquals(0, new Frame(0, 0).getFrameScore());
        Assert.assertEquals(10, new Frame(0, 10).getFrameScore());
        Assert.assertEquals(10, new Frame(10, -1).getFrameScore());
        Assert.assertEquals(7, new Frame(3, 4).getFrameScore());
    }

    /**
     * Tests for isStrike() method
     */
    @Test
    public void testGetCurrentFrameScoreWithChildren() {
        Frame strike1 = new Frame(10, -1);
        Frame strike2 = new Frame(10, -1);
        Frame strike3 = new Frame(10, -1);

        // Two strikes
        strike1.setNextFrame(strike2);
        Assert.assertEquals(20, strike1.getFrameScore());

        // Three strikes
        strike2.setNextFrame(strike3);
        Assert.assertEquals(30, strike1.getFrameScore());

        Frame spare = new Frame(0, 10);
        strike2.setNextFrame(spare);
        Assert.assertEquals(20, strike1.getFrameScore());

        Frame normalFrame = new Frame(3, 5);
        strike2.setNextFrame(normalFrame);
        Assert.assertEquals(23, strike1.getFrameScore());

        Frame zeroFrame = new Frame(0, 0);
        strike1.setNextFrame(zeroFrame);
        Assert.assertEquals(10, strike1.getFrameScore());

        strike1.setNextFrame(normalFrame);
        Assert.assertEquals(18, strike1.getFrameScore());
    }

    /**
     * Tests for getAllFrameScore() method
     */
    @Test
    public void testGetAllFrameScore() {

        Frame strike1 = new Frame(10);
        Frame strike2 = new Frame(10);
        Frame strike3 = new Frame(10);

        // Two strikes
        strike1.setNextFrame(strike2);
        // Three strikes
        strike2.setNextFrame(strike3);
        Assert.assertEquals(Arrays.asList(30, 50, 60), strike1.getAllFramesScore());

        Frame zero1 = new Frame(0, 0);
        Frame zero2 = new Frame(0, 0);
        Frame zero3 = new Frame(0, 0);
        Frame zero4 = new Frame(0, 0);
        zero1.setNextFrame(zero2);
        zero2.setNextFrame(zero3);
        zero3.setNextFrame(zero4);
        Assert.assertEquals(Arrays.asList(0, 0, 0, 0), zero1.getAllFramesScore());

        Frame normal1 = new Frame(1, 2);
        Frame normal3 = new Frame(6, 2);
        strike1.setNextFrame(normal1);
        normal1.setNextFrame(zero3);
        zero3.setNextFrame(normal3);
        Assert.assertEquals(Arrays.asList(13, 16, 16, 24), strike1.getAllFramesScore());
    }

}