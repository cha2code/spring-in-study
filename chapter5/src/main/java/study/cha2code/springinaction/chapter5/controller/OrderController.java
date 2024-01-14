package study.cha2code.springinaction.chapter5.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import study.cha2code.springinaction.chapter5.domain.Order;
import study.cha2code.springinaction.chapter5.domain.User;
import study.cha2code.springinaction.chapter5.repository.OrderRepository;

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
	public String orderForm(@AuthenticationPrincipal User user,
	                        @ModelAttribute Order order) {

		// order form에 사용자 정보들을 미리 입력
		if(order.getDeliveryName() == null){
			order.setDeliveryName(user.getFullname());
		}

		if (order.getDeliveryStreet() == null) {
			order.setDeliveryStreet(user.getStreet());
		}

		if (order.getDeliveryCity() == null) {
			order.setDeliveryCity(user.getCity());
		}

		if (order.getDeliveryState() == null) {
			order.setDeliveryState(user.getState());
		}

		if (order.getDeliveryZip() == null) {
			order.setDeliveryZip(user.getZip());
		}

		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors,
	                           SessionStatus sessionStatus,
	                           @AuthenticationPrincipal User user){

		if(errors.hasErrors()) {
			return "orderForm";
		}

		// Order에 user 객체 전달
		order.setUser(user);

		// repository의 save 메소드를 이용해 db에 저장
		orderRepository.save(order);

		// 세션에 저장된 정보 초기화
		sessionStatus.setComplete();

		return "redirect:/";
	}
}
