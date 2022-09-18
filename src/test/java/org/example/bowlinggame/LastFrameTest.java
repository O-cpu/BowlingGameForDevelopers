package org.example.bowlinggame;

import org.example.bowlinggame.LastFrame;
import org.junit.Assert;
import org.junit.Test;

public class LastFrameTest {

    @Test
    public void testIsValidLastFrame() {
        Assert.assertFalse(new LastFrame(11, 0, 0).isValid());
        Assert.assertFalse(new LastFrame(0, 11, 0).isValid());
        Assert.assertFalse(new LastFrame(-1, 0, 0).isValid());
        Assert.assertFalse(new LastFrame(0, -1, 0).isValid());
        Assert.assertFalse(new LastFrame(6, 5, 0).isValid());
        Assert.assertFalse(new LastFrame(5, 6, 0).isValid());
        Assert.assertFalse(new LastFrame(5, 5, -2).isValid());
        Assert.assertFalse(new LastFrame(5, 5, 11).isValid());
        Assert.assertFalse(new LastFrame(10, 5, -1).isValid());

        Assert.assertTrue(new LastFrame(10, 0, 0).isValid());
        Assert.assertTrue(new LastFrame(5, 5, 10).isValid());
        Assert.assertTrue(new LastFrame(5, 5, 0).isValid());
        Assert.assertTrue(new LastFrame(0, 10, 0).isValid());
        Assert.assertTrue(new LastFrame(0, 10, 10).isValid());
        Assert.assertTrue(new LastFrame(10, 10, 10).isValid());
    }

    @Test
    public void testGetFrameScore() {
        Assert.assertEquals(8, new LastFrame(5, 3, -1).getFrameScore());
        Assert.assertEquals(10, new LastFrame(10, 0, 0).getFrameScore());
        Assert.assertEquals(20, new LastFrame(5, 5, 10).getFrameScore());
        Assert.assertEquals(10, new LastFrame(5, 5, 0).getFrameScore());
        Assert.assertEquals(10, new LastFrame(0, 10, 0).getFrameScore());
        Assert.assertEquals(20, new LastFrame(0, 10, 10).getFrameScore());
        Assert.assertEquals(30, new LastFrame(10, 10, 10).getFrameScore());
    }

}
