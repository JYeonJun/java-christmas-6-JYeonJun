package model.discount;

import java.util.List;
import java.util.Optional;
import model.Menu;

public class GiftDiscount implements DiscountStrategy {

    private final String BENEFIT_DETAIL = "증정 이벤트";
    private final static int GIFT_EVENT_MIN_AMOUNT = 120_000;

    private Optional<Menu> giftGoods = Optional.empty();

    @Override
    public void applyDiscount(int totalOrderAmount, List<Menu> menus, int visitDate) {
        setGiftGoods(totalOrderAmount);
    }

    @Override
    public int getDiscountAmount() {
        return giftGoods.map(menu -> getNegativeAmount(menu.getPrice())).orElse(0);
    }

    @Override
    public String getBenefitDetail() {
        return BENEFIT_DETAIL;
    }

    public Optional<Menu> getGiftGoods() {
        return giftGoods;
    }

    private void setGiftGoods(int totalOrderAmount) {

        if (validateGiftEventConditionBeforeDiscount(totalOrderAmount)) {
            this.giftGoods = Optional.of(Menu.CHAMPAGNE);
        }
    }

    private boolean validateGiftEventConditionBeforeDiscount(int totalOrderAmount) {
        return totalOrderAmount >= GIFT_EVENT_MIN_AMOUNT;
    }
}
