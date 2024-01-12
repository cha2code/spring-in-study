package study.cha2code.springinaction.chapter2.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.cha2code.springinaction.chapter2.domain.Order;

/**
 * taco 주문 form을 나타내거나 전송하기 위한 controller
 */

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

	@GetMapping("/current")
	public String orderForm(Model model) {

		// 주문 객체 생성 후 model 속성에 추가
		model.addAttribute("order", new Order());

		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors) {

		// error 조건 충족 시 orderForm return
		if (errors.hasErrors()) {
			return "orderForm";
		}

		log.info("Order submitted: " + order);

		// 주문 성공 시 main page로 redirect
		return "redirect:/";
	}
}