package org.takeaway.game.of.three.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.takeaway.game.of.three.model.MoveResult;
import org.takeaway.game.of.three.model.NextMoveCalculator;

@Slf4j
@RestController
public class GameController {
    private final NextMoveCalculator nextMoveCalculator;

    @Autowired
    public GameController(NextMoveCalculator nextMoveCalculator) {
        this.nextMoveCalculator = nextMoveCalculator;
    }

    @RequestMapping(value = "/next-move", method = RequestMethod.POST)
    public MoveResult performNextMove(@RequestBody MoveResult inputMove) {
        if (inputMove.isWinning()) {
            log.info("You lose");
            return MoveResult.gameOver();
        }
        MoveResult moveResult = nextMoveCalculator.calculate(inputMove.getValue());
        log.info("Move result %s", moveResult);
        if (moveResult.isWinning())
            log.info("You win!");
        return moveResult;
    }
}
