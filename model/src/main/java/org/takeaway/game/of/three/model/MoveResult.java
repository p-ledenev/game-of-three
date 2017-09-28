package org.takeaway.game.of.three.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MoveResult {
    private final Integer newValue;
    private final Integer increment;
}
