package study.cha2code.springinaction.chapter3.repository;

import study.cha2code.springinaction.chapter3.domain.Ingredient;

/**
 * DB에 저장 된 taco 재료(Ingredient)를 가져오거나 저장하는 repository
 */
public interface IngredientRepository {

	// 모든 taco 재료 데이터를 Ingredient 객체에 저장
	Iterable<Ingredient> findAll();

	// id로 하나의 Ingredient 객체를 가져옴
	Ingredient findById(String id);

	// Ingredient 객체를 db에 저장
	Ingredient save(Ingredient ingredient);
}
