package study.cha2code.springinaction.chapter3.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *  taco 재료의 정보를 가지고 있는 도메인 객체
 *
 * @author cha2jini
 */

@Data
@RequiredArgsConstructor
public class Ingredient {

	private final String id;
	private final String name;
	private final Type type;

	public enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
