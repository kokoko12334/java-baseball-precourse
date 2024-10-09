package baseball;

public class InputValidator {

    public static boolean isValidInput(String userInput) {

        return (userInput.matches("\\d{3}") &&
                userInput.chars().noneMatch(ch -> ch == '0')) ||
                userInput.equals("1") ||
                userInput.equals("2");
    }

    public static void validateOrThrowError(String userInput){
        // 사용자 입력 검증
        if (!isValidInput(userInput)) {
            throw new IllegalArgumentException();
        }

    }

}
