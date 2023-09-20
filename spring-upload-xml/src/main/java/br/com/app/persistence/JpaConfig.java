package br.com.app.persistence;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.app.persistence.repository")
@EntityScan(basePackages = "br.com.app.persistence.entities")
public class JpaConfig {
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
