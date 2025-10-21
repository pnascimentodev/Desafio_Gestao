package dev.matheuslf.desafio.inscritos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
	"dev.matheuslf.desafio.inscritos",
	"presentation",
	"application",
	"infrastructure",
	"domain"
})
@EntityScan(basePackages = "domain.entity")
@EnableJpaRepositories(basePackages = "domain.repository")
public class InscritosApplication {

	public static void main(String[] args) {
		SpringApplication.run(InscritosApplication.class, args);
	}

}
