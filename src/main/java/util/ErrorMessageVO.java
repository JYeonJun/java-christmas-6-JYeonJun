package util;

public interface ErrorMessageVO {

    String INVALID_NUMBER_FORMAT_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    String INVALID_DECEMBER_DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    String INVALID_MENU_FORMAT_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    String INVALID_MENU_SELECTION_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    String DUPLICATE_MENU_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    String INVALID_MENU_COUNT_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    String EXCEEDED_MAX_MENU_COUNT_EXCEPTION = "[ERROR] 주문 가능한 최대 메뉴 개수를 초과하였습니다.";
    String ONLY_DRINKS_ORDERED_EXCEPTION = "[ERROR] 음료만 주문할 수 없습니다.";
}
