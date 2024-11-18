package org.example.Helpers;

public class TextColor {

    // ANSI escape codes for colors
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    // Private constructor to prevent instantiation
    private TextColor() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String toRed(String text) {
        return RED + text + RESET;
    }

    public static String toGreen(String text) {
        return GREEN + text + RESET;
    }

}