package org.takeaway.game.of.three.client.testing;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.takeaway.game.of.three.client.GameClient;
import org.takeaway.game.of.three.client.GameMoveRepresentation;
import org.takeaway.game.of.three.model.SimpleNextMoveCalculator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {GameClient.class, SimpleNextMoveCalculator.class})
public class GameClientTesting {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private GameClient gameClient;

    private ExecutorService executor;

    @Before
    public void setUp() {
        executor = Executors.newCachedThreadPool();
    }

    @After
    public void tearDown() {
        executor.shutdownNow();
    }

    @Test
    public void whenInputIsGameOver() throws Exception {
        Future future = executor.submit(() -> gameClient.runGame(0));
        awaitFuture(future);
    }

    @Test
    public void shouldFinishGame() throws Exception{
        when(restTemplate.postForEntity(any(), any(), any()))
                .thenReturn(new ResponseEntity<>(new GameMoveRepresentation(0), HttpStatus.OK));
        Future future = executor.submit(() -> gameClient.runGame(1));
        awaitFuture(future);
    }

    private void awaitFuture(Future future)throws Exception {
        try {
            future.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            assertFalse(true);
        }
    }
}
