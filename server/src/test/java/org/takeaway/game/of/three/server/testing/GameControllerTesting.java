package org.takeaway.game.of.three.server.testing;

import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.takeaway.game.of.three.model.SimpleNextMoveCalculator;
import org.takeaway.game.of.three.server.GameController;
import org.takeaway.game.of.three.server.GameMoveResource;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GameController.class, SimpleNextMoveCalculator.class})
public class GameControllerTesting {

    @Autowired
    private GameController gameController;

    @Test
    public void shouldReturnGameOver() {
        GameMoveResource actual = gameController.performNextMove(new GameMoveResource(0));
        assertEquals(actual, new GameMoveResource(0));
    }

    @Test
    public void whenReceiveWinningResult() {
        GameMoveResource actual = gameController.performNextMove(new GameMoveResource(1));
        assertEquals(actual, new GameMoveResource(0));
    }

    @Test
    public void shouldCalculateNewResult() {
        GameMoveResource actual = gameController.performNextMove(new GameMoveResource(10));
        assertEquals(actual, new GameMoveResource(3));
    }
}
