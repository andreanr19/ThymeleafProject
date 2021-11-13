package co.edu.icesi.services;

import java.util.Optional;


import co.edu.icesi.users.UserEntity;
import co.edu.icesi.users.Usertype;

public interface UserService {

	  public UserEntity save(UserEntity user);

		  public Optional<UserEntity> findByUsername(String username);

		  public Iterable<UserEntity> findAll();
		  public Iterable<UserEntity> findAllAdministrators();

		  public Iterable<UserEntity> findAllOperators();

		  public void delete(UserEntity user);
		  

		  public Usertype[] getTypes();
}
