package view;

import static util.CommonUtils.addCommaToPrice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.Badge;
import model.Menu;
import model.discount.DiscountStrategy;

public class OutputView {

    private final static String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final static String EXPECTED_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private final static String ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private final static String MENU_CHOICE = "\n<주문 메뉴>";
    private static final String MENU_CHOICE_FORMAT = "%s %d개";
    private static final String PRE_DISCOUNT_TOTAL_AMOUNT = "\n<할인 전 총주문 금액>";
    private static final String PRE_DISCOUNT_TOTAL_AMOUNT_FORMAT = "%s원";
    private static final String GIFT_MENU = "\n<증정 메뉴>";
    private static final String GIFT_MENU_EXISTS_FORMAT = "%s %d개";
    private static final String NONE = "없음";
    private static final String REWARDS_INFORMATION = "\n<혜택 내역>";
    private static final String REWARDS_INFORMATION_FORMAT = "%s: %s원";
    private static final String TOTAL_BENEFIT = "\n<총혜택 금액>";
    private static final String PRICE_FORMAT = "%s원";
    private static final String EXPECTED_TOTAL_PRICE_AFTER_DISCOUNT = "\n<할인 후 예상 결제 금액>";
    private static final String DECEMBER_BADGE = "\n<12월 이벤트 배지>";

    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printExpectedVisitDate() {
        System.out.println(EXPECTED_VISIT_DATE);
    }

    public static void printOrderMenu() {
        System.out.println(ORDER_MENU);
    }

    public static void printMenuChoices(List<Menu> menus) {
        System.out.println(MENU_CHOICE);

        menus.stream()
                .collect(Collectors.groupingBy(Menu::getMenuName, Collectors.counting()))
                .forEach((menuName, count) ->
                        System.out.println(String.format(MENU_CHOICE_FORMAT, menuName, count)));
    }

    public static void printPreDiscountTotalCount(int totalOrderAmount) {
        System.out.println(PRE_DISCOUNT_TOTAL_AMOUNT);
        String addedCommaToPrice = addCommaToPrice(totalOrderAmount);
        System.out.println(String.format(PRE_DISCOUNT_TOTAL_AMOUNT_FORMAT, addedCommaToPrice));
    }

    public static void printGiftMenu(Optional<Menu> giftMenuOpt) {
        System.out.println(GIFT_MENU);

        giftMenuOpt.ifPresentOrElse(
                menu -> System.out.println(String.format(GIFT_MENU_EXISTS_FORMAT, menu.getMenuName(), 1)),
                () -> System.out.println(NONE)
        );
    }

    public static void printRewardsInformation(List<DiscountStrategy> discountStrategies) {
        System.out.println(REWARDS_INFORMATION);

        List<DiscountStrategy> filteredDiscounts = discountStrategies.stream()
                .filter(discount -> discount.getDiscountAmount() < 0)
                .toList();

        if (filteredDiscounts.isEmpty()) {
            System.out.println(NONE);
        } else {
            filteredDiscounts.forEach(discount -> System.out.println(
                    String.format(REWARDS_INFORMATION_FORMAT,
                            discount.getBenefitDetail(),
                            addCommaToPrice(discount.getDiscountAmount()))));
        }
    }

    public static void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println(TOTAL_BENEFIT);
        System.out.println(String.format(PRICE_FORMAT, addCommaToPrice(totalBenefitAmount)));
    }

    public static void printExpectedTotalPriceAfterDiscount(int totalCalculatedAmount) {
        System.out.println(EXPECTED_TOTAL_PRICE_AFTER_DISCOUNT);
        System.out.println(String.format(PRICE_FORMAT, addCommaToPrice(totalCalculatedAmount)));
    }

    public static void printDecemberEventBadge(Optional<Badge> badgeOpt) {
        System.out.println(DECEMBER_BADGE);
        badgeOpt.ifPresentOrElse(
                badge -> System.out.println(badge.getBadgeName()),
                () -> System.out.println(NONE)
        );
    }
}
