package co.edu.icesi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.repositories.UserRepository;
import co.edu.icesi.users.UserEntity;
import co.edu.icesi.users.Usertype;

@Service

public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	  @Autowired
	  public UserServiceImpl(UserRepository userRepository) {
	    this.userRepository = userRepository;
	  }

	  public UserEntity save(UserEntity user) {
	    userRepository.save(user);
	    return user;
	  }

	  public Optional<UserEntity> findByUsername(String username) {

	    return userRepository.findById(username);
	  }

	  public Iterable<UserEntity> findAll() {
	    return userRepository.findAll();
	  }

	  public Iterable<UserEntity> findAllAdministrators() {
	    return userRepository.findByType(Usertype.ADMINISTRATOR);
	  }

	  public Iterable<UserEntity> findAllOperators() {
	    return userRepository.findByType(Usertype.OPERATOR);
	  }

	  public void delete(UserEntity user) {
	    userRepository.delete(user);

	  }

	  public Usertype[] getTypes() {
	    return Usertype.values();
	  }
}
