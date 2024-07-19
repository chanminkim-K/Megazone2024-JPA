package com.rubypaper.biz.domain;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 양방향 참조 - 사원증을 통해 사원증을 소유한 직원 정보에 접근
 */
@Data
@Entity
@Table(name = "S_EMP")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String name;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_CARD_ID")
    private EmployeeCard card;

    public void setEmployeeCard(EmployeeCard card) {
        this.card = card;
        card.setEmployee(this);
    }
}
