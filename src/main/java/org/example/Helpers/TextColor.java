package org.example.Helpers;

import org.example.Characters.ClassType;

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

    public static String toClassColor(ClassType classType, String text) {
        switch (classType){
            case WARRIOR -> {
                return "\u001B[35m" + text + RESET;
            }
            case MAGE -> {
                return "\u001B[34m" + text + RESET;
            }
            case PALADIN -> {
                return "\u001B[33m" + text + RESET;
            }
            case CHARACTER -> {
                return "\u001B[0m" + text + RESET;
            }
        }

        return "\u001B[0m" + text + RESET;
    }

}