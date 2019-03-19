package gestionscript.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionscript.entities.Script;

public interface ScriptRepository extends JpaRepository<Script, Integer>{

}
