package bgshoprental.test;

import static org.mockito.Matchers.any;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;
import bgshoprental.entity.ExternalOrderStatus;
import bgshoprental.repository.ExternalOrderRepository;
import bgshoprental.service.exception.ExternalOrderNotFoundException;
import bgshoprental.service.external.ExternalOrderService;
import bgshoprental.service.external.impl.ExternalOrderServiceImpl;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class ExternalOrderServiceTest {

	@TestConfiguration
	static class ExternalOrderServiceTestContextConfiguration {
		@Bean
		public ExternalOrderService externalOrderService() {
			return new ExternalOrderServiceImpl();
		}
	}

	@Autowired
	ExternalOrderService externalOrderService;

	@MockBean
	ExternalOrderRepository externalOrderRepository;

	@Test(expected = ExternalOrderNotFoundException.class)
	public void shouldThrowExceptionWhenExternalOrderNotFound() {
		Mockito.when(externalOrderRepository.findOne(any())).thenReturn(null);

		externalOrderService.addElementToExternalOrder(3, new ExternalOrderElement());
	}

	@Test
	public void shouldChangeStatusToRealized() {
		// given
		ExternalOrder externalOrder = new ExternalOrder();
		externalOrder.setStatus(ExternalOrderStatus.CREATED);
		
		Mockito.when(externalOrderRepository.findOne(2)).thenReturn(externalOrder);
		
		// when
		externalOrderService.processRealisation(2);
		
		// then
		assertThat(externalOrder.getStatus()).isEqualTo(ExternalOrderStatus.REALIZED);
	}

	@Test
	public void shouldAddProperNumberOfItemsOnRealization() {
		// given
		BoardGame boardGame = new BoardGame();
		boardGame.setSellQuantity(3);

		ExternalOrderElement externalOrderElement = new ExternalOrderElement();
		externalOrderElement.setPrice(null);
		externalOrderElement.setQuantity(2);
		externalOrderElement.setBoardGame(boardGame);

		ExternalOrder externalOrder = new ExternalOrder();
		externalOrder.setElements(new ArrayList<>());
		externalOrder.getElements().add(externalOrderElement);
		externalOrderElement.setExternalOrder(externalOrder);

		Mockito.when(externalOrderRepository.findOne(2)).thenReturn(externalOrder);

		// when
		externalOrderService.processRealisation(2);

		// then
		assertThat(boardGame.getSellQuantity()).isEqualTo(5);
	}

}
