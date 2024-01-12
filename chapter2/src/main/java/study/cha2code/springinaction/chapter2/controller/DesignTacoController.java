package study.cha2code.springinaction.chapter2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.cha2code.springinaction.chapter2.domain.Ingredient;
import study.cha2code.springinaction.chapter2.domain.Ingredient.Type;
import study.cha2code.springinaction.chapter2.domain.Taco;
import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * taco 재료를 선택하는 페이지에 대한 controller
 */

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	@GetMapping
	public String showDesignForm(Model model) {

		/* Ingredient 객체를 저장하는 list */
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);

		// Ingredient Type에 저장 된 값을 가져옴
		Type[] types = Type.values();

		// type의 값 개수만큼 model 객체의 속성으로 추가
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
			                   filterByType(ingredients, type));
		}

		// taco 객체를 model 객체에 추가
		model.addAttribute("taco", new Taco());

		// 'design' view로 반환
		return "design";
	}

	/* 저장 된 Ingredient 객체의 type별로 필터링하는 method */
	private List<Ingredient> filterByType(
			List<Ingredient> ingredients, Type type) {

		return ingredients
				.stream()
				// stream 내 요소들을 하나씩 필터링 (중간 연산)
				// type의 저장된 값과 같은 값만 선택
				.filter(x -> x.getType().equals(type))
				// stream의 요소들을 list로 변환 (최종 연산)
				.collect(Collectors.toList());
	}

	/*

	Stream 특징 (java 8)
	- 원본 데이터 소스 변경 없이 읽기만 한다.
	- 한 번 사용하면 재사용이 불가능하다.
	- 내부 반복으로 작업을 처리한다.
	- 멀티쓰레드 사용으로 병렬처리가 쉽다.

	 */

	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors) {

		// error 조건 충족 시 design view 반환
		if (errors.hasErrors()) {
			return "design";
		}

		log.info("Processing design: " + design);

		// 생성된 taco가 새로고침 등으로 중복 저장 되지 않게 redirect(응답 코드 : 3xx)
		// post - redirect - get
		return "redirect:/orders/current";
	}
}