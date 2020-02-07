package com.adtech.todolist.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

@Component
public class StringUtility {

    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    public static final String DIGITS = "0123456789";
    public static final String UPPER_CASE_ALPHANUMERIC = UPPER + DIGITS;
    public static final String LOWER_CASE_ALPHANUMERIC = LOWER + DIGITS;
    public static final String BOTH_CASE_ALPHANUMERIC = UPPER + LOWER + DIGITS;

    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    /**
     * Generate random lower case alpha numeric char
     */
    private char randomChar() {
        return LOWER_CASE_ALPHANUMERIC.charAt(random.nextInt(LOWER_CASE_ALPHANUMERIC.length()));
    }

    /**
     * Generate random lower case alpha numeric UUID with specified length, spacing and spacer char
     */
    public String randomUUID(int length, int spacing, char spacerChar) {
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        while (length > 0) {
            if (spacer == spacing) {
                sb.append(spacerChar);
                spacer = 0;
            }
            length--;
            spacer++;
            sb.append(randomChar());
        }
        return sb.toString();
    }

    /**
     * Generate random lower case alpha numeric string
     */
    public String randomAlphaNumeric(int length) {
        StringBuilder sb = new StringBuilder();
        while (length > 0) {
            length--;
            sb.append(randomChar());
        }
        return sb.toString();
    }

    public String getEndUserCheckLinkUUID() {
        return randomUUID(32, 8, '-');
    }

    public String getAMLCheckUUID() {
        return randomUUID(64, 4, '_');
    }

    /**
     * Generate a random string.
     */
    public String randomString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public StringUtility(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public StringUtility(int length, Random random) {
        this(length, random, BOTH_CASE_ALPHANUMERIC);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public StringUtility(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public StringUtility() {
        this(21);
    }
}
