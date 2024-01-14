package study.cha2code.springinaction.chapter5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * taco 프로젝트에서 사용되는 Bean을 정의하는 클래스
 * 순환 참조를 방지하기 위해 생성
 */

@Configuration
public class BeanConfig {

	/**
	 * 비밀번호 단방향 암호화 처리하는 Bean
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
