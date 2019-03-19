package gestionscript.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionscript.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findOneByEmail(String email);
	
}
