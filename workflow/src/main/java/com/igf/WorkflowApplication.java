package com.igf;

import java.sql.Date;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.igf.modelo.User;
import com.igf.negocio.servicios.UserService;

@Controller
@SpringBootApplication
public class WorkflowApplication {
	@Autowired
	private UserService userService;	
	
	public static void main(String[] args) {
		SpringApplication.run(WorkflowApplication.class, args);
	}
	
	@GetMapping
	public String index(Model model) {
		return "redirect:/diagramas";
	}
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
		if(!userService.exists("admin@admin")) {
			User user = new User();
			user.setName("admin");
			user.setEmail("admin@admin.com");
			user.setPassword("123456789");
			user.setSexo("Hombre");
			Date date = new Date(System.currentTimeMillis());
			user.setFecha_nac(date);
			userService.save(user);
		}	
	}
	
}
