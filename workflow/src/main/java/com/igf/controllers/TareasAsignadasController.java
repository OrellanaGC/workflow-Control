/**
 * 
 */
package com.igf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Pc
 *
 */
@Controller
@RequestMapping("/tareas")
public class TareasAsignadasController {

	@GetMapping("")
	public String inicio(Model model) {
		return "/Tareas/index";
	}

}
