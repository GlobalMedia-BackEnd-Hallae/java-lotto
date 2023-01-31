package gmbs.view;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Output {

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

    public void matchesDisplay(Map<Integer, Map<String, Integer>> stats) {
        List<Integer> places = stats.keySet()
                .stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        for (int place : places) {
            System.out.println(stat(stats.get(place), place));
        }
    }

    public String stat(Map<String, Integer> stats, int place) {
        String matches = stats.get("matches").toString();
        String price = format.format(stats.get("price"));
        String count = stats.get("count").toString();
        if (place == 2) {
            return matches + "개 일치, 보너스 볼 일치 (" + price + "원)  - " + count + "개";
        }
        return matches + "개 일치 (" + price + "원)  - " + count + "개";
    }

    public void ticketDisplay(List<List<Integer>> ticketData) {
        ticketData.forEach(System.out::println);
    }

    public void profitRatioDisplay(float profitRatio) {
        System.out.println(format.format(profitRatio * 100) + "% 수익");
    }

    public void exceptionDisplay(Exception e) {
        System.out.println(e.getMessage());
    }
}
