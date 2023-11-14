package model.discount;

import static util.DayOfWeekUtils.isWeekend;

import java.util.List;
import model.Menu;

public class WeekendDiscount implements DiscountStrategy {

    private final String MAIN_MENU_CATEGORY = "main";
    private final int WEEKEND_BASIC_DISCOUNT_AMOUNT = 2_023;

    private int discountAmount;


    @Override
    public void applyDiscount(int totalOrderAmount, List<Menu> menus, int visitDate) {
        if (isWeekend(visitDate)) {
            calculateWeekendDiscount(menus);
        }
    }

    @Override
    public int getDiscountAmount() {
        return discountAmount;
    }

    private void calculateWeekendDiscount(List<Menu> menus) {
        int mainCount = countMainMenu(menus);
        this.discountAmount = getNegativeAmount(mainCount * WEEKEND_BASIC_DISCOUNT_AMOUNT);
    }

    private int countMainMenu(List<Menu> menus) {
        return (int) menus.stream()
                .filter(menu -> MAIN_MENU_CATEGORY.equals(menu.getCategory()))
                .count();
    }
}
