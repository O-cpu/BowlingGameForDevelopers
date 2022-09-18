package org.example.bowlinggame.exceptions;

public class InvalidFrameException extends BowlingGameException {
    public InvalidFrameException() {
        super("Frame is invalid!");
    }
}
