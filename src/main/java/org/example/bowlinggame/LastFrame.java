package org.example.bowlinggame;

public class LastFrame extends Frame {
    // Number of Pins in third roll
    private final int thirdRoll;

    public LastFrame(int firstRoll, int secondRoll, int thirdRoll) {
        super(firstRoll, secondRoll);
        this.thirdRoll = thirdRoll;
    }

    @Override
    public boolean isValid() {

        // Common rule
        if (getFirstRoll() > 10 || getFirstRoll() < 0 ||
                getSecondRoll() > 10 || getSecondRoll() < 0 ||
                thirdRoll > 10 || thirdRoll < -1) {
            return false;
        }

        // Strike
        if (getFirstRoll() == 10) {
            if (thirdRoll == -1) {
                return false;
            }
            if (getSecondRoll() < 10) {
                return getSecondRoll() + thirdRoll <= 10;
            }
            return true;
        }
        // Spare
        else if (getFirstRoll() + getSecondRoll() == 10) {
            return thirdRoll != -1;
        }
        return thirdRoll == -1;
    }

    @Override
    protected int getFrameScore() {
        return getFirstRoll() + getSecondRoll() + (thirdRoll == -1 ? 0 : thirdRoll);
    }

    @Override
    protected int getScoreFromNextRolls(int rollsCount) {
        if (rollsCount == 1) {
            return getFirstRoll();
        }

        return getFirstRoll() + getSecondRoll();
    }
}
