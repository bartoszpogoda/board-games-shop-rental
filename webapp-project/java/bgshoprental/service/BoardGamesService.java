package bgshoprental.service;

import java.math.BigDecimal;
import java.util.List;

import bgshoprental.entity.BoardGame;

public interface BoardGamesService {
	Iterable<BoardGame> findAllBoardGames();

	BoardGame findBoardGameById(int id);
	
	List<BoardGame> findAvailableBoardGames();

	void transferItemsToRental(Integer boardGameId, int numberOfItemsToTransfer);

	void removeItems(Integer boardGameId, int quantityToRemoveRental, int quantityToRemoveSell);

	void addBoardGame(String title, String supplierName, int sellQuantity, BigDecimal sellPrice, int rentalQuantity,
			BigDecimal rentalPrice);
}
