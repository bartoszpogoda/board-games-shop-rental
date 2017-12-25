//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//
//import bgshoprental.repository.ExternalOrderRepository;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ExternalOrderTest {
//	
////	@Mock
////	private ExternalOrderRepository externalOrderRepository;
////	
////	@InjectMocks
////	private ExternalOrderService externalOrderService = new ExternalOrderServiceImpl();
//	
//	@Autowired
//	private ExternalOrderRepository externalOrderRepository;
//	
//	@Before
//	public void setUp() {
//		//MockitoAnnotations.initMocks(this);
//		
//		//Mockito.when(externalOrderRepository.findOne(Mockito.anyObject())).thenReturn(new ExternalOrder());
//	}
//
//	@Test
//	public void test() {
//		
//		ExternalOrder aa = new ExternalOrder();
//		externalOrderRepository.save(aa);
//		
//		Iterable<ExternalOrder> findAll = externalOrderRepository.findAll();
//		
//		ExternalOrder next = findAll.iterator().next();
//		
//		assertNotNull(next);
//	}
//
//}
