package receiver;

import exceptions.CustomException;

import java.util.Scanner;

public class CardAcceptor implements MoneyReceiver {
    private int amount;
    private String numberOfCard;
    private String password;

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void addMoney(){
        boolean isRun = true;
        while (isRun) {
            try {
                if (numberOfCard == null || numberOfCard.isEmpty() || numberOfCard.isBlank()) {
                    String number = tryParseToNumber("Введите номер карты (16 цифр).");
                    if (number.length() != 16) {
                        throw new CustomException("Введено не верное количество символов, повторите еще раз.");
                    }
                    numberOfCard = number;
                } else if (password == null || password.isEmpty() || password.isBlank()){
                    String onTimePassword = tryParseToNumber("Введите пароль.");
                    if (onTimePassword.length() != 4) {
                        throw new CustomException("Введен не верный пароль.");
                }
                    password = onTimePassword;
                } else {
                   amount = parseInt("Введите сумму пополнения.");
                   isRun = false;
                }
            } catch (NumberFormatException | CustomException e) {
                e.printStackTrace();
                System.err.println("Введены неверные данные.");
            }
        }
    }

    protected String inputFromConsole(String text) {
        while (true) {
            System.out.print(text);
            String str = new Scanner(System.in).nextLine();
            if (str.isEmpty() || str.isBlank()) {
                System.err.println("Не корректно введены данные.");
            } else {
                return str;
            }
        }
    }

    protected String tryParseToNumber(String text) {
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

    protected int parseInt(String text) {
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
