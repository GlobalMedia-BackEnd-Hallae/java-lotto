package gmbs.view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class Output {

    private static final int TRUE_VALUE = 1;

    private final DecimalFormat format = new DecimalFormat("###,###.##");

    public void winningNumberDisplay() {
        System.out.println("당첨 숫자 입력 (6자리)");
    }

    public void userMoneyDisplay() {
        System.out.println("구매금액 입력 (티켓 당 1000원)");
    }

    public void bonusNumberDisplay() {
        System.out.println("보너스 번호 입력");
    }

    public void statDisplay(Map<String, Integer> stat) {
        String matches = stat.get("matches").toString();
        String price = format.format(stat.get("price"));
        String count = stat.get("count").toString();
        String bonus = "";
        if (stat.get("requireBonus") == TRUE_VALUE) {
            bonus = ", 보너스 볼 일치";
        }
        System.out.println(matches + "개 일치" + bonus + " (" + price + "원)  - " + count + "개");
    }

    public void ticketDataDisplay(List<Integer> ticketNumbers) {
        System.out.println(ticketNumbers);
    }

    public void profitRatioDisplay(float profitRatio) {
        System.out.println(format.format(profitRatio) + "% 수익");
    }

    public void exceptionDisplay(Exception e) {
        System.out.println(e.getMessage());
    }
}
