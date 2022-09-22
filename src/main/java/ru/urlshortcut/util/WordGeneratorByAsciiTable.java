package ru.urlshortcut.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс генерирует слова по кодам из Ascii Table.
 * Слова состоят из цифр и латинских букв (верхний и нижний регистр)
 */

@Component
public class WordGeneratorByAsciiTable {

    private static final int WORD_LENGTH = 8;

    private final ThreadLocalRandom randomNumber = ThreadLocalRandom.current();

    public WordGeneratorByAsciiTable() {
    }

    public String generateWord(int wordLength) {
        return generator(wordLength);

    }

    public String generateWord() {
        return generator(WORD_LENGTH);
    }

    private String generator(int wordLength) {
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
