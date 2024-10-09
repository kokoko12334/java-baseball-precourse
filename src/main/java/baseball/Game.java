package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Game {

    static List<Integer> randomNumbers;

    public void gamePlay(){

        randomNumbers = generateNewRandomNumbers();
        List<Integer> userInput;
        String result;

        while (true) {

            userInput = getUserInputAndValidate();

            if (userInput.size() == 1 && userInput.get(0) == 1) {
                randomNumbers = generateNewRandomNumbers();
                continue;
            }

            if (userInput.size() == 1 && userInput.get(0) == 2) {
                break;
            }

            result = checkMatch(userInput);

            System.out.println(result);
            if (result.equals("3스트라이크")) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            }

        }

    }

    private static List<Integer> getUserInputAndValidate() {
        System.out.print("숫자를 입력해주세요: ");
        String userInput = Console.readLine();

        // 사용자 입력 검증
        InputValidator.validateOrThrowError(userInput);

        // 정수형 ArrayList로 변환
        List<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < userInput.length(); i++) {
            intArray.add(Character.getNumericValue(userInput.charAt(i)));
        }
        return intArray;
    }

    private static List<Integer> generateNewRandomNumbers() {

        List<Integer> randomNumbers = new ArrayList<>();
        int number;

        while (randomNumbers.size() < 3){
            number = Randoms.pickNumberInRange(1, 9);
            if (!randomNumbers.contains(number)){
                randomNumbers.add(number);
            }
        }

//        randomNumbers.forEach(num -> System.out.println(num + " "));
        return randomNumbers;
    }

    private static String checkMatch(List<Integer> userInput) {

        List<String> result = new ArrayList<>();
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < userInput.size(); i++) {
            if (userInput.get(i).equals(randomNumbers.get(i))) {
                strike++;
            } else if (randomNumbers.contains(userInput.get(i))) {
                ball++;
            }
        }

        if (strike == 0 & ball == 0) {
            return "낫싱";
        }

        if (ball != 0) {
            String ballString = String.valueOf(ball) + "볼";
            result.add(ballString);
        }

        if (strike != 0) {
            String strikeString = String.valueOf(strike) + "스트라이크";
            result.add(strikeString);
        }

        return String.join(" ", result);
    }

}
