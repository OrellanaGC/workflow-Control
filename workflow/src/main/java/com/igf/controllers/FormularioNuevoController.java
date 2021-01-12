/**
 * 
 */
package com.igf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igf.modelo.Evento;

/**
 * @author daiv05
 *
 */

@Controller
@RequestMapping("/formulario")
public class FormularioNuevoController {
	@GetMapping("")
	public String inicio() {
		return "/formularioNuevo/index";
	}
}
