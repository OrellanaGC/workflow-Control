/**
 * 
 */
package com.igf.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.expression.Lists;

import com.igf.modelo.DetalleVariable;
import com.igf.modelo.Evento;
import com.igf.modelo.OpcionesVariable;
import com.igf.modelo.Pool;
import com.igf.modelo.Tarea;
import com.igf.negocio.servicios.DetalleVariableService;
import com.igf.negocio.servicios.OpcionesVariableService;
import com.igf.negocio.servicios.TareaService;

/**
 * @author daiv05
 *
 */

@Controller
@RequestMapping("/formulario")
public class FormularioNuevoController {
	@Autowired
	private TareaService tareaService;	
	@Autowired
	private DetalleVariableService detalleVariableService;
	@Autowired
	private OpcionesVariableService opcionesVariableService;
	
	@GetMapping("/{id}")
	public String inicio(Model model, @PathVariable Long id) {
		if(tareaService.exists(id)) {
			Tarea tarea = tareaService.find(id).get();
			/*Pool pool= tarea.getPool();
			System.out.println(pool.getTareas().size());/**/
			model.addAttribute(tarea);
			return "/formularioNuevo/index";
		}else {
			return "redirect:/diagramas";
		}
		
	}
	
	//Guardar variable de tarea
	@PostMapping("/variable")
	public String descripcionSave(Model model, @RequestParam("idTarea") Long id, @RequestParam("maxval") Integer maxval,
			@RequestParam("minval") Integer minval, @RequestParam("maxcar") Integer maxcar, @RequestParam("mincar") Integer mincar,
			@RequestParam(value = "required", required = false) String required, @RequestParam("nombrevariable") String nombre,
			@RequestParam("tipoVar") String tipoVariable, @RequestParam("opciones") String opciones) {
		if(tareaService.exists(id)) {
			Tarea tarea = tareaService.find(id).get();
			DetalleVariable detalleVariable = new DetalleVariable();
			if(maxval!= null) {
				detalleVariable.setMaximo(maxval);
			}
			if(minval!=null) {
				detalleVariable.setMinimo(minval);
			}
			if(maxcar!=null) {
				detalleVariable.setMaxCaracter(maxcar);
			}
			if(mincar!=null) {
				detalleVariable.setMinCaracter(mincar);
			}
			if(required!=null) {
				detalleVariable.setRequerido(true);
			}
			detalleVariable.setNombreVariable(nombre);
			detalleVariable.setTipoVariable(tipoVariable);
			detalleVariable.setTarea(tarea);
			DetalleVariable detalleGuardado= detalleVariableService.save(detalleVariable);
			if(opciones!=null) {
				List<String> opcionesList= Arrays.asList(opciones.split(","));
				for (String string : opcionesList) {
					OpcionesVariable opcionesV = new OpcionesVariable();
					opcionesV.setNombre(string);
					opcionesV.setDetalleVariable(detalleGuardado);
					opcionesVariableService.save(opcionesV);
				}
			}
		}
		return "redirect:/formulario/"+id.toString();
	}

	//Guardar descripción de tarea
	@PostMapping("/descripcion")
	public String variableSave(Model model, @RequestParam("idTarea") Long id, @RequestParam("descripcion") String descripcion) {
		if(tareaService.exists(id)) {
			Tarea tarea = tareaService.find(id).get();
			tarea.setDescripcion(descripcion);
			tareaService.save(tarea);
		}
		return "redirect:/formulario/"+id.toString();
	}
	
	//Traer un detalle tarea
	@GetMapping("/findDetalle/{id}")
	@ResponseBody
	public DetalleVariable find(@PathVariable Long id) {
		return detalleVariableService.find(id).get();	
	}
	// Eliminar detalle-variable
		@GetMapping("/eliminar/{id}")
		public String delete(@PathVariable Long id, Model model) {
			if (detalleVariableService.exists(id)) {
				detalleVariableService.delete(id);
			}
			return "redirect:/formulario";
		}
	
}


