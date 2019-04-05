package gestionscript.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionscript.entities.Script;
import gestionscript.entities.User;

public interface ScriptRepository extends JpaRepository<Script, Integer>{
	
	List<Script> findByCreateur(User createur);

	Script findOneById(int id);
}
