package study.cha2code.springinaction.chapter5.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import study.cha2code.springinaction.chapter5.domain.Ingredient;
import study.cha2code.springinaction.chapter5.repository.IngredientRepository;

import java.util.Optional;

/**
 *  String 타입의 Ingredient id를 사용하여
 *  DB에 저장된 데이터를 읽은 후
 *  Incredient 객체로 변환하기 위한 converter 클래스
 *
 */

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

	private IngredientRepository ingredientRepo;

	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	/**
	 * JPA에서 자동으로 구현 된 findById() 메소드 실행 후
	 * DB에서 Ingredient 정보를 찾지 못해 null이 반환될 경우에 대한 처리
	 */
	@Override
	public Ingredient convert(String id) {

		Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
		return optionalIngredient.isPresent() ?
		       optionalIngredient.get() : null;
	}
}