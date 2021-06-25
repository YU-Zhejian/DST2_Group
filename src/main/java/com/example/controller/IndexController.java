package com.example.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		return "index";
	}

	@RequestMapping("/contact")
	public String contact(Map<String, Object> model) {
		return "contact";
	}
	@RequestMapping("/dosingGuideline")
	public String dosingGuideline(Map<String, Object> model) {
		return "dosingGuideline";
	}
	@RequestMapping("/drug")
	public String drug(Map<String, Object> model) {
		return "drug";
	}
	@RequestMapping("/drugLabel")
	public String drugLabel(Map<String, Object> model) {
		return "drugLabel";
	}
	@RequestMapping("/index")
	public String index(Map<String, Object> model) {
		return "index";
	}
	@RequestMapping("/login")
	public String login(Map<String, Object> model) {
		return "login";
	}
	@RequestMapping("/navi")
	public String navi(Map<String, Object> model) {
		return "navi";
	}
	@RequestMapping("/register")
	public String register(Map<String, Object> model) {
		return "register";
	}
	@RequestMapping("/result")
	public String result(Map<String, Object> model) {
		return "result";
	}

}
