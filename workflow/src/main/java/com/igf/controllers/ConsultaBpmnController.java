/**
 * 
 */
package com.igf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Pc
 *
 */
@Controller
@RequestMapping("/consultar")
public class ConsultaBpmnController {
	@GetMapping("")
	public String inicio() {
		return "/diseñoestatico1/index";
	}
}
