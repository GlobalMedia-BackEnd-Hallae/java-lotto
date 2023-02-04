package model;

public class LottoNumber {

    private static final int LOTTO_NUMBER_MIN_VALUE = 1;
    private static final int LOTTO_NUMBER_MAX_VALUE = 45;

    private final int lottoNumber;

    public LottoNumber(int number) {
        if (checkNumberRange(number)) {
            throw new IllegalArgumentException("[Error] 입력하신 수가 로또 숫자의 범위에 포함되지 않습니다.");
        }

        this.lottoNumber = number;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    private boolean checkNumberRange(int number) {
        return number < LOTTO_NUMBER_MIN_VALUE || number > LOTTO_NUMBER_MAX_VALUE;
    }

    public int drawLottoNumberWithWinningNumbers(Lotto winningNumbers) {
        return winningNumbers.compareLottoNumberWithWinningNumber(this.lottoNumber);
    }

    public int drawLottoNumberWithBonusNumber(LottoNumber bonusNumber) {
        return bonusNumber.isSameNumber(this.lottoNumber);
    }

    public int isSameNumber(int lottoNumber) {
        if (this.lottoNumber == lottoNumber) {
            return 1;
        }

        return 0;
    }
}
