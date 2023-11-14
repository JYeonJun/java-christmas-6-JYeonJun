package model.discount;

import java.util.List;
import model.Menu;

public class GiftDiscount implements DiscountStrategy {

    private final static int GIFT_EVENT_MIN_AMOUNT = 120_000;

    private Menu giftGoods;

    @Override
    public void applyDiscount(int totalOrderAmount, List<Menu> menus, int visitDate) {
        calculateGiftEvent(totalOrderAmount);
    }

    @Override
    public int getDiscountAmount() {
        return getNegativeAmount(giftGoods.getPrice());
    }

    public Menu getGiftGoods() {
        return giftGoods;
    }

    private void calculateGiftEvent(int totalOrderAmount) {
        if (validateGiftEventConditionBeforeDiscount(totalOrderAmount)) {
            this.giftGoods = Menu.CHAMPAGNE;
        }
    }

    private boolean validateGiftEventConditionBeforeDiscount(int totalOrderAmount) {
        return totalOrderAmount >= GIFT_EVENT_MIN_AMOUNT;
    }
}
