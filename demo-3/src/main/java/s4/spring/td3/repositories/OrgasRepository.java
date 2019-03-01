package s4.spring.td3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td3.entities.Organization;

public interface OrgasRepository extends JpaRepository<Organization, Integer>{

}
