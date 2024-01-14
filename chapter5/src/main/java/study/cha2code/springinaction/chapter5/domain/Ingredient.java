package study.cha2code.springinaction.chapter5.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 *  taco 재료의 정보를 가지고 있는 도메인 객체
 *
 */

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity //JPA 개체로 선언
public class Ingredient {

	@Id // primary key
	private final String id;
	private final String name;
	private final Type type;

	public enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}

/*

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
- JPA에서 entity는 인자가 없는 생성자를 가져야 하기 때문에 @NoArgsConstructor 사용

- 이 프로젝트에서는 인자 없는 생성자를 가지지 않기 위해
  access = AccessLevel.PRIVATE으로 설정, 클래스 외부에서 사용하지 못하게 함

- 초기화가 필요한 final 속성들로 인해 force = true로 설정
  (lombok에서 자동 생성한 생성자에서 속성들을 null로 설정함)

 */

/*

@Data
- 인자가 있는 생성자를 자동으로 추가
- @NoArgsConstructor를 사용하면 생성자가 제거됨
- @RequiredArgsConstructor 추가 시 인자 없는 생성자와 인자 있는 생성자 모두를 가질 수 있음

 */