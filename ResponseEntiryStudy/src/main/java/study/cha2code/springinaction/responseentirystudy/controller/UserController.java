package study.cha2code.springinaction.responseentirystudy.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.cha2code.springinaction.responseentirystudy.domain.Message;
import study.cha2code.springinaction.responseentirystudy.domain.StatusEnum;
import study.cha2code.springinaction.responseentirystudy.domain.User;
import study.cha2code.springinaction.responseentirystudy.repository.UserRepository;

import java.nio.charset.Charset;

/**
 * ResponseEntity를 사용하여 로그인 한 user의 정보를
 * json으로 반환하는 controller
 */
@RestController
public class UserController {

	// 생성자 주입
	private final UserRepository repository;
	public UserController(UserRepository repository){
		this.repository = repository;
	}

	@GetMapping(value = "/user/{username}")
	public ResponseEntity<Message> findByUsername(@PathVariable String username) {

		// 사용자의 username으로 DB에서 정보를 찾아 User 객체에 저장
		User user = repository.findByUsername(username);
		Message message = new Message();
		HttpHeaders headers = new HttpHeaders();

		// HttpHeaders의 내용 타입 설정
		headers.setContentType(new MediaType("application", "json",
		                                     Charset.forName("UTF-8")));

		// message 객체에 상태, 메세지, user 정보 저장
		message.setStatus(StatusEnum.OK);
		message.setMessage("Success");
		message.setData(user);

		System.out.println("user info : " + user);

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
}
/*

ResponseEntity
- HttpEntity 클래스를 상속 받아 구현한 클래스
- 사용자의 HttpRequest에 대한 응답 데이터를 포함
- 결과 데이터, HTTP 상태 코드 제어 가능
- HttpEntity 구성 = HttpStatus(상태), HttpHeaders(요청/응답에 대한 요구사항), HttpBody(HttpHeaders에 대한 내용)

 */