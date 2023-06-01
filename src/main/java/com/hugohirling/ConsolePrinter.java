package com.hugohirling;

import java.util.Scanner;

public class ConsolePrinter {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    final static Scanner scanner = new Scanner(System.in);

    public static void printMenu(final String[] menu, final String path) {
        System.out.print(ANSI_GREEN + "[\u001B[90m" + path + ANSI_GREEN +" - Select from menu]: " + ANSI_RESET);
        for(int i=0; i<menu.length-1; i++) {
            System.out.print(ANSI_BLUE + i + " - " + ANSI_RESET + menu[i] + " | ");
        }
        System.out.println(ANSI_BLUE + (menu.length-1) + " - " + ANSI_RESET + menu[menu.length-1]);
    }

    public static String waitForMenuInput(final String path) {
        System.out.print(ANSI_GREEN + "[\u001B[90m"  + path + ANSI_GREEN +" - Select from menu]: " + ANSI_RESET);
        return scanner.nextLine();
    }

    public static void printWarning(final String message) {
        System.out.println(ANSI_YELLOW + "[WARNING]: " + ANSI_RESET + message);
    }

    public static  void printError(final String message) {
        System.out.println(ANSI_RED + "[ERROR]: " + ANSI_RESET + message);
    }

    public static void printInfo(final String message, final String path) {
        System.out.println(ANSI_CYAN + "[\u001B[90m" + path + ANSI_CYAN + " - INFO]: " + ANSI_RESET + message);
    }
    public static void printInfo(final String message) {
        System.out.println(ANSI_CYAN + "[\u001B[90m" + ANSI_CYAN + "INFO]: " + ANSI_RESET + message);
    }

    public static String waitForGeneralInput(final String path) {
        System.out.print(ANSI_GREEN + "[\u001B[90m" + path + ANSI_GREEN +" - INPUT]: " + ANSI_RESET);
        return scanner.nextLine();
    }

    public static void printWelcome() {
        System.out.println(ANSI_PURPLE + "Welcome!" + ANSI_RESET);
    }

}
