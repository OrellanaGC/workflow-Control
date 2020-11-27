package com.igf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igf.modelo.TipoEvento;
import com.igf.negocio.servicios.TipoEventoService;

@Controller
@RequestMapping("/tipoEvento")
public class TipoEventoController {
	
	@Autowired
	private TipoEventoService tipoEventoService;
	
	
	//Listar
	@GetMapping
	public String index(Model model) {
		model.addAttribute("tipos", tipoEventoService.list());
		return "/tipoEvento/index";
	}
	
	//Vista Crear
	
	//Vista Actualizar
	
	//Guardar y actualizar
	@PostMapping("/")	
	public String save(Model model) {
		TipoEvento tipoEvento= new TipoEvento();
		tipoEventoService.save(tipoEvento);
		return "redirect:/tipoEvento";
	}	
	
	//Elimnar

}
