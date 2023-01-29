package view;

import model.WinningCount;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultOutput {


    final private StringBuilder stringBuilder = new StringBuilder();
    public void resultOutput(List<Integer> result) {
        final String connection = " - ";
        final String count = "개\n";
        List<String> description = description();

        System.out.println("당첨 통계");
        System.out.println("---");

        for (int index = 0; index < description.size(); index++) {
            stringBuilder.append(description.get(index));
            stringBuilder.append(connection);
            stringBuilder.append(result.get(index));
            stringBuilder.append(count);
        }

        System.out.println(stringBuilder);
    }

    private List<String> description() {
        return  Stream.of(WinningCount.values())

                .map(m -> m.getDescription())

                .collect(Collectors.toList());
    }
}
