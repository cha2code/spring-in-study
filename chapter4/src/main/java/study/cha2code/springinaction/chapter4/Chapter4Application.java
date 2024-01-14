package study.cha2code.springinaction.chapter4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import study.cha2code.springinaction.chapter4.domain.Ingredient;
import study.cha2code.springinaction.chapter4.domain.Ingredient.Type;
import study.cha2code.springinaction.chapter4.repository.IngredientRepository;

@SpringBootApplication
public class Chapter4Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter4Application.class, args);
	}

	/**
	 * dataLoader() 메소드에서 Ingredient 데이터를 DB에 미리 저장하기 위한 Bean
	 */
	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
				repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
				repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
				repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
				repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
				repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
				repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
				repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
				repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
				repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
			}
		};
	}

}
