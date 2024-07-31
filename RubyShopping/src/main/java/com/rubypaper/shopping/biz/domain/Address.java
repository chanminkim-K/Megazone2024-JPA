package com.rubypaper.shopping.biz.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {

    // 도시
    private String city;

    // 도로명
    private String rodaName;

    // 우편번호
    private String zipCode;
}
