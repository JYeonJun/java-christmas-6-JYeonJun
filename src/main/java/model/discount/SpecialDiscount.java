package model.discount;

import java.util.List;
import model.Menu;

public class SpecialDiscount implements DiscountStrategy {

    private final int SPECIAL_DISCOUNT_AMOUNT = 1_000;
    private final int[] STAR_DATE = {3, 10, 17, 24, 25, 31};

    private int discountAmount;

    @Override
    public void applyDiscount(int totalOrderAmount, List<Menu> menus, int visitDate) {
        if (isStarDate(visitDate)) {
            calculateSpecialDiscount(visitDate);
        }
    }

    @Override
    public int getDiscountAmount() {
        return discountAmount;
    }

    private void calculateSpecialDiscount(int visitDate) {
        this.discountAmount = getNegativeAmount(SPECIAL_DISCOUNT_AMOUNT);
    }

    private boolean isStarDate(int visitDate) {
        boolean result = false;

        for (int d : STAR_DATE) {
            if (visitDate == d) {
                result = true;
            }
        }

        return result;
    }
}
