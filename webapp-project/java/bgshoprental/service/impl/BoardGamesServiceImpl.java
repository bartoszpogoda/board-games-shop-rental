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
	public List<BoardGame> findAvailableBoardGames() {
		return boardGamesRepository.findAvailable();
	}

	@Override
	public Iterable<BoardGame> findAllBoardGames() {
		return boardGamesRepository.findAll();
	}

	@Override
	public BoardGame findBoardGameById(int id) {
		return boardGamesRepository.findOne(id);
	}

}
