package util;

import exceptions.CustomException;

import java.util.Scanner;

public class InputFromConsole {

    public static String inputFromConsole(String text) {
        while (true) {
            System.out.println(text);
            String str = new Scanner(System.in).nextLine();
            if (str.isEmpty() || str.isBlank()) {
                System.err.println("Не корректно введены данные.");
            } else {
                return str;
            }
        }
    }
    public static String tryParseToNumber(String text) {
        while (true) {
            String resultText = inputFromConsole(text);
            try {
                long result = Long.parseLong(resultText);
                if (result < 0) {
                    throw new CustomException("Пароль должен содержать только цифры.");
                }
                return resultText;
            } catch (NumberFormatException | CustomException e) {
                e.printStackTrace();
                System.err.println("Введены неверные данные.");
            }
        }
    }

    public static int parseInt(String text) {
        while (true) {
            String resultText =inputFromConsole(text);
            try {
                int result = Integer.parseInt(resultText);
                if (result < 0) {
                    throw new CustomException("Введенное значение не должно быть меньше 0.");
                }
                return result;
            } catch (NumberFormatException | CustomException e) {
                e.printStackTrace();
                System.err.println("Введены неверные данные.");
            }
        }
    }
}
