package gestionscript.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import gestionscript.entities.User;
import gestionscript.repositories.UserRepository;

@Controller
@RequestMapping("/user/")
public class userController {

	@Autowired
	private UserRepository repoUsers;
	
	@RequestMapping("login")
	public String login(Model model, HttpSession session) {
		model.addAttribute("user",session.getAttribute("user"));
		return "users/login";
	}
	
	@PostMapping("loginUser") 
	 public RedirectView loginUser(@RequestParam("email") String login,@RequestParam("password") String password, HttpSession session,Model model)
    {
    	
		User monUser = repoUsers.findOneByEmail(login);
		if(monUser != null)
		{
			if(password.equals(monUser.getPassword())) {
				
				session.setAttribute("user",monUser);
				model.addAttribute("user",monUser);
				return new RedirectView("../index");

			}
		}
		return new RedirectView("login");

    }
	
	@RequestMapping("logout")
	public RedirectView logoutUser(HttpSession session, Model model)
	{
		session.removeAttribute("user");
		model.addAttribute("user",null);
		return new RedirectView("login");

	}
}
