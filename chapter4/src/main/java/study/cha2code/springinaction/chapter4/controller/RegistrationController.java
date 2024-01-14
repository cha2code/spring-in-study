package study.cha2code.springinaction.chapter4.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.cha2code.springinaction.chapter4.repository.UserRepository;
import study.cha2code.springinaction.chapter4.security.RegistrationForm;

/**
 * 사용자를 등록하기 위한 controller
 */

@Controller
@RequestMapping("/register")
public class RegistrationController {

	// 생성자 주입
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public RegistrationController(UserRepository userRepository,
	                              PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping
	public String registerForm() {
		return "registration";
	}

	@PostMapping
	public String processRegistration(RegistrationForm form) {

		// form 제출 시 BCryptPasswordEncoder 객체를 toUser로 전달
		userRepository.save(form.toUser(passwordEncoder));

		return "redirect:/login";
	}
}
