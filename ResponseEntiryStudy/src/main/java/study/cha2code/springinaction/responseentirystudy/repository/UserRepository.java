package study.cha2code.springinaction.responseentirystudy.repository;


import org.springframework.data.repository.CrudRepository;
import study.cha2code.springinaction.responseentirystudy.domain.User;

/**
 * user 정보를 DB에 저장하는 repository
 */

public interface UserRepository extends CrudRepository<User, Long> {

	// id로 해당 user 검색
	User findByUsername(String username);

}
