package study.cha2code.springinaction.chapter3jpa.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import study.cha2code.springinaction.chapter3jpa.domain.Order;
import study.cha2code.springinaction.chapter3jpa.repository.OrderRepository;

/**
 *  taco order form을 전송하는 controller
 *
 * @author cha2jini
 */

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private final OrderRepository orderRepository;
	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@GetMapping("/current")
	public String orderForm(Model model) {

		model.addAttribute("order", new Order());

		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors,
	                           SessionStatus sessionStatus){

		if(errors.hasErrors()) {
			return "orderForm";
		}

		orderRepository.save(order);
		sessionStatus.setComplete();

		return "redirect:/";
	}
}
