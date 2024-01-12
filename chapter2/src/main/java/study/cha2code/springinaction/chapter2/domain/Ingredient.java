package study.cha2code.springinaction.chapter2.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * taco 재료를 정의하는 class
 */

// getter, setter 등을 생성
@Data
// 초기화 되지 않은 final 필드에 대해 생성자를 생성
@RequiredArgsConstructor
public class Ingredient {

	// 재료 class를 참조하기 위한 필드
	private final String id;

	// 재료 이름
	private final String name;

	// 재료별 타입
	private final Type type;

	// 재료별 타입 지정
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
