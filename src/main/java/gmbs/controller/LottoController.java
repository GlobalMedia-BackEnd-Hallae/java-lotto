package gmbs.controller;

import gmbs.model.*;
import gmbs.view.Input;
import gmbs.view.Output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoController {

    private final Input userInput = new Input();
    private final Output display = new Output();

    public void operate() {
        display.userMoneyDisplay();
        UserMoney money = reqeustUserMoney();
        Tickets tickets = createTickets(money);
        display.ticketDisplay(getTicketsData(tickets));
        display.winningNumberDisplay();
        WinningNumbers userInputWinningNumbers = requestWinningNumbers();
        display.bonusNumberDisplay();
        BonusNumber bonus = requestBonusNumber(userInputWinningNumbers);
        display.matchesDisplay(getStats(tickets.checkMatches(userInputWinningNumbers.getNumbers(), bonus.getBonus())));
        display.profitRatioDisplay(tickets.profitRatio(money.getDefaultTicketPrice(), userInputWinningNumbers.getNumbers(), bonus.getBonus()));
    }

    private UserMoney reqeustUserMoney() {
        UserMoney money;
        while (true) {
            try {
                money = new UserMoney(userInput.scan());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return money;
    }

    private Tickets createTickets(UserMoney money) {
        return new Tickets(new TicketGenerator().generate(money.getTicketCount()));
    }

    private WinningNumbers requestWinningNumbers() {
        WinningNumbers numbers;
        while (true) {
            try {
                numbers = new WinningNumbers(List.of(userInput.scan().split(",")));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return numbers;
    }

    private BonusNumber requestBonusNumber(WinningNumbers winningNumbers) {
        BonusNumber bonus;
        while (true) {
            try {
                bonus = new BonusNumber(winningNumbers, new LottoNumber(userInput.scan()));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return bonus;
    }

    private Map<Integer, Map<String, Integer>> getStats(Map<Prize, Integer> stats) {
        Map<Integer, Map<String, Integer>> winningStats = new HashMap<>();
        stats.keySet().remove(Prize.LOSER);
        for (Map.Entry<Prize, Integer> winningStat : stats.entrySet()) {
            Prize prize = winningStat.getKey();
            winningStats.put(prize.place(), Map.of("price", prize.money(), "matches", prize.matches(), "count", winningStat.getValue()));
        }
        return winningStats;
    }

    private List<Integer> getTicketNumbers(Ticket ticket) {
        List<Integer> numbers = new ArrayList<>();
        for (LottoNumber lottoNumber : ticket.getNumbers()) {
            numbers.add(lottoNumber.getValue());
        }
        return numbers;
    }

    private List<List<Integer>> getTicketsData(Tickets givenTickets) {
        List<List<Integer>> tickets = new ArrayList<>();
        for (Ticket ticket : givenTickets.getTickets()) {
            tickets.add(getTicketNumbers(ticket));
        }
        return tickets;
    }
}
