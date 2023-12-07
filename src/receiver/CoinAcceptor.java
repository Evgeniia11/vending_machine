package receiver;

public class CoinAcceptor implements MoneyReceiver {
    private int amount;

    public CoinAcceptor(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void addMoney() {
        System.out.println("Торговый автомат принимает только монеты, номиналом 10 сом.");
        amount += 10;
        System.out.println("Произошло пополнение на 10 сом.");
    }
}
