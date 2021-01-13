package com.igf.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.igf.modelo.Diagrama;
import com.igf.modelo.TipoEvento;
import com.igf.negocio.dao.diagramaDao;
import com.igf.negocio.servicios.DiagramaService;

import antlr.collections.List;

@Controller
@RequestMapping("/diagramas")
public class DiagramaController {
	@Autowired
	private DiagramaService diagramaService;

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
			ArrayList<diagramaDao> elementosa=new ArrayList<>(); 
			Diagrama diagrama = diagramaService.find(id).get();
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
			model.addAttribute("elementos",elementosa);
			model.addAttribute("diagrama", diagrama);
			/*
			for (diagramaDao diagramaDao : elementosa) {
				System.out.println(diagramaDao.getNombre());
				System.out.println(diagramaDao.getLineaPadre());
			}*/
			
			return "/disenoestatico1/index";
		}else {
			return "redirect:/diagramas";
		}
		
	}	
	// Eliminar
				@GetMapping("/eliminar/{id}")
				public String delete(@PathVariable Long id, Model model) {
					if (diagramaService.exists(id)) {
						diagramaService.delete(id);
					}
					return "redirect:/diagramas";
				}
	
}
