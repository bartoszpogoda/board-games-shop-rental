package bgshoprental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bgshoprental.entity.BoardGame;

public interface BoardGamesRepository extends CrudRepository<BoardGame, Integer> {
	@Query(value = "SELECT b FROM BoardGame b WHERE b.sellQuantity > 0 OR b.rentalQuantity > 0")
	List<BoardGame> findAvailable();
}
