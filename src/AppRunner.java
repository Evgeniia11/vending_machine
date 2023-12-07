import enums.ActionLetter;
import exceptions.CustomException;
import model.*;
import receiver.CardAcceptor;
import receiver.CoinAcceptor;
import receiver.MoneyReceiver;
import util.InputFromConsole;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();

    private MoneyReceiver acceptor;

    private static boolean isExit = false;

    private AppRunner() {
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
    }

    public static void run() {
        AppRunner app = new AppRunner();
        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        print("В автомате доступны:");
        showProducts(products);
        if (acceptor == null) {
            int getAcceptor = InputFromConsole.parseInt("Для пополнения через карту введите * 1 *, " +
                    "для пополнения монетами введите * 2 *.");
            if (getAcceptor == 1) {
                acceptor = new CardAcceptor();
            } else {
                acceptor = new CoinAcceptor();
            }
        }
        print("Доступная сумма: " + acceptor.getAmount());

        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());
        chooseAction(allowProducts);

    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (acceptor.getAmount() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        print(" a - Пополнить баланс");
        showActions(products);
        print(" h - Выйти");
        String action = fromConsole().substring(0, 1);
        if ("a".equalsIgnoreCase(action)) {
            acceptor.addMoney();
            return;
        }
        try {
            if (products.size() > 0) {
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                        acceptor.setAmount(acceptor.getAmount() - products.get(i).getPrice());
                        print("Вы купили " + products.get(i).getName());
                        break;
                    }
                }
            } else {
                throw new CustomException("Введены не корректные данные.");
            }
        } catch (IllegalArgumentException | CustomException e) {
            if ("h".equalsIgnoreCase(action)) {
                isExit = true;
            } else {
                print("Недопустимая буква. Попробуйте еще раз.");
                chooseAction(products);
            }
        }


    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}
