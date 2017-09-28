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
        if (inputMove.isWinning()) {
            log.info("You lose");
            return GameMoveResource.gameOver();
        }
        MoveResult moveResult = nextMoveCalculator.calculate(inputMove.getValue());
        log.info("Move result {}", moveResult);
        GameMoveResource response = from(moveResult);
        if (response.isWinning())
            log.info("You win!");
        return response;
    }

    private GameMoveResource from(MoveResult moveResult) {
        return new GameMoveResource(moveResult.getNewValue());
    }
}
