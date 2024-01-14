package study.cha2code.springinaction.chapter4.repository;


import org.springframework.data.repository.CrudRepository;
import study.cha2code.springinaction.chapter4.domain.Ingredient;

/**
 * DB에 저장 된 taco 재료(Ingredient)를 가져오거나 저장하는 repository
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}

/*

CrudRepository<xxxx,xxxx>
- create, read, update, delete 연산을 위한 메소드가 선언되어 있는 인터페이스
- 첫번째 매개변수 : repository에 저장되는 개체 타입
- 두번째 매개변수 : 개체 ID 속성의 타입
- application이 시작될 때 JPA가 구현체를 자동으로 생성
  (controller에 주입만 하면 사용 가능)

 */