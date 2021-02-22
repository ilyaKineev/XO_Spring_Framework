package org.kineev.model;

import org.kineev.service.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class Player {

    private final char chat;
    private final boolean isCom;

    @Autowired
    private MessageSource messageSource;


    public Player(char chat, boolean isCom, Locale locale, MessageSource messageSource) {
        this.chat = chat;
        this.isCom = isCom;
        this.messageSource = messageSource;
    }

    public char getChat() {
        return chat;
    }

    public void stepPlayer(Matrix matrix, Locale locale) {
        if (isCom) {
            Print.getPrint(messageSource.getMessage("player.think", new String[]{String.valueOf(chat)}, locale));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Print.printStepCOM(matrix, chat);
        } else {
            Print.getPrint(messageSource.getMessage("player.step", new String[]{String.valueOf(chat)}, locale) + chat);
            Print.printStepPlayer(matrix, chat, locale);
        }
        matrix.printMatrix();
    }
}
