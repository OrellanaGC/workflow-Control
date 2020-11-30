/**
 * 
 */

package com.igf.controllers;

/**
 * @author user-1
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igf.modelo.Evento;
import com.igf.negocio.servicios.EventoService;


@Controller
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private EventoService eventoService;

	// Listar
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("eventos", eventoService.list());
		return "/evento/index";
	}

	// Vista Crear
	@GetMapping("/crear")
	public String create(Model model) {
		Evento evento = new Evento();
		model.addAttribute(evento);
		return "/evento/create";
	}

	// Guardar
	@PostMapping("/guardar")
	public String save(Evento evento, Model model) {
		eventoService.save(evento);
		return "redirect:/eventos";
	}

	// Vista actualizar
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Long id, Model model) {
		if (eventoService.find(id).isPresent()) {
			model.addAttribute("evento", eventoService.find(id));
			return "/evento/edit";
		} else {
			return "redirect:/eventos";
		}
	}

	// Eliminar
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable Long id, Model model) {
		if (eventoService.exists(id)) {
			eventoService.delete(id);
		}
		return "redirect:/eventos";
	}
}
