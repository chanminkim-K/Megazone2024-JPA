package com.rubypaper.shopping.biz.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = "orderList")
@Table(name = "S_CUSTOMER")
public class Customer {

    // 회원 아이디
    @Id @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private Long id;

    // 회원 이름
    private String name;

    // 회원 전화번호
    private String phone;

    // 회원 특징 설명
    private String comments;

    // 회원 주소
    @Embedded
    private Address address;

    // 주문 목록
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<Order>();
}
