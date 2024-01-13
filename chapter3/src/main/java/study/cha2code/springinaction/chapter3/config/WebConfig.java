package study.cha2code.springinaction.chapter3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * view controller의 역할을 하는 configuration
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
	// WebMvcConfigurer - Spring MVC를 구성하는 method를 정의하는 interface

	// 하나 이상의 view controller를 등록하기 위해 ViewControllerRegistry 객체 반환
	// HomeController 대체 가능
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/").setViewName("home");
	}
}
