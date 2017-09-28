package org.takeaway.game.of.three.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MoveResult {
    private final Integer value;
    private final Integer increment;

    public boolean isWinning() {
        return Integer.valueOf(1).equals(value);
    }

    public static MoveResult gameOver() {
        return new MoveResult(0, 0);
    }
}
