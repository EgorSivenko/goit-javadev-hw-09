package org.example.util;

import lombok.experimental.UtilityClass;

import java.util.Scanner;

@UtilityClass
public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readStatusCode() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.print("Please, enter an integer number: ");
            }
        }
    }
}
