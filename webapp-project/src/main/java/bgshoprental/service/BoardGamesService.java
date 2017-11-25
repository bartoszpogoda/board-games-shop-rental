package bgshoprental.service;

import java.util.List;

import bgshoprental.entity.BoardGame;

public interface BoardGamesService {
	List<BoardGame> getAvailableBoardGames();
}
