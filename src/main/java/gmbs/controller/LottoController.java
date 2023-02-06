package gmbs.controller;

import gmbs.model.*;
import gmbs.model.generator.TicketGenerator;
import gmbs.model.generator.UserInputLottoGenerator;
import gmbs.model.vo.LottoNumber;
import gmbs.view.Input;
import gmbs.view.Output;

import java.util.List;
import java.util.Map;

public class LottoController {

    private final Input userInput = new Input();
    private final Output display = new Output();
    private final ProfitCalculator calculator = new ProfitCalculator();

    public void operate() {
        UserMoney money = reqeustUserMoney();
        Tickets tickets = createTickets(money);
        showTickets(tickets);
        Winner winner = requestWinner();
        Map<Prize, Integer> stats = tickets.checkMatches(winner);
        showStats(stats);
        showProfitRatio(money, stats);
    }

    private UserMoney reqeustUserMoney() {
        display.userMoneyDisplay();
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

    private Winner requestWinner() {
        Ticket winningTicket;
        LottoNumber bonus;
        while (true) {
            try {
                winningTicket = requestWinningTicket();
                bonus = requestBonus();
                break;
            } catch (IllegalArgumentException e) {
                display.exceptionDisplay(e);
            }
        }
        return new Winner(winningTicket, bonus);
    }

    private Ticket requestWinningTicket() {
        display.winningNumberDisplay();
        UserInputLottoGenerator numbers = new UserInputLottoGenerator(List.of(userInput.scan().split(",")));
        return new Ticket(numbers);
    }

    private LottoNumber requestBonus() {
        display.bonusNumberDisplay();
        return LottoNumber.from(userInput.scan());
    }

    private void showStats(Map<Prize, Integer> stats) {
        for (Prize prize : Prize.values()) {
            if (prize == Prize.LOSER) {
                continue;
            }
            display.statDisplay(Map.of("price", prize.money(), "matches", prize.matches(), "count", stats.get(prize), "requireBonus", convertBonus(prize.requireBonus())));
        }
    }

    private int convertBonus(boolean requireBonus) {
        if (requireBonus) {
            return 1;
        }
        return 0;
    }

    private void showProfitRatio(UserMoney money, Map<Prize, Integer> stats) {
        float profitRatio = calculator.calculate(money, stats);
        display.profitRatioDisplay(profitRatio);
    }
}
