/**
 * 
 */
package com.igf.controllers;

import java.util.Arrays;
import java.util.List;
import java.io.*;

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
		if (tareaService.exists(id)) {
			Tarea tarea = tareaService.find(id).get();
			/*
			 * Pool pool= tarea.getPool(); System.out.println(pool.getTareas().size());/
			 **/
			model.addAttribute(tarea);
			return "/formularioNuevo/index";
		} else {
			return "redirect:/diagramas";
		}

	}

	// Guardar variable de tarea
	@PostMapping("/variable")
	public String descripcionSave(Model model, @RequestParam("idTarea") Long id, @RequestParam("maxval") Integer maxval,
			@RequestParam("minval") Integer minval, @RequestParam("maxcar") Integer maxcar,
			@RequestParam("mincar") Integer mincar, @RequestParam(value = "required", required = false) String required,
			@RequestParam("nombrevariable") String nombre, @RequestParam("tipoVar") String tipoVariable,
			@RequestParam("opciones") String opciones, @RequestParam("idVariable") Long idVariable) {
		if (tareaService.exists(id)) {
			Tarea tarea = tareaService.find(id).get();
			DetalleVariable detalleVariable;
			if (idVariable != null) {
				detalleVariable = detalleVariableService.find(idVariable).get();
				for (OpcionesVariable opcion : detalleVariable.getOpcionesVariables()) {
					opcionesVariableService.delete(opcion.getId());
				}
				detalleVariable.setOpcionesVariables(null);
			} else {
				detalleVariable = new DetalleVariable();
			}
			if (maxval != null) {
				detalleVariable.setMaximo(maxval);
			}
			if (minval != null) {
				detalleVariable.setMinimo(minval);
			}
			if (maxcar != null) {
				detalleVariable.setMaxCaracter(maxcar);
			}
			if (mincar != null) {
				detalleVariable.setMinCaracter(mincar);
			}
			if (required != null) {
				detalleVariable.setRequerido(true);
			} else {
				detalleVariable.setRequerido(false);
			}
			if (tipoVariable.equals("Input Text") || tipoVariable.equals("Text Area") || tipoVariable.equals("Email")) {
				detalleVariable.setMaximo(null);
				detalleVariable.setMinimo(null);
				opciones = null;
			}
			if (tipoVariable.equals("Numero")) {
				detalleVariable.setMaxCaracter(null);
				detalleVariable.setMinCaracter(null);
				opciones = null;
			}
			if (tipoVariable.equals("CheckBox") || tipoVariable.equals("RadioCheck") || tipoVariable.equals("Select")) {
				detalleVariable.setMaximo(null);
				detalleVariable.setMinimo(null);
				detalleVariable.setMaxCaracter(null);
				detalleVariable.setMinCaracter(null);
			}
			detalleVariable.setNombreVariable(nombre);
			detalleVariable.setTipoVariable(tipoVariable);
			detalleVariable.setTarea(tarea);
			DetalleVariable detalleGuardado = detalleVariableService.save(detalleVariable);
			if (opciones != null && !opciones.isEmpty()) {
				List<String> opcionesList = Arrays.asList(opciones.split(","));
				for (String string : opcionesList) {
					OpcionesVariable opcionesV = new OpcionesVariable();
					opcionesV.setNombre(string);
					opcionesV.setDetalleVariable(detalleGuardado);
					opcionesVariableService.save(opcionesV);
				}
			}
		}
		return "redirect:/formulario/" + id.toString();
	}

	// Guardar descripción de tarea
	@PostMapping("/descripcion")
	public String variableSave(Model model, @RequestParam("idTarea") Long id,
			@RequestParam("descripcion") String descripcion) {
		if (tareaService.exists(id)) {
			Tarea tarea = tareaService.find(id).get();
			tarea.setDescripcion(descripcion);
			tareaService.save(tarea);
		}
		return "redirect:/formulario/" + id.toString();
	}

	// Traer un detalle tarea
	@GetMapping("/findDetalle/{id}")
	@ResponseBody
	public DetalleVariable find(@PathVariable Long id) {
		DetalleVariable detalleVariable = detalleVariableService.find(id).get();
		detalleVariable.getTarea().setPool(null);
		detalleVariable.getTarea().setDetalleVariables(null);
		detalleVariable.getOpcionesVariables();
		for (OpcionesVariable opcion : detalleVariable.getOpcionesVariables()) {
			opcion.setDetalleVariable(null);
		}
		return detalleVariable;
	}

	@GetMapping("/generar")
	public String generarFormulario(Model model, @RequestParam("idTarea") Long id) {
		if (tareaService.exists(id)) {
			Tarea tarea = tareaService.find(id).get();
			try {
				BufferedWriter bw = new BufferedWriter(
						new FileWriter("/home/aleml98-mint/Desktop/" + id.toString() + ".blade.html"));
				//Copiar inicio de formulario
				BufferedReader inicio = new BufferedReader(new FileReader(
						"/home/aleml98-mint/Desktop/Piezas formulario/Inicio.txt"));
				String s;
				while ((s = inicio.readLine()) != null) {
					bw.write(s + "\n");
				}
				inicio.close();
				for (DetalleVariable variable : tarea.getDetalleVariables()) {
					String nombreV = variable.getId().toString() + variable.getNombreVariable();
					if (variable.getTipoVariable().equals("Input Text")) {
						String restricciones="";
						if(variable.getMaxCaracter()!=null) { restricciones= restricciones+ "maxlength='"+variable.getMaxCaracter().toString()+"' ";}
						if(variable.getMinCaracter()!=null) { restricciones= restricciones+ "minlength='"+variable.getMinCaracter().toString()+"' ";}
						if(variable.isRequerido()) { restricciones= restricciones+ "required ";}
						bw.write("<div class='form-group'><label for='" + nombreV + "'>" + variable.getNombreVariable()
								+ ": </label>" + "<input "+restricciones+"class='form-control' type='text' name='" + nombreV + "' id='"
								+ nombreV + "'></div><hr>\n");
					}
					if (variable.getTipoVariable().equals("Text Area")) {
						String restricciones="";
						if(variable.getMaxCaracter()!=null) { restricciones= restricciones+ "maxlength='"+variable.getMaxCaracter().toString()+"' ";}
						if(variable.getMinCaracter()!=null) { restricciones= restricciones+ "minlength='"+variable.getMinCaracter().toString()+"' ";}
						if(variable.isRequerido()) { restricciones= restricciones+ "required ";}
						bw.write("<div class='form-group'><label for='" + nombreV + "'>" + variable.getNombreVariable()
						+ ": </label>" + "<textarea "+restricciones+"class='form-control' cols='30' rows='10' name='" + nombreV + "' id='"
						+ nombreV + "'></textarea></div><hr>\n");						
					}					
					if (variable.getTipoVariable().equals("Email")) {
						String restricciones="";
						if(variable.getMaxCaracter()!=null) { restricciones= restricciones+ "maxlength='"+variable.getMaxCaracter().toString()+"' ";}
						if(variable.getMinCaracter()!=null) { restricciones= restricciones+ "minlength='"+variable.getMinCaracter().toString()+"' ";}
						if(variable.isRequerido()) { restricciones= restricciones+ "required ";}
						bw.write("<div class='form-group'><label for='" + nombreV + "'>" + variable.getNombreVariable()
						+ ": </label>" + "<input "+restricciones+"class='form-control' type='email' name='" + nombreV + "' id='"
						+ nombreV + "'></div><hr>\n");
					}
					if (variable.getTipoVariable().equals("Numero")) {
						String restricciones="";
						if(variable.getMaximo()!=null) { restricciones= restricciones+ "max='"+variable.getMaximo().toString()+"' ";}
						if(variable.getMinimo()!=null) { restricciones= restricciones+ "min='"+variable.getMinimo().toString()+"' ";}
						if(variable.isRequerido()) { restricciones= restricciones+ "required ";}
						bw.write("<div class='form-group'><label for='" + nombreV + "'>" + variable.getNombreVariable()
						+ ": </label>" + "<input "+restricciones+"class='form-control' type='number' name='" + nombreV + "' id='"
						+ nombreV + "'></div><hr>\n");
					}
					if (variable.getTipoVariable().equals("CheckBox")) {
						bw.write("<label for='" + nombreV + "'>" + variable.getNombreVariable()+ ": </label><div class='form-check form-check-inline'>\n");						
						for (OpcionesVariable opcion : variable.getOpcionesVariables()) {
							String nombreO= opcion.getId().toString()+opcion.getNombre();
							bw.write("<input class='form-check-input' type='checkbox' name='"+nombreV+"[]' value='"+opcion.getNombre()+"' id='"
							+ nombreO+"'> <label class='form-check-label' for='"+nombreO+"'>"+opcion.getNombre()+"</label>"		);
						}
						bw.write("</div><hr>\n");
					}
					if (variable.getTipoVariable().equals("RadioCheck")) {
						bw.write("<label for='" + nombreV + "'>" + variable.getNombreVariable()+ ": </label><div class='form-check form-check-inline'>\n");						
						for (OpcionesVariable opcion : variable.getOpcionesVariables()) {
							String nombreO= opcion.getId().toString()+opcion.getNombre();
							bw.write("<input class='form-check-input' type='radio' name='"+nombreV+"' value='"+opcion.getNombre()+"' id='"
							+ nombreO+"'> <label class='form-check-label' for='"+nombreO+"'>"+opcion.getNombre()+"</label>"		);
						}
						bw.write("</div><hr>\n");
					}
					if (variable.getTipoVariable().equals("Select")) {
						bw.write("<div class='form-group'> <label for='"+nombreV+"'>"+variable.getNombreVariable()+": </label>\n"
								+ "<select class='form-control' name='"+nombreV+"' id='"+nombreV+"'>");
						for (OpcionesVariable opcion : variable.getOpcionesVariables()) {
							//String nombreO= opcion.getId().toString()+opcion.getNombre();
							bw.write("<option>"+opcion.getNombre()+"</option>");
						}
						bw.write("</select><hr>\n");
					}
				}
				//Copiar final de formulario
				BufferedReader finalt = new BufferedReader(new FileReader(
						"/home/aleml98-mint/Desktop/Piezas formulario/final.txt"));
				String f;
				while ((f = finalt.readLine()) != null) {
					bw.write(f + "\n");
				}
				finalt.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "redirect:/formulario/" + id.toString();
	}

	// Eliminar detalle-variable
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable Long id, Model model) {
		if (detalleVariableService.exists(id)) {
			detalleVariableService.delete(id);
		}
		return "redirect:/formulario";
	}

	// Editar campo de una tarea INCOMPLETO
	@GetMapping("/editar/{idTarea}")
	public String editar(Model model, @PathVariable("idTarea") Long id) {
		if (tareaService.exists(id)) {
			Tarea tarea = tareaService.find(id).get();
			model.addAttribute("idTarea", tarea);
		}
		return "/formularioNuevo/edit";
	}
}
