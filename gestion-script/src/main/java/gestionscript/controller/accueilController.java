package gestionscript.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class accueilController {

	@GetMapping("index")
	public String main(Model model) {
		
		return "index";
	}
}
