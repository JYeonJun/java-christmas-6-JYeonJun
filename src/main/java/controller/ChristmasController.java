package controller;

import static util.ErrorMessageVO.*;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Optional;
import model.Customer;
import model.Menu;
import model.Receipt;
import model.discount.GiftDiscount;
import service.MenuService;
import model.discount.DiscountStrategy;
import util.CommonUtils;
import view.OutputView;

public class ChristmasController {

    private final int DECEMBER_START_DATE = 1;
    private final int DECEMBER_LAST_DATE = 31;
    private final MenuService menuService = new MenuService();

    public void showWelcomeMessage() {

        OutputView.printWelcomeMessage();
    }

    public int getExpectedVisitDate() {

        OutputView.printExpectedVisitDate();

        while (true) {
            try {
                String expectedVisitDateStr = Console.readLine();

                int expectedVisitDate = CommonUtils.stringToInt(expectedVisitDateStr);
                validateExpectedVisitDate(expectedVisitDate);

                return expectedVisitDate;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Menu> getUserMenuSelection() {
        OutputView.printOrderMenu();

        while (true) {
            try {
                String orderMenu = Console.readLine();
                List<Menu> menus = menuService.validateAndCreateMenuList(orderMenu);
                return menus;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Receipt createReceipt(List<Menu> menus, int visitDate) {
        return new Receipt(menus, visitDate);
    }

    public Customer createCustomer(List<Menu> menus, Receipt receipt) {
        return new Customer(menus, receipt);
    }

    public void showEventRewardsPreview(Customer customer) {

        showMenuChoices(customer);
        showPreDiscountTotalAmount(customer);
        showGiftMenu(customer);
        showRewardsInformation(customer);
        showTotalBenefitAmount(customer);
        showExpectedTotalPriceAfterDiscount(customer);
        showDecemberEventBadge(customer);
    }

    private void validateExpectedVisitDate(int expectedVisitDate) {

        if (expectedVisitDate < DECEMBER_START_DATE || expectedVisitDate > DECEMBER_LAST_DATE) {
            throw new IllegalArgumentException(INVALID_DECEMBER_DATE_EXCEPTION);
        }
    }

    private void showMenuChoices(Customer customer) {
        List<Menu> menus = customer.getMenus();
        OutputView.printMenuChoices(menus);
    }

    private void showPreDiscountTotalAmount(Customer customer) {
        int totalOrderAmount = customer.getReceipt().getTotalOrderAmount();
        OutputView.printPreDiscountTotalCount(totalOrderAmount);
    }

    private void showGiftMenu(Customer customer) {
        Optional<Menu> giftMenu = customer.getReceipt().getDiscountStrategies().stream()
                .filter(discount -> discount instanceof GiftDiscount)
                .map(discount -> ((GiftDiscount) discount).getGiftGoods())
                .findFirst()
                .orElse(Optional.empty());

        OutputView.printGiftMenu(giftMenu);
    }

    private void showRewardsInformation(Customer customer) {
        List<DiscountStrategy> discountStrategies = customer.getReceipt().getDiscountStrategies();
        OutputView.printRewardsInformation(discountStrategies);
    }

    private void showTotalBenefitAmount(Customer customer) {
        int totalBenefitAmount = customer.getReceipt().getTotalBenefitAmount();
        OutputView.printTotalBenefitAmount(totalBenefitAmount);
    }

    private void showExpectedTotalPriceAfterDiscount(Customer customer) {
        int totalCalculatedAmount = customer.getReceipt().getTotalCalculatedAmount();
        OutputView.printExpectedTotalPriceAfterDiscount(totalCalculatedAmount);
    }

    private void showDecemberEventBadge(Customer customer) {
        OutputView.printDecemberEventBadge(customer.getBadge());
    }
}