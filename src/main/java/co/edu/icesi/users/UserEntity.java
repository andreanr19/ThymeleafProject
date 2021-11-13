package co.edu.icesi.users;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserEntity {

	@Id
	private String username;
	
	private String password;
	
	private Usertype type;
}
