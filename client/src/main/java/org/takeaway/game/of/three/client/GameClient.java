package org.takeaway.game.of.three.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.takeaway.game.of.three.model.MoveResult;
import org.takeaway.game.of.three.model.NextMoveCalculator;

@Slf4j
@Component
public class GameClient {
    private final RestTemplate restTemplate;
    private final NextMoveCalculator nextMoveCalculator;
    private final String url;

    public GameClient(@Value("${server.url}") String url,
                      NextMoveCalculator nextMoveCalculator) {
        this.url = url;
        this.nextMoveCalculator = nextMoveCalculator;
        restTemplate = new RestTemplate();
    }

    public void runGame(Integer initialValue) {
        GameMoveRepresentation nextMove = new GameMoveRepresentation(initialValue);
        while (!nextMove.isWinning() || !nextMove.isOver()) {
            try {
                GameMoveRepresentation respondMove = sendMove(nextMove);
                nextMove = makeMove(respondMove.getValue());
                validateRespondMove(respondMove);
                validateNextMove(nextMove);
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    private void validateNextMove(GameMoveRepresentation gameMove) {
        if (gameMove.isWinning())
            log.info("Client WIN");
    }

    private void validateRespondMove(GameMoveRepresentation gameMove) {
        if (gameMove.isWinning())
            log.info("Client LOSE");
    }

    private GameMoveRepresentation makeMove(Integer value) {
        MoveResult moveResult = nextMoveCalculator.calculate(value);
        log.info("Move result {}", moveResult);
        return new GameMoveRepresentation(moveResult.getNewValue());
    }

    private GameMoveRepresentation sendMove(GameMoveRepresentation gameMove) {
        ResponseEntity<GameMoveRepresentation> responseEntity =
                restTemplate.postForEntity(url, gameMove, GameMoveRepresentation.class);
        return responseEntity.getBody();
    }
}
