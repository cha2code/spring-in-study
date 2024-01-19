package study.cha2code.springinaction.responseentirystudy.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 사용자 인증 확인을 위해 비밀번호를 암호화 하지 않는 class
 */
public class NoEncodingPasswordEncoder implements PasswordEncoder {

	// 입력 받은 password를 암호화 하지 않고 String으로 반환하는 메소드
	@Override
	public String encode(CharSequence rawPwd) {
		return rawPwd.toString();
	}

	// encode에서 반환 된 비밀번호를 DB에 저장 된 비밀번호와 비교하는 메소드
	@Override
	public boolean matches(CharSequence rawPwd, String encodedPwd) {
		return rawPwd.toString().equals(encodedPwd);
	}
}
