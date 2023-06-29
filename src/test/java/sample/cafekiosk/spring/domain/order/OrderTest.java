package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.order.OrderStatus.INIT;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

class OrderTest {

    // Order 생성 시, totalPrice 계산을 위한 단위테스트
    @DisplayName("주문 생성 시, 상품 리스트에서 주문의 총 금액을 계산한다.")
    @Test
    void calculateTotalPrice()
    {
        // given
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        assertThat(order.getTotalPrice()).isEqualTo(3000);
    }

    @DisplayName("주문 생성 시, 주문 상태는 INIT이다.")
    @Test
    void init()
    {
        // given
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        assertThat(order.getOrderStatus()).isEqualTo(INIT);
        assertThat(order.getOrderStatus()).isEqualByComparingTo(INIT);
    }

    // Order 생성 시, totalPrice 계산을 위한 단위테스트
    @DisplayName("주문 생성 시, 주문 등록 시간을 기록한다.")
    @Test
    void registeredDateTime()
    {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        // when
        Order order = Order.create(products, registeredDateTime);

        // then
        assertThat(order.getRegisteredDateTime()).isEqualTo(registeredDateTime);
    }


    // given 단계에서 필요한 상품을 만들어 줄 메서드
    private Product createProduct(String ProductNumber, int price) {
        return Product.builder()
                .type(HANDMADE)
                .productNumber(ProductNumber)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .price(price)
                .build();
    }
}