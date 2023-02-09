package gmbs.controller;

import gmbs.model.*;
import gmbs.model.generator.TicketGenerator;
import gmbs.model.generator.UserInputLottoGenerator;
import gmbs.model.vo.LottoNumber;
import gmbs.view.Input;
import gmbs.view.Output;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoController {

    private final Input userInput = new Input();
    private final Output display = new Output();
    private final ProfitCalculator calculator = new ProfitCalculator();
    private final UserInputConverter converter = new UserInputConverter();

    public void operate() {
        UserMoney money = reqeustUserMoney();
        Tickets tickets = createTickets(money);
        showTickets(tickets);
        WinningNumbers winningNumbers = requestWinningNumbers();
        Map<Prize, Integer> stats = tickets.checkMatches(winningNumbers);
        showStats(stats);
        showProfitRatio(money, stats);
    }

    private UserMoney reqeustUserMoney() {
        display.userMoneyDisplay();
        UserMoney money;
        while (true) {
            try {
                int userInputMoney = converter.convert(userInput.scan());
                money = new UserMoney(userInputMoney);
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

    private WinningNumbers requestWinningNumbers() {
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
        return new WinningNumbers(winningTicket, bonus);
    }

    private Ticket requestWinningTicket() {
        display.winningNumberDisplay();
        List<Integer> userInputNumbers = Stream.of(userInput.scan().split(","))
                .map(converter::convert)
                .collect(Collectors.toUnmodifiableList());
        UserInputLottoGenerator numbers = new UserInputLottoGenerator(userInputNumbers);
        return new Ticket(numbers);
    }

    private LottoNumber requestBonus() {
        display.bonusNumberDisplay();
        int userInputBonus = converter.convert(userInput.scan());
        return LottoNumber.from(userInputBonus);
    }

    private void showStats(Map<Prize, Integer> stats) {
        for (Prize prize : Prize.values()) {
            if (prize == Prize.LOSER) {
                continue;
            }
            display.statDisplay(Map.of("price", prize.money(), "matches", prize.matches(), "count", stats.get(prize), "requireBonus", convertBonusData(prize.requireBonus())));
        }
    }

    private int convertBonusData(boolean requireBonus) {
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
