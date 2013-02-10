package com.odobo.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.odobo.services.IRectanglesService;
import com.odobo.services.impl.RectanglesService;


@Configuration
@ImportResource("classpath:application-context.xml")
public class ApplicationContext {
	
	@Bean
	public IRectanglesService getUserLogic() {
		return new RectanglesService();
	}
}
