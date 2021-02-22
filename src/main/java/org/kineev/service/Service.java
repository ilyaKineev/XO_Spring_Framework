package org.kineev.service;

import org.kineev.model.Matrix;
import org.kineev.model.Player;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@PropertySource("classpath:application.properties")
public class Service {

    private String step = null;


    private Matrix matrix;
    private Player playerOne;
    private Player playerTwo;
    private Locale locale;

    private final static int size = 3;

    public Service(@Value("${service.xChar}") char xChar, @Value("${service.oChar}") char oChar, MessageSource messageSource) {
        this.xChar = xChar;
        this.oChar = oChar;
        this.messageSource = messageSource;
    }

    char xChar;
    char oChar;
    private MessageSource messageSource;


    public static int getSize() {
        return size;
    }

    private void init() {
        System.out.println("Please choose your language!\nПожалуйста выберите ваш язык!\nEN or RU");
        locale = Locale.ROOT;
        locale.setDefault(getLocale(Print.readString()));

        matrix = new Matrix();
        Print.getPrint(messageSource.getMessage("service.game", null, locale));
        createNewGame();
        createPlayers();
    }

    private void createPlayers() {
        System.out.println(messageSource.getMessage("service.player", null, locale));
        System.out.println(messageSource.getMessage("service.com", null, locale));

        int choice = Print.readInt(locale);

        switch (choice) {
            case 1:
                createPlayersNew(false, false);
                break;
            case 2:
                createPlayersNew(false, true);
                break;
            default:
                createPlayersNew(true, true);
                break;
        }
    }

    private void createPlayersNew(boolean one, boolean two) {
        if (Math.random() * 2 > 1) {
            Print.getPrint(messageSource.getMessage("service.XO", new String[]{String.valueOf(xChar), String.valueOf(oChar)}, locale));
            playerOne = new Player(xChar, one, locale, messageSource);
            playerTwo = new Player(oChar, two, locale, messageSource);
        } else {
            Print.getPrint(messageSource.getMessage("service.XO", new String[]{String.valueOf(oChar), String.valueOf(xChar)}, locale));
            playerOne = new Player(oChar, one, locale, messageSource);
            playerTwo = new Player(xChar, two, locale, messageSource);
        }
    }

    private void createNewGame() {
        Print.getPrint(messageSource.getMessage("service.resetMatrix", null, locale));
        matrix.resetMatrix();
    }

    public void start() {
        init();
        while (true) {
            if (matrix.isWin()) {
                playerOne.stepPlayer(matrix, locale);
                step = String.valueOf(playerOne.getChat());
            } else {
                break;
            }
            if (matrix.isWin()) {
                playerTwo.stepPlayer(matrix, locale);
                step = String.valueOf(playerTwo.getChat());
            } else {
                break;
            }
        }
        endGame();
    }

    private void endGame() {
        Print.getPrint(messageSource.getMessage("service.gameOver", new String[]{step}, locale));
    }

    private Locale getLocale(String localeS) {
        Locale locale = null;
        switch (localeS) {
            case "en":
                locale = Locale.ENGLISH;
                break;
            case "ru":
                locale = Locale.getDefault();
                break;
            default:
                locale = Locale.getDefault();
        }
        return locale;
    }
}
