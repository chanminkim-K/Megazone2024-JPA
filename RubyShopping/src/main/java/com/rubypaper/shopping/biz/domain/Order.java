package com.rubypaper.shopping.biz.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.exception.DataException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "S_ORDER")
@Data
@NoArgsConstructor
@ToString(exclude = {"customer", "searcgCustomerName", "searchOrderStatus", "itemList"})
public class Order {

    // 주문 아이디
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    // 회원 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    // 주문 상태
    @Enumerated (EnumType.STRING)
    private OrderStatus status;

    // 주문 날짜
    private Date orderDate;

    /** 검색 관련 정보 */
    // 검색할 회원 이름
    @Transient
    private String searchCustomerName;

    // 검색할 주문 상태
    @Transient
    private OrderStatus searchOrderStatus;

    // 주문내역 목록
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> itemList = new ArrayList<>();

    // 주문 생성자 : 주문한 회원 정보를 설정한다.
    public Order(Customer customer, Item item) {
        setCustomer(customer);
        addItem(item);
        this.status = OrderStatus.ORDER; // 주문 생성 시 상태는 ORDER
        this.orderDate = new Date();
    }


    // 회원 설정 시에 회원 쪽에도 양방향 참조 설정
    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.getOrderList().add(this);
    }

    // 주문상품 설정 시에 주문상품 쪽에도 양방향 설정
    private void addItem(Item item) {
        itemList.add(item);
        item.setOrder(this);
    }

    // 주문 취소 처리
    public void orderCancel() {
        this.setStatus(OrderStatus.CANCEL);
        for (Item item : itemList) {
            item.restoreStock();
        }
    }
}
