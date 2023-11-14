package model.discount;

import java.util.List;
import model.Menu;

public class ChristmasDdayDiscount implements DiscountStrategy {

    private final int CHRISTMAS_BASIC_DISCOUNT_AMOUNT = 1_000;
    private final int CHRISTMAS_PER_DAY_DISCOUNT_AMOUNT = 100;
    private final int CHRISTMAS_EVENT_START_DATE = 1;
    private final int CHRISTMAS_EVENT_END_DATE = 25;

    private int discountAmount;

    @Override
    public void applyDiscount(int totalOrderAmount, List<Menu> menus, int visitDate) {
        if (isDuringChristmasDiscountPeriod(visitDate)) {
            calculateChristmasDdayDiscount(visitDate);
        }
    }

    @Override
    public int getDiscountAmount() {
        return discountAmount;
    }

    private boolean isDuringChristmasDiscountPeriod(int visitDate) {
        return visitDate >= CHRISTMAS_EVENT_START_DATE && visitDate <= CHRISTMAS_EVENT_END_DATE;
    }

    private void calculateChristmasDdayDiscount(int visitDate) {
        this.discountAmount = getNegativeAmount(
                CHRISTMAS_BASIC_DISCOUNT_AMOUNT + ((visitDate - 1) * CHRISTMAS_PER_DAY_DISCOUNT_AMOUNT));
    }
}