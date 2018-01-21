package bgshoprental.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.Supplier;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.service.BoardGamesService;
import bgshoprental.service.exception.NotEnoughItemsAvailableException;
import bgshoprental.service.external.SupplierService;

@Service
public class BoardGamesServiceImpl implements BoardGamesService {

	@Autowired
	BoardGamesRepository boardGamesRepository;

	@Autowired
	SupplierService supplierService;

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

	@Override
	@Transactional
	public void transferItemsToRental(Integer boardGameId, int numberOfItemsToTransfer) {
		BoardGame boardGame = boardGamesRepository.findOne(boardGameId);
		if (boardGame.getSellQuantity() < numberOfItemsToTransfer) {
			throw new NotEnoughItemsAvailableException();
		}

		boardGame.setSellQuantity(boardGame.getSellQuantity() - numberOfItemsToTransfer);
		boardGame.setRentalQuantity(boardGame.getRentalQuantity() + numberOfItemsToTransfer);
	}

	@Override
	@Transactional
	public void removeItems(Integer boardGameId, int quantityToRemoveRental, int quantityToRemoveSell) {
		BoardGame boardGame = boardGamesRepository.findOne(boardGameId);

		if (boardGame.getSellQuantity() < quantityToRemoveSell) {
			throw new NotEnoughItemsAvailableException();
		}

		boardGame.setSellQuantity(boardGame.getSellQuantity() - quantityToRemoveSell);

		if (boardGame.getRentalQuantity() < quantityToRemoveRental) {
			throw new NotEnoughItemsAvailableException();
		}

		boardGame.setRentalQuantity(boardGame.getRentalQuantity() - quantityToRemoveRental);
	}

	@Override
	public void addBoardGame(String title, String supplierName, int sellQuantity, BigDecimal sellPrice,
			int rentalQuantity, BigDecimal rentalPrice) {
		BoardGame boardGame = new BoardGame();

		Supplier supplier = supplierService.findSupplierByName(supplierName);

		boardGame.setSupplier(supplier);
		boardGame.setTitle(title);
		boardGame.setSellQuantity(sellQuantity);
		boardGame.setSellPrice(sellPrice);
		boardGame.setRentalQuantity(rentalQuantity);
		boardGame.setRentalPrice(rentalPrice);

		boardGamesRepository.save(boardGame);
	}
}
