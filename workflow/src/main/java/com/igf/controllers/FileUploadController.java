package com.igf.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.igf.modelo.Diagrama;
import com.igf.negocio.servicios.DiagramaService;

import antlr.collections.List;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	@Autowired
	private DiagramaService diagramaService;
	
	
	public static String uploadDirectory= System.getProperty("user.dir")+"/diagramas";
	
	@GetMapping("")
	public String uploadPage(Model model) {
		return "/upload/uploadPage";
	}
	
	//guardar archivo
	@PostMapping("")
	public String uploadPageSave(Model model, @RequestParam("files") MultipartFile[] files) {
		String id="";
		StringBuilder filenames= new StringBuilder();
		for(MultipartFile file : files) {
			String nombre= file.getOriginalFilename();
			if(nombre.endsWith("xml")) {
				nombre= nombre.substring(0, nombre.length() - 4);					
			}else {
				nombre= nombre.substring(0, nombre.length() - 5);					
			}			
			if(!diagramaService.findBynombre(nombre).isEmpty()) {
				model.addAttribute("msg", "Este diagrama ha sido subido al sistema con interioridad");				
				return "/upload/uploadPage";
			}
			if(file.getSize()>5000000) {
				model.addAttribute("msg", "El archivo que intenta subir es superior a 5 MB");				
				return "/upload/uploadPage";
			}
			if(!file.getOriginalFilename().endsWith("xml") && !file.getOriginalFilename().endsWith("proc")) {
				model.addAttribute("msg", "solo se permiten archivos con extension '.xml' o '.proc'");				
				return "/upload/uploadPage";
			}
			Path filenameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
			filenames.append(file.getOriginalFilename());
			try {
				Files.write(filenameAndPath, file.getBytes());
				Diagrama diagrama = new Diagrama();
				diagrama.setNombre(nombre);
				diagrama.setPathArchivo(uploadDirectory+"/"+file.getOriginalFilename());
				id=diagramaService.save(diagrama).getId().toString();
				
			} catch (Exception e) {
				e.printStackTrace();				
			}						 
		}
		//model.addAttribute("msg", "Archivos subidos exitosamente: "+filenames.toString());
		return "redirect:/diagramas/"+ id;		
	}
	
}
