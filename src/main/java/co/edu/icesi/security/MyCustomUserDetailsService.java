package co.edu.icesi.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.repositories.UserRepository;
import co.edu.icesi.users.UserEntity;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public MyCustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userApp = userRepository.findByUsername(username);

		if (userApp != null) {

			User.UserBuilder builder = User.withUsername(username).password(userApp.getPassword())
					.roles(userApp.getType().toString());

			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}