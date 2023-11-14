package model.discount;

import java.util.List;
import model.Menu;

public interface DiscountStrategy {

    void applyDiscount(int totalOrderAmount, List<Menu> menus, int visitDate);
    int getDiscountAmount();
    default int getNegativeAmount(int amount) {
        return amount * -1;
    }
}
