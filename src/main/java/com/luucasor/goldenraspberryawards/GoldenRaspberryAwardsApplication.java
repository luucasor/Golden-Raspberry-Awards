package com.luucasor.goldenraspberryawards;

import com.luucasor.goldenraspberryawards.security.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GoldenRaspberryAwardsApplication {

	public static void main(String[] args){
		//TODO Falta criar autenticação
		SpringApplication.run(GoldenRaspberryAwardsApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean(){
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}

}
