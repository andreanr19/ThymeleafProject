package co.edu.icesi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.services.UserService;
import co.edu.icesi.services.UserServiceImpl;
import co.edu.icesi.users.UserEntity;
import co.edu.icesi.users.Usertype;

@SpringBootApplication
@ComponentScan(basePackages = { "co.edu.icesi" })
@EnableJpaRepositories
public class Taller1PruebasApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext c = SpringApplication.run(Taller1PruebasApplication.class, args);
		UserService u = c.getBean(UserServiceImpl.class);
		UserEntity user1 = new UserEntity();
		user1.setUsername("charles777");
		user1.setPassword("{noop}password1");

		user1.setType(Usertype.ADMINISTRATOR);

		u.save(user1);

		UserEntity user2 = new UserEntity();
		user2.setUsername("Andrea");
		user2.setPassword("{noop}password2");

		user2.setType(Usertype.OPERATOR);
		u.save(user2);
	}

}
