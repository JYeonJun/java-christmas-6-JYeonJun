package model;

import java.util.Arrays;
import java.util.List;
import model.discount.ChristmasDdayDiscount;
import model.discount.DiscountStrategy;
import model.discount.GiftDiscount;
import model.discount.SpecialDiscount;
import model.discount.WeekdayDiscount;

public class Receipt {

    private final int EVENT_APPLICABLE_MIN_AMOUNT = 10_000;

    private int totalOrderAmount;
    private int totalBenefitAmount;
    private int totalCalculatedAmount;
    private List<DiscountStrategy> discountStrategies = Arrays.asList(
            new ChristmasDdayDiscount(),
            new WeekdayDiscount(),
            new SpecialDiscount(),
            new GiftDiscount()
    );

    public Receipt(List<Menu> menus, int visitDate) {

        calculateTotalOrderAmount(menus);

        if (checkEventEligibility()) {
            discountStrategies.forEach(strategy -> strategy.applyDiscount(totalOrderAmount, menus, visitDate));
            calculateTotalBenefitAmount();
        }

        if (isOrderAmountGreaterThanBenefit()) {
            calculateTotalCalculatedAmount();
        }
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public int getTotalCalculatedAmount() {
        return totalCalculatedAmount;
    }

    public List<DiscountStrategy> getDiscountStrategies() {
        return discountStrategies;
    }

    private boolean checkEventEligibility() {
        return this.totalOrderAmount >= EVENT_APPLICABLE_MIN_AMOUNT;
    }

    private void calculateTotalOrderAmount(List<Menu> menus) {
        this.totalOrderAmount = menus.stream().mapToInt(Menu::getPrice).sum();
    }

    private void calculateTotalBenefitAmount() {
        for (DiscountStrategy discount : discountStrategies) {
            this.totalBenefitAmount += discount.getDiscountAmount();
        }
    }

    private void calculateTotalCalculatedAmount() {
        int benefitAmount = discountStrategies.stream()
                .filter(discount -> !(discount instanceof GiftDiscount))
                .mapToInt(DiscountStrategy::getDiscountAmount)
                .sum();
        this.totalCalculatedAmount = this.totalOrderAmount + benefitAmount;
    }

    private boolean isOrderAmountGreaterThanBenefit() {
        return this.totalOrderAmount > this.totalBenefitAmount;
    }
}