package gestionscript.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import gestionscript.entities.Category;
import gestionscript.entities.History;
import gestionscript.entities.Language;
import gestionscript.entities.Script;
import gestionscript.entities.User;
import gestionscript.repositories.CategoryRepository;
import gestionscript.repositories.HistoryRepository;
import gestionscript.repositories.LanguageRepository;
import gestionscript.repositories.ScriptRepository;
import gestionscript.repositories.UserRepository;

@Controller
@RequestMapping("/script/")
public class scriptController {
	
	@Autowired
	private ScriptRepository scriptRepo;
	
	@Autowired
	private CategoryRepository categorieRepo;
	
	@Autowired
	private LanguageRepository langageRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private HistoryRepository historyRepo;
	
	@RequestMapping("deletebase")
	public RedirectView supprimerbase() {
		historyRepo.deleteAll();
		scriptRepo.deleteAll();

		langageRepo.deleteAll();
		categorieRepo.deleteAll();

		userRepo.deleteAll();
		
		
		return new RedirectView("../user/login");
	}
	
	@RequestMapping("initbase")
	public RedirectView initialiser(){
	
		User user1 = new User();
		user1.setEmail("a@a.fr");
		user1.setPassword("az");
		user1.setIdentity("Carlos Ghosn");
		user1.setLogin("Super_Carlos");
		userRepo.saveAndFlush(user1);
		
		User user2 = new User();
		user2.setEmail("b@b.fr");
		user2.setPassword("az");
		user2.setIdentity("Jean Dujardin");
		user2.setLogin("OSS117");
		userRepo.saveAndFlush(user2);
		
		Language php = new Language();
		php.setName("PHP");
		langageRepo.saveAndFlush(php);
		
		Language C = new Language();
		C.setName("C");
		langageRepo.saveAndFlush(C);
		
		Language java = new Language();
		java.setName("Java");
		langageRepo.saveAndFlush(java);
		
		Language sql = new Language();
		sql.setName("SQL");
		langageRepo.saveAndFlush(sql);
		
		Category config = new Category();
		config.setName("Configuration de préférences");
		categorieRepo.saveAndFlush(config);
		
		Category init = new Category();
		init.setName("Initialisation de composants");
		categorieRepo.saveAndFlush(init);
		
		Category cool = new Category();
		cool.setName("Script cool");
		categorieRepo.saveAndFlush(cool);		
		
		return new RedirectView("../user/login");
	}
	
	//------------------------------------------------------------ajout---------------------------------------------------------------
	
	@RequestMapping("new")
	public String newScript(Model model, HttpSession session) {
		
		if(session.getAttribute("user") == null) {
			return "users/login";
		}

		model.addAttribute("user",session.getAttribute("user"));
		model.addAttribute("categories", categorieRepo.findAll());
		model.addAttribute("langages", langageRepo.findAll());

		return ("scripts/new");
	}
	
	@RequestMapping("delete/{id}")
	public RedirectView supprimerScript(@PathVariable("id") int id)
	{
		scriptRepo.deleteById(id);
		return new RedirectView("../../index");
	}
	
	//--------------------------------------------------------edit--------------------------------------------------------
	
	@RequestMapping("{id}")
	@GetMapping
	public String editScript(@PathVariable("id") int id, Model model, HttpSession session) {
		
		Script monScript = scriptRepo.findOneById(id);
		User userSession = (User) session.getAttribute("user");
		if(userSession == null) {
			return "users/login";
		}else if(userSession.getId() != monScript.getCreateur().getId()) {
			return "../index";
		}
		
		model.addAttribute("user",session.getAttribute("user"));
		
		
		List<Category> mesCategories = categorieRepo.findAll();
		Category categorySelectionnee = monScript.getCategorie();
		mesCategories.remove(categorySelectionnee);
		
		List<Language> mesLangages = langageRepo.findAll();
		Language langageSelectionne = monScript.getLangage();
		mesLangages.remove(langageSelectionne);
		
		model.addAttribute("categories", mesCategories);
		model.addAttribute("Cselect", categorySelectionnee);

		model.addAttribute("langages", mesLangages);
		model.addAttribute("Lselect", langageSelectionne);
		
		model.addAttribute("script",monScript);
		
		return "scripts/edit";
	}
	

	//--------------------------------------------------------submit------------------------------------------------------
	@PostMapping("submit")
	public RedirectView ajouterScript(Script script, HttpSession session,@Nullable HttpServletRequest request) {
		
		Optional<Script> opt = scriptRepo.findById(script.getId());
		if(opt.isPresent())
		{
			Script ancienScript = scriptRepo.findOneById(script.getId());
			
			History archive = new History();
			archive.setComment(request.getParameter("commentaire"));
			archive.setScript(ancienScript);
			archive.setContent(ancienScript.getContent());
			archive.setDate(new Date());
			
			History nouvelleArchive = historyRepo.save(archive);
			ancienScript.getHistory().add(nouvelleArchive);
					
			
			
		}
		
			User user = (User) session.getAttribute("user");

			if(script.getTitle() == "")
			{
				script.setTitle("Sans titre");
			}
			if(script.getContent() == "") {
				script.setContent("Script vide");
			}
			if(script.getDescription() == "") {
				script.setDescription("Aucune description renseignée");
			}
			
			script.setCreationDate(new Date());
			script.setCreateur(user);
			scriptRepo.save(script);
		
		
		return new RedirectView("../index");
	}
	

	

}
