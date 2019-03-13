package s4.spring.td3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;

@Controller
@RequestMapping("/components/")
public class TestCompo {

	@Autowired
	private VueJS vue;
	
	@GetMapping("dialog")
	public String indexDialog(Model model) {
		model.addAttribute("vue",vue);
		vue.addData("msg","Voulez-vous afficher une alerte ?");
		vue.addMethod("validation", "alert('validation ! ');");
		return "td4_components/indexDialog";
	}
	
	@GetMapping("table")
	public String indexTable(Model model) {
		
		model.addAttribute("vue",vue);
		vue.addDataRaw("headers", "[{text:'Nom',value:'nom'}]");
		vue.addDataRaw("items", "[{nom:'QUENTIN'},{nom:'LE'},{nom:'BETA'}]");

		return "td4_components/indexTable";
	}
}
