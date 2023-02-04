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

    public void operate() {
        display.userMoneyDisplay();
        UserMoney money = reqeustUserMoney();
        Tickets tickets = createTickets(money);
        showTickets(tickets);
        Winner winner = requestWinner();
        showStats(tickets.checkMatches(winner));
        float profitRatio = tickets.getProfitRatio(money.getDefaultTicketPrice(), winner);
        display.profitRatioDisplay(profitRatio);
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

    private Winner requestWinner() {
        Ticket winningTicket;
        LottoNumber bonus;
        while (true) {
            try {
                winningTicket = requestWinningTicket();
                bonus = requestBonus();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return new Winner(winningTicket, bonus);
    }

    private LottoNumber requestBonus() {
        display.bonusNumberDisplay();
        return new LottoNumber(userInput.scan());
    }

    private Ticket requestWinningTicket() {
        display.winningNumberDisplay();
        UserInputLottoGenerator numbers = new UserInputLottoGenerator(List.of(userInput.scan().split(",")));
        return new Ticket(numbers);
    }

    private void showStats(Map<Prize, Integer> stats) {
        stats.keySet().remove(Prize.LOSER);
        for (Map.Entry<Prize, Integer> winningStat : stats.entrySet()) {
            Prize prize = winningStat.getKey();
            display.statDisplay(Map.of("price", prize.money(), "matches", prize.matches(), "count", winningStat.getValue(), "requireBonus", convertBonus(prize.requireBonus())));
        }
    }

    private int convertBonus(boolean requireBonus) {
        if (requireBonus) {
            return 1;
        }
        return 0;
    }
}
