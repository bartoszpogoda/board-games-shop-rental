package bgshoprental.repository;

import java.util.List;

import bgshoprental.entity.BoardGame;

public interface BoardGamesRepositoryCustom {
	List<BoardGame> findAvailableBoardGames();
}
