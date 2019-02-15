package com.example.demo;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("hello")
	public @ResponseBody String index() {
		return "Hello world!";
	}
	
	@RequestMapping("hello/tp1")
	public String helloWithTemplate() {
		return "helloTemplate";
	}
	
	@RequestMapping("hello/dest/{who}")
	public String hello(@PathVariable String who, ModelMap model) {
		model.addAttribute("who", who);
		return "helloTemplate";
	}
	
}
