package com.igf.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.igf.modelo.TipoEvento;
import com.igf.negocio.servicios.TipoEventoService;

@Controller
@RequestMapping("/tiposEventos")
public class TipoEventoController {

	@Autowired
	private TipoEventoService tipoEventoService;

	// Listar
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("tipos", tipoEventoService.list());
		return "/tipoEvento/index";
	}

	// Vista Crear
	@GetMapping("/crear")
	public String create(Model model) {
		TipoEvento tipoEvento = new TipoEvento();
		model.addAttribute(tipoEvento);
		return "/tipoEvento/create";
	}

	// Guardar
	@PostMapping("/guardar")
	public String save(@Valid TipoEvento tipoEvento, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute(tipoEvento);
			return "/tipoEvento/create";
		}
		tipoEventoService.save(tipoEvento);
		return "redirect:/tiposEventos";
	}
	
	// Vista actualizar
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Long id, Model model) {
		if (tipoEventoService.find(id).isPresent()) {
			model.addAttribute("tipoEvento", tipoEventoService.find(id));
			return "/tipoEvento/edit";
		} else {
			return "redirect:/tiposEventos";
		}
	}
	
	// Elimnar
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable Long id, Model model) {
		if (tipoEventoService.exists(id)) {
			tipoEventoService.delete(id);
		}
		return "redirect:/tiposEventos";
	}
}
