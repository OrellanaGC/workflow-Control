package com.igf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class WorkflowApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WorkflowApplication.class, args);
		
	}
	
	@GetMapping
	public String index(Model model) {
		return "redirect:/diagramas";
	}

}
