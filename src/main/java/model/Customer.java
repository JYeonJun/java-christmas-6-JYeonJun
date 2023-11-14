package model;

import java.util.List;
import java.util.Optional;

public class Customer {

    private List<Menu> menus;
    private Receipt receipt;
    private Optional<Badge> badge;

    public Customer(List<Menu> menus, Receipt receipt) {
        this.menus = menus;
        this.receipt = receipt;
        setBadge(receipt.getTotalBenefitAmount());
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Optional<Badge> getBadge() {
        return badge;
    }

    private void setBadge(int totalBenefitAmount) {
        this.badge = Optional.ofNullable(Badge.of(totalBenefitAmount));
    }
}
