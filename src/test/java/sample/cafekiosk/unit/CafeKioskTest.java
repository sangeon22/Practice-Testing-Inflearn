package sample.cafekiosk.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverages.Americano;
import sample.cafekiosk.unit.beverages.Latte;
import sample.cafekiosk.unit.oder.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeKioskTest {

    @DisplayName("음료 1개를 추가하면 주문 목록에 담긴다.")
    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void addSeveralBeverages() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //when
        cafeKiosk.add(americano, 2);

        //then
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);
    }

    @Test
    void addZeroBeverages() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //when

        //then
        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
    }

    @Test
    void createOrder() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //when
        cafeKiosk.add(americano, 1);
        Order order = cafeKiosk.createOrder(LocalDateTime.of(2023, 6, 8, 10, 0));

        //then
        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void createOrderOutsideOpenTime() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //when
        cafeKiosk.add(americano, 1);

        //then
        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2023, 6, 8, 9, 59)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 가능 시간이 아닙니다. 관리자에게 문의하세요.");
    }

    @DisplayName("주문 목록에 담긴 상품들의 총 금액을 계산할 수 있다.")
    @Test
    void calculateTotalPrice() {
        // given - 테스트에 필요한 준비(객체, 상태, 값)
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latte);

        // when(메서드를 호출하는 단계, 시나리오를 수행하는 단계)
        int totalPrice = cafeKiosk.calculateTotalPrice();

        // then(검증하는 단계)
        assertThat(totalPrice).isEqualTo(8500);
    }

    @DisplayName("")
    @Test
    void test()
    {
        // given


        // when


        // then

    }
//    @Test
//    void remove() {
//        // given
//        CafeKiosk cafeKiosk = new CafeKiosk();
//        Americano americano = new Americano();
//
//        cafeKiosk.add(americano);
//        assertThat(cafeKiosk.getBeverages()).hasSize(1);
//
//        // when
//        cafeKiosk.remove(americano);
//
//        // then
//        assertThat(cafeKiosk.getBeverages()).hasSize(0);
//        assertThat(cafeKiosk.getBeverages()).isEmpty();
//    }
//
//
//    @Test
//    void clear() {
//        // given
//        CafeKiosk cafeKiosk = new CafeKiosk();
//        Americano americano = new Americano();
//        Latte latte = new Latte();
//
//        cafeKiosk.add(americano);
//        cafeKiosk.add(latte);
//        assertThat(cafeKiosk.getBeverages()).hasSize(2);
//
//        // when
//        cafeKiosk.clear();
//
//        // then
//        assertThat(cafeKiosk.getBeverages()).isEmpty();
//    }

}