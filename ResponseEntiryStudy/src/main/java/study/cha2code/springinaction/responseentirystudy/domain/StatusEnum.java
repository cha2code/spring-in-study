package study.cha2code.springinaction.responseentirystudy.domain;

/**
 * 상태 코드로 보낼 예시를 정의
 */
public enum StatusEnum {

	OK(200, "OK"),
	BAD_REQUEST(400, "BAD_REQUEST"),
	NOT_FOUND(500, "INTERNAL_SERVER_ERROR");

	int statusCode;
	String code;

	StatusEnum(int statusCode, String code) {
		this.code = code;
	}
}
