package bgshoprental.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bgshoprental.entity.BoardGame;
import bgshoprental.repository.BoardGamesRepositoryCustom;

public class BoardGamesRepositoryImpl implements BoardGamesRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<BoardGame> findAvailableBoardGames() {
		List<BoardGame> resultList = em
				.createQuery("SELECT b FROM BoardGame b WHERE b.sellQuantity > 0 OR b.rentalQuantity > 0",
						BoardGame.class)
				.getResultList();

		return resultList;
	}

}
