package gestionscript.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import gestionscript.entities.User;
import gestionscript.repositories.UserRepository;

@Controller
@RequestMapping("/")
public class userController {

	@Autowired
	private UserRepository repoUsers;
	
	@GetMapping("login")
	public String login(Model model) {
		
		model.addAttribute("nom","Pas connecté");
		return "users/login";
	}
	
	@PostMapping("/user/loginUser") 
	 public RedirectView loginUser(@RequestParam("email") String login,@RequestParam("password") String password)
	    {
	    	
			User test = repoUsers.findOneByEmail(login);
			if(test != null)
			{
				if(password.equals(test.getPassword())) {
					
			    	return new RedirectView("../index");
				}
			}
	    	return new RedirectView("../login");
	    }
}
