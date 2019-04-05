package gestionscript.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import gestionscript.entities.Script;
import gestionscript.entities.User;
import gestionscript.repositories.CategoryRepository;
import gestionscript.repositories.HistoryRepository;
import gestionscript.repositories.LanguageRepository;
import gestionscript.repositories.ScriptRepository;
import gestionscript.repositories.UserRepository;

@Controller
@RequestMapping("/")
public class accueilController {

	@Autowired
	private ScriptRepository scriptRepo;
	
	@RequestMapping("index")
	public String main(Model model, HttpSession session) {
		
		if(session.getAttribute("user") == null) {
			return "users/login";
		}
		
		model.addAttribute("scripts",scriptRepo.findByCreateur((User)session.getAttribute("user")));
		
		model.addAttribute("user",session.getAttribute("user"));
		return "index";
	}
}
