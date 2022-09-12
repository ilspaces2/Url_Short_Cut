package ru.urlshortcut.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Класс генерирует слова по кодам из Ascii Table.
 * Слова состоят из цифр и латинских букв (верхний и нижний регистр)
 */

@Component
public class WordGeneratorByAsciiTable {

    private final Random randomNumber = new Random();

    public WordGeneratorByAsciiTable() {
    }

    public String generateWord(int wordLength) {
        StringBuilder word = new StringBuilder();
        int exitLoop = 0;
        while (exitLoop < wordLength) {
            int number = randomNumber.nextInt(48, 123);
            if ((number <= 57 || number >= 65) && (number <= 90 || number >= 97)) {
                word.appendCodePoint(number);
                exitLoop++;
            }
        }
        return word.toString();
    }
}
