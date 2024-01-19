package study.cha2code.springinaction.responseentirystudy.domain;

import lombok.Data;

/**
 * 상태 코드, 메세지, 데이터를 담을 필드를 정의하는 클래스
 */
@Data
public class Message {

	private StatusEnum status;
	private String message;
	private Object data;

	public Message() {
		this.status = StatusEnum.BAD_REQUEST;
		this.data = null;
		this.message = null;
	}
}
