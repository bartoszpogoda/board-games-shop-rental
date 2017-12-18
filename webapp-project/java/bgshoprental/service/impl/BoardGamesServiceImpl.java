package bgshoprental.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.BoardGame;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.service.BoardGamesService;

@Service
public class BoardGamesServiceImpl implements BoardGamesService {

	@Autowired
	BoardGamesRepository boardGamesRepository;

	@Override
	public List<BoardGame> getAvailableBoardGames() {
		return boardGamesRepository.findAvailableBoardGames();
	}

	@Override
	public Iterable<BoardGame> getAllBoardGames() {
		return boardGamesRepository.findAll();
	}

	@Override
	public BoardGame getBoardGameById(int id) {
		return boardGamesRepository.findOne(id);
	}

}
