package christmas;

import controller.ChristmasController;
import java.util.List;
import model.Customer;
import model.Menu;
import model.Receipt;

public class Application {
    private static final ChristmasController christmasController = new ChristmasController();

    public static void main(String[] args) {
        processChristmasEvent();
    }

    private static void processChristmasEvent() {
        christmasController.showWelcomeMessage();

        int expectedVisitDate = christmasController.getExpectedVisitDate();
        List<Menu> orderMenus = christmasController.getUserMenuSelection();

        Customer customer = createCustomer(orderMenus, expectedVisitDate);

        christmasController.showEventRewardsPreview(customer);
    }

    private static Customer createCustomer(List<Menu> orderMenus, int expectedVisitDate) {
        Receipt receipt = christmasController.createReceipt(orderMenus, expectedVisitDate);
        return christmasController.createCustomer(orderMenus, receipt);
    }
}