package gmbs.util;

public abstract class LottoNumberValidator {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    protected void validateNumberRangeIn(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 입력 로또 번호는 1~45 사이의 숫자여야 합니다");
        }
    }
}
