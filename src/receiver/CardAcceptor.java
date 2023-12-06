package receiver;

import exceptions.CustomException;
import util.InputFromConsole;

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
                    String number = InputFromConsole.tryParseToNumber("Введите номер карты (16 цифр).");
                    if (number.length() != 16) {
                        throw new CustomException("Введено не верное количество символов, повторите еще раз.");
                    }
                    numberOfCard = number;
                } else if (password == null || password.isEmpty() || password.isBlank()){
                    String onTimePassword = InputFromConsole.tryParseToNumber("Введите пароль.");
                    if (onTimePassword.length() != 4) {
                        throw new CustomException("Введен не верный пароль.");
                }
                    password = onTimePassword;
                } else {
                   amount += InputFromConsole.parseInt("Введите сумму пополнения.");
                   isRun = false;
                }
            } catch (NumberFormatException | CustomException e) {
                e.printStackTrace();
                System.err.println("Введены неверные данные.");
            }
        }
    }
}
