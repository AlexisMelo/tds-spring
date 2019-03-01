package s4.spring.td2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.td2.entities.Groupe;
import s4.spring.td2.entities.Organization;
import s4.spring.td2.repositories.GroupesRepository;
import s4.spring.td2.repositories.OrgasRepository;

@Controller
@RequestMapping("/orgas/")
public class OrgasController {
	
	@Autowired
	private OrgasRepository orgasRepo;
	@Autowired
	private GroupesRepository repoGroupes;
	
	@RequestMapping("create")
	@ResponseBody
	public String createOrga() {
		
		Organization organization = new Organization();
		organization.setName("IUT ifs");
		organization.setDomain("unicaen.fr");
		organization.setAliases("iutc3.unicaen.fr");
		organization.setVille("Ifs");
		
		orgasRepo.save(organization);
		return organization+" ajoutée dans la bdd.";
	}
	
	@RequestMapping("groupes")
	@ResponseBody
	public String createGroupe() {
		Groupe groupe = new Groupe();
		repoGroupes.save(groupe);		
		return "Groupe créé";
	}
	
	@RequestMapping("create/groupes/{id}")
	@ResponseBody
	public String createOrgaWithGroupes(@PathVariable int id) {
		
		Optional<Organization> optOrga = orgasRepo.findById(id);
	
		if(optOrga.isPresent()) {
		
			Organization organization = optOrga.get();
			Groupe groupe = new Groupe();
			groupe.setName("Etudiants");
			organization.addGroupe(groupe);
			orgasRepo.save(organization);
			
			orgasRepo.save(organization);
			return organization+" ajoutée dans la bdd.";
		}
		else {
			return "Organization inexistante";
		}
		
	}
	
	@RequestMapping("{path:(index)?}")
	public String messagerie() {
		return "index";
	}
	
    @RequestMapping("index")
    public String index(ModelMap model)
    {
    	List<Organization> orgas = orgasRepo.findAll(); 
    	model.addAttribute("orgas",orgas);
        return "index";
    }
    
    @RequestMapping("new")
    public String creationOrganisation()
    {
    	return "nouvelleOrganisation";
    }

    @PostMapping("newOrga")
    public RedirectView addNew(@RequestParam("name") String nom, @RequestParam("domain") String domaine,
    		@RequestParam("aliases") String aliases)
    {
    	Organization org = new Organization();
    	
    	org.setName(nom);
    	org.setDomain(domaine);
    	org.setAliases(aliases);
    	
    	orgasRepo.save(org);
    	
    	return new RedirectView("index");
    }
    
    @PostMapping("submit")
    public RedirectView submit(@PathVariable int id, Organization postedOrga) {
    	
    	if(postedOrga.getId() != 0)
    	{
    		id = postedOrga.getId(); 
    		Optional<Organization> opt = orgasRepo.findById(id);
	if(opt.isPresent()) {
    		Organization orga = opt.get();
    		copyFrom(postedOrga, orga);
    		orgasRepo.save(orga);
    	}
    	}
    	
    
    	
    	return new RedirectView("index");
    }
    
    
    
    @GetMapping("edit/{id}")
    public String frmEdit(@PathVariable int id, Model model) {
    	Optional<Organization> opt = orgasRepo.findById(id);
    	if(opt.isPresent()) {
    		model.addAttribute("orga",opt.get());
    		return "orgas/frm";
    	}
    	
    	return "orgas/404";
    }
    
    @PostMapping("submit")
    public RedirectView submitView(Organization postedOrga) {
    	orgasRepo.save(postedOrga);
    	return new RedirectView("index");
    }
    
    
    
    private void copyFrom(Organization source, Organization dest) {
    	dest.setName(source.getName());
    	dest.setDomain(source.getDomain());
    	dest.setAliases(source.getAliases());
    	dest.setGroupes(source.getGroupes());
    	dest.setVille(source.getVille());
    }
    
    
    @RequestMapping("404")
    public String notFound() {
    	return "404";
    }
    
  
}
