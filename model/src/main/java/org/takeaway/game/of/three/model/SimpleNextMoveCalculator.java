package org.takeaway.game.of.three.model;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SimpleNextMoveCalculator implements NextMoveCalculator {
    private static final Integer[] INCREMENTS = new Integer[]{-1, 0, 1};

    @Override
    public MoveResult calculate(Integer inputValue) {
        Integer increment = Arrays.stream(INCREMENTS)
                .filter(i -> isDivisibleByThree(i, inputValue))
                .findAny()
                .orElseThrow(IllegalStateException::new);
        return new MoveResult(calculateNewValue(inputValue, increment), increment);
    }

    private Integer calculateNewValue(Integer inputValue, Integer increment) {
        return (inputValue + increment) / 3;
    }

    private boolean isDivisibleByThree(int increment, int inputValue) {
        return (inputValue + increment) % 3 == 0;
    }
}
