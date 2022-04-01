package com.contare.pokemonapp.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class Config extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("your cross origin url")
                .allowedOrigins("your cross origin host/url")
                .allowedHeaders("Access-Control-Allow-Origin", "*")
                .allowedHeaders("Access-Control-Allow-Headers", "Content-Type,x-requested-with").maxAge(20000)
                .allowCredentials(true)
                .allowedMethods("DELETE");
    }
}




