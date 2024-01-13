package study.cha2code.springinaction.chapter3.domain;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 사용자 주문 정보를 저장하는 객체
 */

@Data
public class Order {

	// Order 객체 식별 id
	private Long id;

	// 주문 정보 생성 날짜
	private Date placedAt;

	@NotBlank(message="Name is required")
	private String deliveryName;

	@NotBlank(message="Street is required")
	private String deliveryStreet;

	@NotBlank(message="City is required")
	private String deliveryCity;

	@NotBlank(message="State is required")
	private String deliveryState;

	@NotBlank(message="Zip code is required")
	private String deliveryZip;

	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;

	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			message="Must be formatted MM/YY")
	private String ccExpiration;

	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;

	private List<Taco> tacos = new ArrayList<>();
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
}