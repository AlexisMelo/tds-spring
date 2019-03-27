package gestionscript.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class accueilController {

	@RequestMapping("index")
	public String main(Model model, HttpSession session) {
		
		model.addAttribute("user",session.getAttribute("user"));
		return "index";
	}
}
