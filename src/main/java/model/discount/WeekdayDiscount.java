package model.discount;

import static util.DayOfWeekUtils.isWeekday;

import java.util.List;
import model.Menu;

public class WeekdayDiscount implements DiscountStrategy {

    private final String BENEFIT_DETAIL = "평일 할인";
    private final String DESSERT_CATEGORY = "dessert";
    private final int WEEKDAY_BASIC_DISCOUNT_AMOUNT = 2_023;

    private int discountAmount;

    @Override
    public void applyDiscount(int totalOrderAmount, List<Menu> menus, int visitDate) {
        if (isWeekday(visitDate)) {
            calculateWeekdayDiscount(menus);
        }
    }

    @Override
    public int getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public String getBenefitDetail() {
        return BENEFIT_DETAIL;
    }

    private void calculateWeekdayDiscount(List<Menu> menus) {
        int dessertCount = countDessertMenu(menus);
        this.discountAmount = getNegativeAmount(dessertCount * WEEKDAY_BASIC_DISCOUNT_AMOUNT);
    }

    private int countDessertMenu(List<Menu> menus) {
        return (int) menus.stream()
                .filter(menu -> DESSERT_CATEGORY.equals(menu.getCategory()))
                .count();
    }
}
