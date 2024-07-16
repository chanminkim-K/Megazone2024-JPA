package com.rubypaper.biz.domain;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * 식별자 클래스
 *
 * 복합키 : 식별자가 될 수 있는 복합키어야만 함.
 *          따라서 식별자 클래스의 복합키에 대한 후보군
 *          id, mailId
 *          id, name
 *          id, mailId, name
 *
 * 식별자 클래스 작성 규칙
 * p.140
 * lombok 어노테이션을 활용해서 구현
 */

@Embeddable // 식별자 클래스로 사용되는 클래스임을 나타냄
@NoArgsConstructor // lombok 기본 생성자
@AllArgsConstructor // lombok
@Getter // lombok
@EqualsAndHashCode // lombok
@ToString
public class Employee12Id implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String mailId;
}
