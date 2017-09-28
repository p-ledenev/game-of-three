package org.takeaway.game.of.three.model.testing;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.takeaway.game.of.three.model.MoveResult;
import org.takeaway.game.of.three.model.SimpleNextMoveCalculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleNextMoveCalculator.class)
public class SimpleNextMoveCalculatorTest {

    @Autowired
    private SimpleNextMoveCalculator nextMoveCalculator;

    @Test
    public void shouldDiminishAndDivide() {
        MoveResult actual = nextMoveCalculator.calculate(7);
        assertEquals(Integer.valueOf(2), actual.getNewValue());
    }

    @Test
    public void shouldIncreaseAndDivide() {
        MoveResult actual = nextMoveCalculator.calculate(5);
        assertEquals(Integer.valueOf(2), actual.getNewValue());
    }

    @Test
    public void shouldJustDivide() {
        MoveResult actual = nextMoveCalculator.calculate(6);
        assertEquals(Integer.valueOf(2), actual.getNewValue());
    }
}
