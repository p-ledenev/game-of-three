package org.takeaway.game.of.three.client;

import static java.util.Objects.isNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.takeaway.game.of.three.server.api.GameMove;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameMoveRepresentation implements GameMove {
    private Integer value;

    @Override
    public Integer getValue() {
        return value;
    }

    public boolean isWinning() {
        return Integer.valueOf(1).equals(value);
    }

    public boolean isOver() {
        return isNull(value) || Integer.valueOf(0).equals(value);
    }
}
