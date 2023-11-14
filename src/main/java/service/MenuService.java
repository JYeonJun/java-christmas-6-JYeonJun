package service;

import static util.ErrorMessageVO.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import model.Menu;
import util.CommonUtils;

public class MenuService {
    private final int MAX_MENU_COUNT = 20;
    private final String DESSERT_CATEGORY = "dessert";

    public List<Menu> validateAndCreateMenuList(String orderMenu) {
        String[] menuSelections = orderMenu.split(",");
        Set<String> menuSet = new HashSet<>();
        int totalMenuCount = 0;
        boolean onlyDrinksOrdered = true;
        List<Menu> menus = new ArrayList<>();

        for (String selection : menuSelections) {
            validateMenuFormat(selection);

            String[] menuAndCount = selection.split("-");
            String menuName = menuAndCount[0];
            int count = CommonUtils.stringToInt(menuAndCount[1]);

            validateMenuCount(count);
            validateMenuDuplication(menuSet, menuName);

            Menu menu = getMenuByName(menuName);

            if (!isDrink(menu)) {
                onlyDrinksOrdered = false;
            }

            addMenusToList(menus, menu, count);
            totalMenuCount += count;
        }

        validateTotalMenuCount(totalMenuCount);
        validateIfOnlyDrinksOrdered(onlyDrinksOrdered);

        return menus;
    }

    private void validateMenuFormat(String menuSelection) {
        if (!menuSelection.matches(".+-\\d+")) {
            throw new IllegalArgumentException(INVALID_MENU_FORMAT_EXCEPTION);
        }
    }

    private void validateMenuCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(INVALID_MENU_COUNT_EXCEPTION);
        }
    }

    private void validateMenuDuplication(Set<String> menuSet, String menuName) {
        if (menuSet.contains(menuName)) {
            throw new IllegalArgumentException(DUPLICATE_MENU_EXCEPTION);
        }
        menuSet.add(menuName);
    }

    private Menu getMenuByName(String menuName) {
        Optional<Menu> menuOpt = Menu.findByName(menuName);
        return menuOpt.orElseThrow(() -> new IllegalArgumentException(INVALID_MENU_SELECTION_EXCEPTION));
    }

    private boolean isDrink(Menu menu) {
        return menu.getCategory().equals(DESSERT_CATEGORY);
    }

    private void addMenusToList(List<Menu> menus, Menu menu, int count) {
        for (int i = 0; i < count; i++) {
            menus.add(menu);
        }
    }

    private void validateTotalMenuCount(int totalMenuCount) {
        if (totalMenuCount > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(EXCEEDED_MAX_MENU_COUNT_EXCEPTION);
        }
    }

    private void validateIfOnlyDrinksOrdered(boolean onlyDrinksOrdered) {
        if (onlyDrinksOrdered) {
            throw new IllegalArgumentException(ONLY_DRINKS_ORDERED_EXCEPTION);
        }
    }
}
