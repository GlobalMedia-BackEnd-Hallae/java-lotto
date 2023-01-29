package model;

public enum WinningCount {
    THREE(3, 5000, "3개 일치 (5,000원)"),
    FOUR(4, 50000, "4개 일치 (50,000원)"),
    FIVE(5, 1500000, "5개 일치 (1,500,000원)"),
    FIVE_WITH_BONUS(5, 30000000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    SIX(6, 2000000000, "6개 일치 (2,000,000,000원)");

    private final int count;
    private final int price;
    private final String description;

    WinningCount(int count, int price, String description) {
        this.count = count;
        this.price = price;
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
