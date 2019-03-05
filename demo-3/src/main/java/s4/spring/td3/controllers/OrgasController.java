package s4.spring.td3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;

import s4.spring.td3.entities.*;
import s4.spring.td3.repositories.OrgasRepository;

@Controller
@RequestMapping("/orgas/")
public class OrgasController {

	@Autowired
	private VueJS vue;
	@Autowired
	private OrgasRepository orgasRepo;
	
	@GetMapping("")
	public String main(Model model) {
		
		model.addAttribute("vue", vue);
		List<Organization> orgas = orgasRepo.findAll();
		
		vue.addData("orgas", orgas);
		vue.addDataRaw("headers", "[{text:'Name', value:'name'},{text:'Domain', value:'domain'},{text:'Aliases', value:'aliases'}]");
		vue.addData("dialog", false);
		vue.addDataRaw("editedItem", "{}");
				
		vue.addDataRaw("editedIndex", "-1");
		vue.addComputed("formTitle", "(this.itemIndex==-1)?'Nouvelle orga':'Modification orga'");
		vue.addMethod("close", "this.dialog=false;");
		
		
		vue.addMethod("save","var self=this;"+Http.post("/rest/orgas/create","self.editedItem",
				"self.dialog=false; self.orgas.push(response.data); "));
		
		return "orgas/index";
		
	}
}
