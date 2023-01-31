package gmbs.model.generator;

import gmbs.model.vo.LottoNumber;

import java.util.List;

public interface LottoGenerator {

    int LOTTO_LENGTH = 6;
    int MAX = 45;
    int MIN = 1;

    List<LottoNumber> getNumbers();
}
