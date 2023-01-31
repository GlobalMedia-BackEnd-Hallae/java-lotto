package gmbs.controller;

import gmbs.model.Prize;
import gmbs.model.Ticket;
import gmbs.model.Tickets;
import gmbs.model.UserMoney;
import gmbs.model.generator.TicketGenerator;
import gmbs.model.generator.UserInputLottoGenerator;
import gmbs.model.vo.BonusNumber;
import gmbs.model.vo.LottoNumber;
import gmbs.view.Input;
import gmbs.view.Output;

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
        showTickets(tickets);
        display.winningNumberDisplay();
        UserInputLottoGenerator userInputUserInputLottoGenerator = requestWinningNumbers();
        Ticket winningNumbers = new Ticket(userInputUserInputLottoGenerator);
        display.bonusNumberDisplay();
        LottoNumber bonus = requestBonusNumber(winningNumbers);
        display.matchesDisplay(getStats(tickets.checkMatches(winningNumbers, bonus)));
        display.profitRatioDisplay(tickets.profitRatio(money.getDefaultTicketPrice(), winningNumbers, bonus));
    }

    private UserMoney reqeustUserMoney() {
        UserMoney money;
        while (true) {
            try {
                money = new UserMoney(userInput.scan());
                break;
            } catch (IllegalArgumentException e) {
                display.exceptionDisplay(e);
            }
        }
        return money;
    }

    private Tickets createTickets(UserMoney money) {
        return new Tickets(new TicketGenerator().generate(money.getTicketCount()));
    }

    private void showTickets(Tickets tickets) {
        tickets.getTickets()
                .forEach((ticket -> display.ticketDataDisplay(ticket.getLottoNumberValues())));
    }

    private UserInputLottoGenerator requestWinningNumbers() {
        UserInputLottoGenerator numbers;
        while (true) {
            try {
                numbers = new UserInputLottoGenerator(List.of(userInput.scan().split(",")));
                break;
            } catch (IllegalArgumentException e) {
                display.exceptionDisplay(e);
            }
        }
        return numbers;
    }

    private LottoNumber requestBonusNumber(Ticket winningNumbers) {
        BonusNumber bonus;
        while (true) {
            try {
                bonus = new BonusNumber(winningNumbers, new LottoNumber(userInput.scan()));
                break;
            } catch (IllegalArgumentException e) {
                display.exceptionDisplay(e);
            }
        }
        return bonus.getLottoNumber();
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
}
