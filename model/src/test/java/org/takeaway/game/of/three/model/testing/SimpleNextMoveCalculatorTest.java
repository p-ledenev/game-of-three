package org.takeaway.game.of.three.model.testing;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.takeaway.game.of.three.model.SimpleNextMoveCalculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleNextMoveCalculator.class)
public class SimpleNextMoveCalculatorTest {

    @Autowired
    private SimpleNextMoveCalculator nextMoveCalculator;

    @Test
    public void shouldDiminishAndDivide() {
        int actual = nextMoveCalculator.calculate(7);
        assertEquals(2, actual);
    }

    @Test
    public void shouldIncreaseAndDivide() {
        int actual = nextMoveCalculator.calculate(5);
        assertEquals(2, actual);
    }

    @Test
    public void shouldJustDivide() {
        int actual = nextMoveCalculator.calculate(6);
        assertEquals(2, actual);
    }
}
