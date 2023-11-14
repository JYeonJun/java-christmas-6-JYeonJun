package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String EXPECTED_VISIT_DATE_QUESTION = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public int readDate() {

        System.out.println(EXPECTED_VISIT_DATE_QUESTION);
        String input = Console.readLine();

        return 0;
    }
}
