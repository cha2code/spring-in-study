package study.cha2code.springinaction.chapter4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.cha2code.springinaction.chapter4.domain.User;
import study.cha2code.springinaction.chapter4.repository.UserRepository;

/**
 * 사용자에 대한 정보를 명세하는 Service
 */

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	// 생성자 주입
	private UserRepository userRepo;

	@Autowired
	public UserRepositoryUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	// 사용자의 id에 해당하는 user를 검색하기 위한 메소드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByUsername(username); // username에 해당하는 사용자를 찾아 User 객체에 저장

		// 사용자를 찾았을 경우
		if (user != null) {
			return user;
		}

		// loadUserByUsername은 null을 반환하지 않기 때문에 findByUsername에서 null 반환 시 UsernameNotFoundException 발생
		throw new UsernameNotFoundException(
				"User '" + username + "' not found");
	}
}