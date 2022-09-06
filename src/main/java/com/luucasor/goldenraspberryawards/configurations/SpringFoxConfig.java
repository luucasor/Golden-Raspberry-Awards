package com.luucasor.goldenraspberryawards.configurations;

import com.luucasor.goldenraspberryawards.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.luucasor.goldenraspberryawards.controllers"))
                .build()
                .apiInfo(apiInfo()).useDefaultResponseMessages(false);

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("API Golden Raspberry Awards")
                .description("API RESTful que possibilita a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards")
                .version(Constants.VERSION_API).build();
    }
}
