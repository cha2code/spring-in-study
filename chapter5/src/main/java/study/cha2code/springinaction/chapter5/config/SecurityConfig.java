package study.cha2code.springinaction.chapter5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * http 요청 경로별 접근 제한 및 login, logout을 처리하는 configuration
 *
 * 버전이 상향 되면서 WebSecurityConfigurerAdapter가 deprecated되어
 * SecurityFilterChain 및 AuthenticationProvider Bean으로 대체
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// 생성자 주입
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder passwordEncoder;

	public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {

		// cors, csrf 비 활성화
		http.cors(AbstractHttpConfigurer::disable)
		    .csrf(AbstractHttpConfigurer::disable);

		// 요청 경로별 접근 제한 설정
		http.authorizeHttpRequests((request -> request
				.requestMatchers(
						new AntPathRequestMatcher("/common/**"),
						new AntPathRequestMatcher("/dist/**"),
						new AntPathRequestMatcher("/js/**"),
						new AntPathRequestMatcher("/plugins"),
						new AntPathRequestMatcher("/common/**"),
						new AntPathRequestMatcher("/")
				).permitAll() // 전체 접근 가능

				.requestMatchers(
						new AntPathRequestMatcher("/design"),
						new AntPathRequestMatcher("/orders")
				).hasAuthority("ROLE_user") // 권한이 "USER"인 사용자만 접근 가능

				.anyRequest().permitAll() // 그 외 요청들 전체 접근 가능
		                           ));

		http.formLogin(form -> form
				.loginPage("/login") // custom된 로그인 페이지
				.defaultSuccessUrl("/design") // 로그인 성공 시 이동할 페이지
				.permitAll());

		http.logout(form -> form
				.logoutUrl("/logout")
				.logoutSuccessUrl("/"));

		return http.build();
	}

	/**
	 * username, password가 유효한 지 검사하는 인증 처리자 Bean
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {

		// AuthenticationProvider의 구현체
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		// UserDetailsService를 통해 id와 매칭되는 사용자 검색
		authenticationProvider.setUserDetailsService(userDetailsService);
		// 위에서 조회된 사용자의 password와 입력 받은 password가 일치하는 지 확인
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return authenticationProvider;
	}

/*
	// 메모리 내에서 사용자를 관리하기 위한 Bean
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {


		UserDetails user1 = User.withUsername("user1")
		                        .password("{noop}password1")
		                        .authorities("user")
		                        .build();

		UserDetails user2 = User.withUsername("user2")
		                        .password("{noop}password2")
		                        .authorities("user")
		                        .build();

		return new InMemoryUserDetailsManager(user1, user2);


	}

 */

}
