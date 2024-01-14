package study.cha2code.springinaction.chapter5.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.cha2code.springinaction.chapter5.domain.User;

/**
 * 사용자 정보 등록 전송 form에 대한 객체
 */

@Data
public class RegistrationForm {

	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;

	// RegisterationForm의 속성 값을 갖는 새로운 User 객체를 생성하는 메소드
	// 비밀번호가 db에 저장되기 전에 PasswordEncoder를 사용하여 암호화
	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(
				username, passwordEncoder.encode(password),
				fullname, street, city, state, zip, phone);
	}
}
