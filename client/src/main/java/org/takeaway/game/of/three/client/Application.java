package org.takeaway.game.of.three.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@Slf4j
@SpringBootApplication(scanBasePackages = "org.takeaway.game.of.three")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        GameClient gameClient = context.getBean(GameClient.class);
        log.info("Client is started as {} ", gameClient);
        while (true) {
            Integer inputValue = readInput();
            gameClient.runGame(inputValue);

        }
    }

    private static Integer readInput() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter initial value to start the game: ");
        return reader.nextInt();
    }
}
