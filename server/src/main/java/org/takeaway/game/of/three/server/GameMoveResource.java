package org.takeaway.game.of.three.server;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.takeaway.game.of.three.server.api.GameMove;

@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GameMoveResource implements GameMove {
    private Integer value;

    @Override
    public Integer getValue() {
        return value;
    }

    public boolean isWinning() {
        return Integer.valueOf(1).equals(value);
    }

    public static GameMoveResource gameOver() {
        return new GameMoveResource(0);
    }
}
