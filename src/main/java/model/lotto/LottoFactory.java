package model.lotto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

    private static final int LOTTO_SIZE = 6;

    public static Lotto createRandomLotto() {
        return new Lotto(LottoFactory.getRandomLotto());
    }

    private static List<LottoNumber> getRandomLotto() {
        return LottoNumber.getRandomLottoNumberCache()
                .stream()
                .limit(LOTTO_SIZE)
                .sorted(Comparator.comparing(LottoNumber::getLottoNumber))
                .collect(Collectors.toUnmodifiableList());
    }

    public static WinningLotto createWinningLotto(List<LottoNumber> winningLotto) {
        checkLotteryCount(winningLotto);
        checkLotteryOverlap(winningLotto);
        return new WinningLotto(new Lotto(winningLotto));
    }

    private static void checkLotteryCount(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 6개의 숫자를 입력해주세요.");
        }
    }

    private static void checkLotteryOverlap(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 중복이 아닌 숫자를 입력해주세요.");
        }
    }
}
