package study.cha2code.springinaction.chapter2.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * 사용자가 생성한 taco를 저장하는 객체
 */

@Data
public class Taco {

	// 생성 된 taco의 이름
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;

	// taco 생성에 들어간 재료 list
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<String> ingredients;
}