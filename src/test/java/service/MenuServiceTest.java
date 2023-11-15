package service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuServiceTest {

    private MenuService menuService;

    @BeforeEach
    void setUp() {
        menuService = new MenuService();
    }

    @Test
    void 메뉴_주문_성공_테스트() {
        String orderMenu = "양송이수프-2,시저샐러드-3";

        List<Menu> result = menuService.validateAndCreateMenuList(orderMenu);

        assertThat(result).hasSize(5);
    }

    @Test
    void 메뉴_주문_동일메뉴_주문_예외_테스트() {
        String orderMenu = "아이스크림-2,아이스크림-3";

        assertThatThrownBy(() -> menuService.validateAndCreateMenuList(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 메뉴_주문_최소주문_예외_테스트() {
        String orderMenu = "양송이수프-0,아이스크림-3";

        assertThatThrownBy(() -> menuService.validateAndCreateMenuList(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 메뉴_주문_없는_메뉴_주문_예외_테스트() {
        String orderMenu = "메뉴1-0,아이스크림-3";

        assertThatThrownBy(() -> menuService.validateAndCreateMenuList(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 메뉴_주문_음료주문_예외_테스트() {
        String orderMenu = "제로콜라-2,레드와인-3";

        assertThatThrownBy(() -> menuService.validateAndCreateMenuList(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 음료만 주문할 수 없습니다.");
    }

    @Test
    void 메뉴_주문_최대주문_예외_테스트() {
        String orderMenu = "제로콜라-11,티본스테이크-10";

        assertThatThrownBy(() -> menuService.validateAndCreateMenuList(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 주문 가능한 최대 메뉴 개수를 초과하였습니다.");
    }

}