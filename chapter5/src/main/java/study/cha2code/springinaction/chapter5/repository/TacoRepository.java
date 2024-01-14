package study.cha2code.springinaction.chapter5.repository;


import org.springframework.data.repository.CrudRepository;
import study.cha2code.springinaction.chapter5.domain.Taco;

/**
 * Taco 객체를 db에 저장하기 위한 repository
 */
public interface TacoRepository extends CrudRepository<Taco, Long> {

}
