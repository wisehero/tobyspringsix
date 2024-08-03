package com.example.tobyspringsix;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.example.tobyspringsix.data.OrderRepository;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class DataConfig {

	// data source
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	// entity manager factory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("com.example.tobyspringsix");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {
			{
				setDatabase(Database.H2);
				setGenerateDdl(true);
				setShowSql(true);
			}
		});

		return emf;
	}

	@Bean
	public OrderRepository orderRepository(EntityManagerFactory emf) {
		return new OrderRepository(emf);
	}
}
