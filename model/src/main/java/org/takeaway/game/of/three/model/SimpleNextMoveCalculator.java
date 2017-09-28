package org.takeaway.game.of.three.model;

import org.springframework.stereotype.Component;

@Component
public class SimpleNextMoveCalculator implements NextMoveCalculator {

    @Override
    public int calculate(int inputValue) {
        if (inputValue + 1 % 3 == 0)
            return (inputValue + 1) / 3;
        if (inputValue - 1 % 3 == 0)
            return (inputValue - 1) / 3;
        return inputValue / 3;
    }
}
