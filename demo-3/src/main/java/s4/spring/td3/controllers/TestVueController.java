package s4.spring.td3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;

@Controller
@RequestMapping("/vue/")
public class TestVueController {

	@Autowired
	private VueJS vue;
	
	@GetMapping("test")
	public String test(Model model) {
		
		model.addAttribute("vue",vue);
		
		vue.addData("message","Hello world!");
		vue.addData("alertVisible",false);
		
		vue.addMethod("update", "this.message=\"Message modifié !\"");
		vue.addMethod("testAjax", Http.post("/vue/test/ajax", "this.ajaxMessage=response.data;this.alertVisible=true;"));
	

		return "vueJs/test";
		
	}
	
	@GetMapping("test/ajax")
	@ResponseBody
	public String testAjax() {
		return "Test ok";
	}
}