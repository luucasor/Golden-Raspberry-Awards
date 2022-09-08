package com.luucasor.goldenraspberryawards;

import com.luucasor.goldenraspberryawards.dtos.MovieDTO;
import com.luucasor.goldenraspberryawards.readers.CSVReader;
import com.luucasor.goldenraspberryawards.security.AuthFilter;
import com.luucasor.goldenraspberryawards.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GoldenRaspberryAwardsApplication implements CommandLineRunner {

	@Autowired
	MovieService movieService;

	@Autowired
	MovieService awardService;

	public static void main(String[] args){
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

	@Override
	public void run(String... args) throws Exception {
		List<MovieDTO> beans = (List<MovieDTO>) new CSVReader().getValues("movielist (5).csv", ';');
		movieService.createMovies(beans);
		awardService.createAwards(beans);
	}
}
