package co.edu.icesi.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.users.UserEntity;
import co.edu.icesi.users.Usertype;

public interface UserRepository extends CrudRepository<UserEntity,String> {

	public Iterable<UserEntity> findByType(Usertype type);

	public UserEntity findByUsername(String name);


}
