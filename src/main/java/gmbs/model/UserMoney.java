package gmbs.model;

public class UserMoney {

    private static final int DEFAULT_TICKET_PRICE = 1000;
    private final int money;

    public UserMoney(int userInputMoney) {
        moneyValidate(userInputMoney);
        this.money = userInputMoney;
    }

    private void moneyValidate(int money) {
        if (hasChange(money)) {
            throw new IllegalArgumentException("[error] invalid money input");
        }
    }

    private static boolean hasChange(int money) {
        return money % DEFAULT_TICKET_PRICE != 0 || money == 0;
    }

    public int getTicketCount() {
        return money / DEFAULT_TICKET_PRICE;
    }

    public int getUserMoney() {
        return money;
    }
}
