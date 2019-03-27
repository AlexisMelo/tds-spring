package gestionscript.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/script/")
public class scriptController {
	
	@RequestMapping("new")
	public String newScript(Model model, HttpSession session) {
		
		
		return ("scripts/new");
	}

}
