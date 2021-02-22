package org.kineev.service;

import org.kineev.model.FreeX;
import org.kineev.model.Matrix;
import org.springframework.context.MessageSource;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Print {


    private static MessageSource messageSource;

    public Print(MessageSource messageSource) {
        Print.messageSource = messageSource;
    }

    public static void getPrint(String string) {
        System.out.println(string);
    }

    public static void printStepPlayer(Matrix matrix, char Char, Locale locale) {
        int one;
        int two;
        do {
            Print.getPrint(messageSource.getMessage("print.one", null, locale));
            one = readInt(locale);
            Print.getPrint(messageSource.getMessage("print.two", null, locale));
            two = readInt(locale);
        } while (!matrix.checkIsFree(one, two));
        matrix.setX(one, two, Char);
    }

    public static int readInt(Locale locale) {
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(messageSource.getMessage("print.zeroTo", null, locale) + (Service.getSize() - 1));
                number = readInt(locale);
            }
        } while (!isCorrect(number));
        return number;
    }

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    private static boolean isCorrect(int number) {
        if (number > Service.getSize()) {
            return false;
        } else if (number < 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void printStepCOM(Matrix matrix, char Char) {
        List<FreeX> xes = matrix.freeXES();
        int i = (int) (Math.random() * xes.size());
        matrix.setO(xes.get(i).getX(), xes.get(i).getY(), Char);
    }
}
