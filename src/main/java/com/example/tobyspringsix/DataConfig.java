package com.example.tobyspringsix;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
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

	/**
	 * 아래의 Bean 생성 메서드는 @PersistenceContext, @PersistenceUnit을 처리함.
	 * @PersistenceContenxt -> 애노테이션이 달린 필드나 메소드에 EntityManager 주입
	 * @PersistenceUnit -> 애노테이션이 달린 필드나 메소드에 EntityManagerFactory 주입
 	 */

	@Bean
	public BeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

	/**
	 * JpaTransactionManager는 JPA를 사용하는 애플리케이션에서 트랜잭션을 관리하는 역할을 수행
	 * 주입된 EntityManager를 사용하여 트랜잭션 경계를 설정하고, 트랜잭션 시작, 커밋, 롤백 등을 관리
	 *
	 *
	 *
	 */

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public OrderRepository orderRepository() {
		return new OrderRepository();
	}
}
