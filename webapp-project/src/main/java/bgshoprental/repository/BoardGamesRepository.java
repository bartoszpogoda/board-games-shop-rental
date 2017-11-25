package bgshoprental.repository;

import org.springframework.data.repository.CrudRepository;

import bgshoprental.entity.BoardGame;

public interface BoardGamesRepository extends CrudRepository<BoardGame, Integer>, BoardGamesRepositoryCustom {

}
