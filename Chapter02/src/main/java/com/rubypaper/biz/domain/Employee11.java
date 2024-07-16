
package com.rubypaper.biz.domain;

import jakarta.persistence.*;
import lombok.Data;


/*
 * 식별자
 *
 * JPA 가 관리하는 엔터티는 @Id로 지정한 식별자 변수를 통해서 식별되었음.
 * 테이블의 기본키와 엔터티의 식별자 변수를 매핑해서 유일한 엔터티 객체를 식별하고
 * 관리.
 *
 * 식별자 생성 전략( 중요 )
 * p.118 참고
 *
 * Sequence 전략을 사용한 실습
 *
 */

@Data
@Entity
@Table(name = "Employee11")
public class Employee11 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
