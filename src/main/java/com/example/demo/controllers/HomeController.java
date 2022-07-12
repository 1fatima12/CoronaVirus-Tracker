package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.CoronaVirusService;

@Controller
public class HomeController {
	@Autowired
	private CoronaVirusService serv;
	@RequestMapping("/home")
      public String home(Model model) {
		model.addAttribute("all", serv.getAllStats());
		model.addAttribute("total", serv.getTotal());
    	  return "home";
      }
}
