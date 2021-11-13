package co.edu.icesi.security;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import co.edu.icesi.repositories.UserRepository;
import co.edu.icesi.users.UserEntity;
import lombok.extern.log4j.Log4j2;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userApp = userRepository.findByUsername(username);

		if (userApp != null) {

			User.UserBuilder builder = User.withUsername(username).password(userApp.getPassword()).roles(userApp.getType().toString());

			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}