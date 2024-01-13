package study.cha2code.springinaction.chapter3.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import study.cha2code.springinaction.chapter3.domain.Ingredient;
import study.cha2code.springinaction.chapter3.domain.Order;
import study.cha2code.springinaction.chapter3.domain.Taco;
import study.cha2code.springinaction.chapter3.repository.IngredientRepository;
import study.cha2code.springinaction.chapter3.repository.TacoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * taco custom 페이지와 taco form을 제출하는 controller
 */

@Slf4j
@RequestMapping("/design")
@Controller
@SessionAttributes("order")
public class DesignTacoController {

	// 생성자 주입
	private final IngredientRepository ingredientRepository;
	private final TacoRepository tacoRepository;

	public DesignTacoController(IngredientRepository ingredientRepository,
	                            TacoRepository tacoRepository) {
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}

	@GetMapping
	public String showDesignForm(Model model) {

		// db에 저장된 Ingredient 데이터들을 가져와 list에 저장
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach(ingredients::add);

		// Ingredient내 Type에 저장된 값을 가져와 저장
		Ingredient.Type[] types = Ingredient.Type.values();

		// Ingredient가 저장된 list 정보에 맞는 type 저장 및 model 속성에 추가
		for (Ingredient.Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
			                   filterByType(ingredients, type));
		}

		// taco 객체를 model 객체에 추가
		model.addAttribute("taco", new Taco());

		return "design";
	}

	/* 저장 된 Ingredient 객체의 type별로 필터링하는 method */
	private List<Ingredient> filterByType(
			List<Ingredient> ingredients, Ingredient.Type type) {

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

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors,
	                            @ModelAttribute Order order) {

		// error 조건 충족 시 design view 반환
		if (errors.hasErrors()) {
			return "design";
		}

		Taco saved = tacoRepository.save(design);
		order.addDesign(saved);

		// 생성된 taco가 새로고침 등으로 중복 저장 되지 않게 redirect(응답 코드 : 3xx)
		// post - redirect - get
		return "redirect:/orders/current";
	}
}
