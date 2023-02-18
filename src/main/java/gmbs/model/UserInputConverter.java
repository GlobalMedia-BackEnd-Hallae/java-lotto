package gmbs.model;

public class UserInputConverter {

    private static final String NUMBER_REGEX = "^\\d*$";

    public int convert(String input) {
        validateNumber(input);
        validateNoInput(input);
        return Integer.parseInt(input);
    }

    private void validateNumber(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("[error] not a number");
        }
    }

    private static void validateNoInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(("[error] no input"));
        }
    }
}
