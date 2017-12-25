package bgshoprental.service;

import java.util.List;

import bgshoprental.entity.BoardGame;

public interface BoardGamesService {
	Iterable<BoardGame> findAllBoardGames();

	BoardGame findBoardGameById(int id);
	
	List<BoardGame> findAvailableBoardGames();
}
