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
    public GameMoveResource performNextMove(@RequestBody GameMoveResource inputMove) {
        log.info("Received move: {}", inputMove);
        if (inputMove.isWinning()) {
            log.info("Server LOSE");
            return GameMoveResource.gameOver();
        }
        MoveResult moveResult = nextMoveCalculator.calculate(inputMove.getValue());
        log.info("Next move to send {}", moveResult);
        GameMoveResource response = from(moveResult);
        log.info("Sending move: {}", inputMove);
        if (response.isWinning())
            log.info("Server WIN");
        return response;
    }

    private GameMoveResource from(MoveResult moveResult) {
        return new GameMoveResource(moveResult.getNewValue());
    }
}
