package org.example.bowlinggame;

import org.example.bowlinggame.exceptions.BowlingGameException;
import org.example.bowlinggame.exceptions.FramesLimitException;
import org.example.bowlinggame.exceptions.InvalidFrameException;

import java.util.List;

public class BowlingGame {
    private static final int FRAMES_LIMIT = 10;
    private Frame firstFrame = null;
    private Frame lastFrame = null;
    private int framesCount = 0;

    public BowlingGame() {
    }

    /**
     * Add frame to frames list
     *
     * @param frame
     * @throws BowlingGameException
     */
    public void addFrame(Frame frame) throws BowlingGameException {
        if (!frame.isValid()) {
            throw new InvalidFrameException();
        }
        if (framesCount >= FRAMES_LIMIT) {
            throw new FramesLimitException();
        }
        if (framesCount == 9 && !(frame instanceof LastFrame)) {
            throw new InvalidFrameException();
        }
        //  First frame
        if (framesCount == 0) {
            lastFrame = firstFrame = frame;
            framesCount++;
            return;
        }
        lastFrame.setNextFrame(frame);
        lastFrame = frame;
    }

    public List<Integer> getGameScore() {
        return firstFrame.getAllFramesScore();
    }
}
