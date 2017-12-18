package bgshoprental.service;

import java.util.List;

import bgshoprental.entity.BoardGame;

public interface BoardGamesService {
	Iterable<BoardGame> getAllBoardGames();

	BoardGame getBoardGameById(int id);
	
	List<BoardGame> getAvailableBoardGames();
}
