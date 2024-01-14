package study.cha2code.springinaction.chapter4.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * OrderController의 pageSize 설정 클래스
 * 구성 속성 관련 코드를 모아 Bean으로 등록하면 수정 및 재사용이 용이
 */

@Validated
@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
public class OrderProps {

	@Min(value = 5, message = "must be between 5 and 25")
	@Max(value = 25, message = "must be between 5 and 25")
	private int pageSize = 20;
}
