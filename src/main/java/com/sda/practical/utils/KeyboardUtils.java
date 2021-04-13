package com.sda.practical.utils;
import java.util.InputMismatchException;
import java.util.Scanner;

public class KeyboardUtils {
    public static Integer readNumber(Scanner scanner, String message) {
        Integer value = null;
        while (value == null) {
            try {
                LoggerUtils.print(message);
                value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException inputMismatchException){
                LoggerUtils.print("Exception: Please insert a number !");
                scanner.nextLine();
                value = null;
            }
        }
        return value;
    }
}
