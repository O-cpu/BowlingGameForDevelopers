package org.example.bowlinggame.exceptions;

public class FramesLimitException extends BowlingGameException {
    public FramesLimitException() {
        super("Frames limit exceeded!");
    }
}
