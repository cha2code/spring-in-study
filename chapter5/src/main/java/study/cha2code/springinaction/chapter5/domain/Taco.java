package study.cha2code.springinaction.chapter5.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 사용자가 생성한 taco를 저장하는 객체
 */

@Data
@Entity
public class Taco {

	// taco 객체 식별 id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //db가 자동으로 생성해주는 값 사용
	private Long id;

	// taco 객체 생성 날짜
	private Date createdAt;

	// 생성 된 taco의 이름
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;

	// taco 생성에 들어간 재료 list
	// 하나의 taco는 많은 Ingredient를 가질 수 있고 하나의 Ingredient는 여러 taco에 포함
	@ManyToMany(targetEntity = Ingredient.class) // N:M 관계
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;

	/**
	 * taco 객체가 저장되기 전에 createdAt 속성을
	 * 현재 날짜와 시간으로 설정하는 메소드
	 */
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}