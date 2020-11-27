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
@RequestMapping("/tiposEventos")
public class TipoEventoController {
	
	@Autowired
	private TipoEventoService tipoEventoService;
	
	
	//Listar
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("tipos", tipoEventoService.list());
		return "/tipoEvento/index";
	}
	
	//Vista Crear
	@GetMapping("/create")
	public String create(@PathVariable Long id, Model model) {
		return "create";
	}
	
	//Guardar
	@PostMapping("")	
	public String store(Model model) {
		TipoEvento tipoEvento= new TipoEvento();
		tipoEventoService.save(tipoEvento);
		return "redirect:/tipoEvento";
	}	
	
	
	//Vista actualizar
	@GetMapping("/{id}")
	public String edit(@PathVariable Long id, Model model) {	
		if(tipoEventoService.find(id).isPresent()) {
			model.addAttribute("tipoEvento", tipoEventoService.find(id));
			return "update";
		}else {
			return "redirect:/";
		}
		
	}
	
	//Actualizar	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, Model model) {
		return "";
	}
	
	//Elimnar
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id ,Model model) {		
		if(tipoEventoService.find(id).isPresent()) {
			tipoEventoService.delete(id);			
		}
		return "redirect:/";
	}
}
