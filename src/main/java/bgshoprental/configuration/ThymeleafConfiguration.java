package bgshoprental.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import bgshoprental.service.internal.InternalOrderService;
import bgshoprental.service.internal.RentalService;
import bgshoprental.service.internal.impl.InternalOrderServiceImpl;
import bgshoprental.service.internal.impl.RentalServiceImpl;
import bgshoprental.util.InternalOrderBuilder;

@Configuration
public class ThymeleafConfiguration {

	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}

	@Bean
	InternalOrderService internalOrderService() {
		return new InternalOrderServiceImpl();
	}
	
	@Bean
	InternalOrderBuilder internalOrderBuilder() {
		return new InternalOrderBuilder();
	}
	
	@Bean
	RentalService rentalService() {
		return new RentalServiceImpl();
	}
}
