package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LotteryDrawing {

    private static final int THIRD_PLACE_NUMBER = 2;
    private static final int FAIL_TO_WIN = 5;

    // 당첨 번호와 로또들 비교
    public List<Integer> drawLottery(Lotto winningNumbers, List<Lotto> createdLotto) {
        final List<Integer> winningResult = Arrays.asList(0, 0, 0, 0, 0, 0);
        final List<Integer> winningInformation = getWinningCount();

        for (Lotto lotto : createdLotto) {
            int count = compareAllLotto(winningNumbers, lotto);
            int index = setIndex(count, winningInformation);
            index += checkSecondOrThirdPlace(winningNumbers.getBonusNumber(), lotto, index);
            winningResult.set(index, winningResult.get(index) + 1);
        }

        return winningResult;
    }

    private List<Integer> getWinningCount() {
        return Stream.of(Winning.values())
                .map(m -> m.getCount())
                .collect(Collectors.toList());
    }

    // 당첨 번호들과 로또 번호들 비교
    private int compareAllLotto(Lotto winningNumbers, Lotto lotto) {
        int count = 0;

        for (int lottoNumber : lotto.getLotto()) {
            count += compareLotto(winningNumbers, lottoNumber);
        }

        return count;
    }

    // 당첨 번호들과 로또 번호 비교
    private int compareLotto(Lotto winningNumbers, int lottoNumber) {
        int count = 0;

        for (int winningNumber : winningNumbers.getLotto()) {
            count += compareNumber(winningNumber, lottoNumber);
        }

        return count;
    }

    // 2, 3등을 가리기 위해 보너스 번호와 로또 번호 비교, 공백 제외 8줄
    private int checkSecondOrThirdPlace(int bonusNumber, Lotto lotto, int index) {
        if (index != THIRD_PLACE_NUMBER) {
            return 0;
        }

        int check = 0;

        for (int lottoNumber : lotto.getLotto()) {
            check = Math.max(check, compareNumber(bonusNumber, lottoNumber));
        }

        return check;
    }

    // 당첨 번호와 로또 번호 비교
    private int compareNumber(int winningNumber, int lottoNumber) {
        if (winningNumber == lottoNumber) {
            return 1;
        }

        return 0;
    }

    private int setIndex(int count, List<Integer> winningInformation) {
        int index = FAIL_TO_WIN;

        for (int order = 0; order < winningInformation.size(); order++) {
            index = Math.min(index, compareCount(winningInformation.get(order), count, order));
        }

        return index;
    }

    private int compareCount(int winningCount, int count, int order) {
        if (winningCount == count) {
            return order;
        }

        return FAIL_TO_WIN;
    }
}
