package gestionscript.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionscript.entities.History;

public interface HistoryRepository extends JpaRepository<History, Integer>{

}
