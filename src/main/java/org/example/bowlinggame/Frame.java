package org.example.bowlinggame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    // Number of Pins in first roll
    private final int firstRoll;
    // Number of Pins in second roll
    private final int secondRoll;

    // next Frame
    private Frame nextFrame = null;

    // Constructor for normal roll or Spare
    public Frame(int firstRoll, int secondRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    // Constructor for Strike
    public Frame(int firstRoll) {
        this.firstRoll = firstRoll;
        secondRoll = -1;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public int getSecondRoll() {
        return secondRoll;
    }

    // Validate rolls data
    public boolean isValid() {

        // Common rule
        if (firstRoll > 10 || firstRoll < 0 || secondRoll > 10) {
            return false;
        }

        // Strike
        if (isStrike()) {
            return secondRoll == -1;
        }

        // Normal frame or Spare
        return secondRoll != -1 && firstRoll + secondRoll <= 10;
    }

    /**
     * Set the next frame
     *
     * @param nextFrame next frame
     */
    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }


    /**
     * Returns true if Frame is Spare
     *
     * @return boolean
     */
    boolean isSpare() {
        return firstRoll + secondRoll == 10;
    }

    /**
     * Returns true if frame is Strike
     *
     * @return boolean
     */
    boolean isStrike() {
        return firstRoll == 10;
    }

    /**
     * Return total count of pins from following rollsCount rolls
     *
     * @param rollsCount - count of next rolls
     * @return count of pins
     */
    protected int
    getScoreFromNextRolls(int rollsCount) {
        if (rollsCount == 1) {
            return firstRoll;
        }
        if (isStrike()) {
            return firstRoll + (nextFrame == null ? 0 : nextFrame.getScoreFromNextRolls(1));
        }
        return firstRoll + secondRoll;
    }

    /**
     * Return total Score for The Frame
     *
     * @return Frame score
     */
    protected int getFrameScore() {
        int result = firstRoll;
        // for strike
        if (isStrike()) {
            result += nextFrame == null ? 0 : nextFrame.getScoreFromNextRolls(2);
        }
        // normal roll
        else {
            result += secondRoll;

            // for Spare
            if (isSpare()) {
                result += nextFrame == null ? 0 : nextFrame.getScoreFromNextRolls(1);
            }
        }
        return result;
    }

    /**
     * Returns list of scores fo all frames from current
     *
     * @return list of scores fo all frames
     */
    public List<Integer> getAllFramesScore() {
        final Integer currentFrameScore = getFrameScore();

        if (nextFrame == null) {
            return Collections.singletonList(currentFrameScore);
        }

        // Get list of score from all next frames
        List<Integer> allFrameScoreFromNextFrames = nextFrame.getAllFramesScore();

        // Add current frame score to all other
        List<Integer> allFrameScoreFromNextFramesCorrected = allFrameScoreFromNextFrames.stream().map(e -> e + currentFrameScore).collect(Collectors.toList());
        List<Integer> result = new ArrayList<>();

        // Add current frame score to start of list
        result.add(currentFrameScore);
        result.addAll(allFrameScoreFromNextFramesCorrected);
        return result;
    }
}