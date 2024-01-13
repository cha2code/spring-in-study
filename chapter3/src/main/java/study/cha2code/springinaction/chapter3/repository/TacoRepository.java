package study.cha2code.springinaction.chapter3.repository;

import study.cha2code.springinaction.chapter3.domain.Taco;

/**
 * Taco 객체를 db에 저장하기 위한 repository
 */
public interface TacoRepository {

	Taco save(Taco design);
}
