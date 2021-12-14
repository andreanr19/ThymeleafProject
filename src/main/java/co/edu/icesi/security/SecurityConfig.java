package co.edu.icesi.security;

import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import co.edu.icesi.security.LoggingAccessDeniedHandler;
import co.edu.icesi.users.*;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	// @Autowired
	// private MyCustomUserDetailsService userDetailsService;

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
	// auth.authenticationProvider(authenticationProvider());
	// }

	// @Bean
	// public DaoAuthenticationProvider authenticationProvider() {
	// DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	// authProvider.setUserDetailsService(userDetailsService);
	// authProvider.setPasswordEncoder(encoder());
	// return authProvider;
	// }
	//
	// @Bean
	// public PasswordEncoder encoder() {
	// return new BCryptPasswordEncoder(11);
	// }

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
				// .antMatchers("/**").permitAll()
				.antMatchers("/login/**").permitAll().antMatchers("/logout/**").permitAll()
				.and().authorizeRequests()
				.antMatchers("/api/**")
				.permitAll()
				// products
				.antMatchers("/products*").permitAll().antMatchers("/products/add/**")
				.hasRole(Usertype.ADMINISTRATOR.toString()).antMatchers("/products/edit/**")
				
				.hasRole(Usertype.ADMINISTRATOR.toString())
				.antMatchers("/categories*").permitAll().antMatchers("/categories/add/**")
				.hasRole(Usertype.ADMINISTRATOR.toString()).antMatchers("/categories/edit/**")
				.hasRole(Usertype.ADMINISTRATOR.toString())
				// product-vendors
				.antMatchers("/product-vendors*").permitAll().antMatchers("/product-vendors/add/**")
				.hasRole(Usertype.ADMINISTRATOR.toString()).antMatchers("/product-vendors/edit/**")
				.hasRole(Usertype.ADMINISTRATOR.toString())
				// transaction-histories
				.antMatchers("/transaction-histories*").permitAll().antMatchers("/transaction-histories/add/**")
				.hasRole(Usertype.OPERATOR.toString()).antMatchers("/transaction-histories/edit/**")
				.hasRole(Usertype.OPERATOR.toString())
				// documents
				.antMatchers("/documents*").permitAll().antMatchers("/documents/info/**").permitAll()
				.antMatchers("/documents/add/**").hasRole(Usertype.OPERATOR.toString())
				.antMatchers("/documents/edit/**").hasRole(Usertype.OPERATOR.toString()).antMatchers("/**")
				.authenticated().anyRequest().permitAll().and().formLogin().loginPage("/login").defaultSuccessUrl("/")
				.failureUrl("/login?error").and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

//		.and().logout()
//		.invalidateHttpSession(true).clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
//		.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

	}
}