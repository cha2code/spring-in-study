package study.cha2code.springinaction.chapter4.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 사용자 주문 정보를 저장하는 객체
 */

@Data
@Entity
@Table(name="Taco_Order") // order 객체를 db의 Taco_Order 테이블에 저장
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	// Order 객체 식별 id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //db가 자동으로 생성해주는 값 사용
	private Long id;

	// 주문 정보 생성 날짜
	private Date placedAt;

	// User 객체와 연관되는 속성 (한 명의 사용자가 여러 주문을 가짐)
	@ManyToOne
	private User user;

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

	@ManyToMany(targetEntity = Taco.class)
	private List<Taco> tacos = new ArrayList<>();
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}

	/**
	 * order 객체가 저장되기 전에 placedAt 속성을
	 * 현재 날짜와 시간으로 설정하는 메소드
	 */
	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
}