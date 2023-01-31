package gmbs.model;

import gmbs.model.vo.LottoNumber;

import java.util.List;

public interface NumberGenerator {

    int LOTTO_LENGTH = 6;
    int MAX = 45;
    int MIN = 1;

    List<LottoNumber> getNumbers();
}
