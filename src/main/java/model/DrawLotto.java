package model;

public class DrawLotto {

    // 맞은 로또 개수를 알려주는 메서드
    public int drawLotto(Lotto winningNumbers, Lotto lotto) {
        int count = 0;

        for (int index = 0; index < winningNumbers.getLotto().size(); index++) {
            count += compareNumber(winningNumbers.getLotto().get(index), lotto);
        }

        return count;
    }

    private int compareNumber(int number, Lotto lotto) {
        for (int index = 0; index < lotto.getLotto().size(); index++) {
            if (number == lotto.getLotto().get(index)) {
                return 1;
            }
        }

        return 0;
    }
}
