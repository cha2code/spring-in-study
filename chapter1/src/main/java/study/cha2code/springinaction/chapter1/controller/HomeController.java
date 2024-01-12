package study.cha2code.springinaction.chapter1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * taco main page 실행을 위한 controller
 */

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
}
