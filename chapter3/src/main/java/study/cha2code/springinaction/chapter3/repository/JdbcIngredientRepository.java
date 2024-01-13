package study.cha2code.springinaction.chapter3.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import study.cha2code.springinaction.chapter3.domain.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JdbcTemplate을 사용하여
 * IngredientRepository내 메소드를 구현하는 repository
 */

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	// Ingredient 객체가 저장 된 컬렉션 반환
	@Override
	public Iterable<Ingredient> findAll() {

		return jdbc.query("select id, name, type from Ingredient",
		                  this::mapRowToIngredient);
	}

	// Ingredient의 id를 사용하여 하나의 Ingredient 객체를 반환
	@Override
	public Ingredient findById(String id) {

		return jdbc.queryForObject(
				"select id, name, type from Ingredient where id=?",
				this::mapRowToIngredient, id);
	}

	/**
	 * 쿼리로 생성된 ResultSet의 행 개수만큼 호출 후
	 * 각각 Ingredient 객체로 생성 후 반환하는 메소드
	 */
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
			throws SQLException {

		return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type")));
	}

	/**
	 * db에 Ingredient 데이터를 추가하는 메소드
	 */
	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update(
				"insert into Ingredient (id, name, type) values (?, ?, ?)",
				ingredient.getId(),
				ingredient.getName(),
				ingredient.getType().toString());

		return ingredient;
	}
}