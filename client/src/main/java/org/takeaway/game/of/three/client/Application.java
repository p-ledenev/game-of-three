package org.takeaway.game.of.three.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication(scanBasePackages = "org.takeaway.game.of.three.client")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        GameClient gameClient = context.getBean(GameClient.class);
        log.info("Client is started as {} ", gameClient);
        if (args.length > 2)
            gameClient.runGame(Integer.valueOf(args[2]));
    }
}
