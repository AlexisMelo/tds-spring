package s4.spring.td3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.td3.entities.Organization;
import s4.spring.td3.repositories.OrgasRepository;

@RestController
@RequestMapping("/rest/")
public class RestOrgasController {

	@Autowired
	private OrgasRepository repo;
	
	@GetMapping("")
	public List<Organization> get(){
		return repo.findAll();
	}
	
	@GetMapping("orgas")
	public void read() {
		
	}
	
	@GetMapping("orgas/{id}")
	public void read(@PathVariable int id) {
		
	}
	
	@PostMapping("orgas/create")
	public Organization create(@RequestBody Organization orga) {
		return repo.saveAndFlush(orga);
	}
	
	@PutMapping("orgas/update")
	public void update() {
		
	}
	
	@DeleteMapping("orgas/delete")
	public void delete() {
		
	}
	
	@PutMapping("orgas/{member}/{id}")
	public void get(@PathVariable int id,@PathVariable String member) {
		
	}
}
