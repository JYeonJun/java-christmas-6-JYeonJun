package model;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {

    BUTTON_MUSHROOM_SOUP("양송이수프", 6_000, "appetizer"),
    TAPAS("타파스", 5_500, "appetizer"),
    CAESAR_SALAD("시저샐러드", 8_000, "appetizer"),
    CHOCOLATE_CAKE("초코케이크", 15_000, "dessert"),
    ICE_CREAM("아이스크림", 5_000, "dessert"),
    ZERO_COLA("제로콜라", 3_000, "drink"),
    RED_WINE("레드와인", 60_000, "drink"),
    CHAMPAGNE("샴페인", 25_000, "drink"),
    T_BONE_STEAK("티본스테이크", 55_000, "main"),
    BARBECUE_RIBS("바비큐립", 54_000, "main"),
    SEAFOOD_PASTA("해산물파스타", 35_000, "main"),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, "main");

    private final String menuName;
    private final int price;
    private final String category;

    Menu(String menuName, int price, String category) {
        this.menuName = menuName;
        this.price = price;
        this.category = category;
    }

    public String getMenuName() {
        return new String(menuName);
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return new String(category);
    }

    public static Optional<Menu> findByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.menuName.equals(menuName))
                .findFirst();
    }
}
