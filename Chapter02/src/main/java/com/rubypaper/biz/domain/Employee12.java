
package com.rubypaper.biz.domain;

import jakarta.persistence.*;
import lombok.Data;


/*
 * 식별자
 *
 * p.138
 *
 * 식별자 클래스를 활용한 엔티티 작성
 *
 * Employee12 : 엔티티 클래스
 * Employee12Id : 식별자 클래스
 *                  복합키에 해당하는 컬럼의 mailId, name 인 두 가지 멤버로 구성
 *                  ( 논리적인 관계가 안됨 왜냐면, 식별자가 될 수 없기 때문 )
 *
 *                  PK : id (식별자)
 *                  unique constraints : mailId, name
 *                                      (식별자가 아님, 추가적인 unique 조건일 뿐)
 *
 *                  복합키 : 식별자가 될 수 있는 복합키어야만 함.
 *                          따라서 식별자 클래스의 복합키에 대한 후보군
 *                          id, mailId
 *                          id, name
 *                          id, mailId, name
 *                  식별자 클래스 Employee12Id 객체를 생성 후,
 *                  직접 Employee12 객체의 empId 멤버에 초기화 값으로 사용
 * Employee12ServiceClient
 *
 *
 */

@Data
@Entity
@Table(name = "Employee3")
public class Employee12 {
    @Id
    private Employee12Id id; // 식별자 클래스(복합키) 객체 멤버

    private String name;
}
