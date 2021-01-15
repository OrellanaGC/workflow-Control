package com.igf.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.expression.Lists;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.igf.modelo.Diagrama;
import com.igf.modelo.Pool;
import com.igf.modelo.Tarea;
import com.igf.modelo.TipoEvento;
import com.igf.negocio.dao.diagramaDao;
import com.igf.negocio.servicios.DiagramaService;
import com.igf.negocio.servicios.PoolService;
import com.igf.negocio.servicios.TareaService;

import antlr.collections.List;

@Controller
@RequestMapping("/diagramas")
public class DiagramaController {
	@Autowired
	private DiagramaService diagramaService;
	@Autowired
	private PoolService poolService;
	@Autowired
	private TareaService tareaService;

	//Vista Listar
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("diagramas", diagramaService.list());
		return "/diagrama/index";
	}
	
	// Vista de mostrar diagrama individual
	@GetMapping("/{id}")
	public String show(@PathVariable Long id,Model model) {
		if(diagramaService.exists(id)) {
			//Declaracion de la lista de elementos del diagrama bpmn			 
			Diagrama diagrama = diagramaService.find(id).get();
			ArrayList<diagramaDao> elementosa=new ArrayList<>();
			if(diagrama.getConfirmado()==null || diagrama.getConfirmado()==false) {				
				//Declaración de string para nombres de lineas;
				String carrilPadrE="";
				//Leer informacion del archivo xml y traerla (pool y tasks)
				DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
				try {
					DocumentBuilder builder = factory.newDocumentBuilder();				
					Document doc= builder.parse(diagrama.getPathArchivo());
					NodeList elementos = doc.getElementsByTagName("elements");
					for (int i = 0; i < elementos.getLength(); i++) {
						Node nodo = elementos.item(i);
						if(nodo.getNodeType()==Node.ELEMENT_NODE) {
							Element element= (Element) nodo;
							String type =element.getAttribute("xmi:type");
							if(type.endsWith("Lane") || type.endsWith("Task")) {
								diagramaDao elementoDia= new diagramaDao();
								elementoDia.setNombre(element.getAttribute("name"));
								if(type.endsWith("Task")) {
									elementoDia.setLineaPadre(carrilPadrE);
								}else {
									carrilPadrE= elementoDia.getNombre();								
								}
								elementosa.add(elementoDia);
							}						
						}
					}
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}else {
				Set<Pool> carriles =  diagrama.getPools();				
				for (Pool pool : carriles) {
					diagramaDao elementoDia= new diagramaDao();
					elementoDia.setNombre(pool.getNombre());
					elementosa.add(elementoDia);
					Set<Tarea> tareas= pool.getTareas();					
					//System.out.println("Carril: "+ pool.getId()+ " " + pool.getNombre());
					for (Tarea tarea : tareas) {
						//System.out.println("tarea: "+tarea.getId()+ " " +tarea.getNombre());
						diagramaDao elementoDia2= new diagramaDao();
						elementoDia2.setLineaPadre(elementoDia.getNombre());
						elementoDia2.setNombre(tarea.getNombre());
						elementoDia2.setId(tarea.getId());
						elementoDia2.setDescripcion(tarea.getDescripcion());						
						elementosa.add(elementoDia2);						
					}					
				}				
			}
			/*
			for (diagramaDao diagramaDao : elementosa) {
				System.out.println(diagramaDao.getNombre());
				System.out.println(diagramaDao.getLineaPadre());
			}*/		
			model.addAttribute("elementos",elementosa);
			model.addAttribute("diagrama", diagrama);
			
			return "/diagrama/show";
		}else {
			return "redirect:/diagramas";
		}		
	}
	
	@PostMapping("/confirm")
	public String confirmacion(Model model, @RequestParam("idDiagrama") Long id) {
		Diagrama diagrama = diagramaService.find(id).get();
		System.out.println(diagrama.getId());
		System.out.println(id);
		System.out.println(diagrama.getConfirmado());
		if(diagrama.getConfirmado()==null || diagrama.getConfirmado()==false) {			
			//Leer informacion del archivo xml y traerla (pool y tasks)
			DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();				
				Document doc= builder.parse(diagrama.getPathArchivo());
				NodeList elementos = doc.getElementsByTagName("elements");
				Pool poolPadre= new Pool();
				for (int i = 0; i < elementos.getLength(); i++) {
					Node nodo = elementos.item(i);
					if(nodo.getNodeType()==Node.ELEMENT_NODE) {
						Element element= (Element) nodo;
						String type =element.getAttribute("xmi:type");
						if(type.endsWith("Lane") || type.endsWith("Task")) {
							if(type.endsWith("Task")) {
								Tarea tarea= new Tarea();
								tarea.setNombre(element.getAttribute("name"));
								tarea.setPool(poolPadre);
								tareaService.save(tarea);
							}else {
								Pool pool = new Pool();
								pool.setDiagrama(diagrama);
								pool.setNombre(element.getAttribute("name"));								
								poolPadre= poolService.save(pool);
							}							
						}						
					}
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			diagrama.setConfirmado(true);
			diagramaService.save(diagrama);
			return "redirect:/diagramas/"+ id.toString();
		}else {
			return "redirect:/diagramas";
		}
		
		
	}	
	// Eliminar diagrama (BDD y carpeta diagramas)
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable Long id, Model model) {
		if (diagramaService.exists(id)) {
			File file = new File(diagramaService.find(id).get().getPathArchivo());
			boolean val = file.delete();
			
			//No se si validaran esto asi que lo dejo asi
			if(val){ //si no hay ningun error al borrar el archivo, borramos de la base de datos
				diagramaService.delete(id);
			} else {
				//Error al eliminar archivo
			}
		}
		return "redirect:/diagramas";
	}

}
