package gmbs.model;

public class UserMoney {

    private static final int DEFAULT_TICKET_PRICE = 1000;
    private final int money;

    public UserMoney(String userInput) {
        typeValidate(userInput);
        int convertMoney = Integer.parseInt(userInput);
        moneyValidate(convertMoney);
        this.money = convertMoney;
    }

    private void typeValidate(String number) {
        if (!number.matches("^[0-9]*$")) {
            throw new IllegalArgumentException("[error] is not number");
        }
    }

    private void moneyValidate(int money) {
        if (money % DEFAULT_TICKET_PRICE != 0 || money == 0) {
            throw new IllegalArgumentException("[error] invalid money input");
        }
    }

    public int getTicketCount() {
        return money / DEFAULT_TICKET_PRICE;
    }

    public int getDefaultTicketPrice() {
        return DEFAULT_TICKET_PRICE;
    }
}
