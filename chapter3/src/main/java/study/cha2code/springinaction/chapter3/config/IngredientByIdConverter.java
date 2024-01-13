package study.cha2code.springinaction.chapter3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import study.cha2code.springinaction.chapter3.domain.Ingredient;
import study.cha2code.springinaction.chapter3.repository.IngredientRepository;

/**
 *  String 타입의 Ingredient id를 사용하여
 *  DB에 저장된 데이터를 읽은 후
 *  Incredient 객체로 변환하기 위한 converter 클래스
 *
 * @author cha2jini
 */

@Component
public class IngredientByIdConverter
		implements Converter<String, Ingredient> {
	private IngredientRepository ingredientRepo;

	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	@Override
	public Ingredient convert(String id) {
		return ingredientRepo.findById(id);
	}
}