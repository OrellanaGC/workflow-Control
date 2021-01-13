package com.igf.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;

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
import com.igf.modelo.User;
import com.igf.negocio.servicios.DiagramaService;
import com.igf.negocio.servicios.UserService;

import antlr.collections.List;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	@Autowired	
	private DiagramaService diagramaService;
	@Autowired
	private UserService userService;
	
	public static String uploadDirectory= System.getProperty("user.dir")+"/diagramas";
	
	@GetMapping("")
	public String uploadPage(Model model) {
		return "/upload/uploadPage";
	}
	
	//guardar archivo diagrama
	@PostMapping("")
	public String uploadPageSave(Model model, @RequestParam("files") MultipartFile[] files) {		
		String id="";
		//StringBuilder filenames= new StringBuilder();
		for(MultipartFile file : files) {
			String nombre= file.getOriginalFilename();
			//Validación de documento nulo
			if(file.getSize()<=0) {
				model.addAttribute("msg", "Tiene que subir un archivo");				
				return "/upload/uploadPage";
			}
			if(nombre.endsWith("xml")) {
				nombre= nombre.substring(0, nombre.length() - 4);					
			}else {
				nombre= nombre.substring(0, nombre.length() - 5);					
			}			
			if(!diagramaService.findBynombre(nombre).isEmpty()) {
				model.addAttribute("msg", "Este diagrama ha sido subido al sistema con interioridad");				
				return "/upload/uploadPage";
			}
			//Validación de archivos mayor a 5 MB
			if(file.getSize()>5000000) {
				model.addAttribute("msg", "El archivo que intenta subir es superior a 5 MB");				
				return "/upload/uploadPage";
			}			
			//Validación de archivos permitidos, xml y proc
			if(!file.getOriginalFilename().endsWith("xml") && !file.getOriginalFilename().endsWith("proc")) {
				model.addAttribute("msg", "solo se permiten archivos con extension '.xml' o '.proc'");				
				return "/upload/uploadPage";
			}
			Path filenameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
			//filenames.append(file.getOriginalFilename());
			try {
				Files.write(filenameAndPath, file.getBytes());
				Diagrama diagrama = new Diagrama();
				diagrama.setNombre(nombre);
				diagrama.setPathArchivo(uploadDirectory+"/"+file.getOriginalFilename());
				User usuario= userService.find("admin@admin.com").get();
				/* Prueba de manejo de fechas con springboot
				System.out.println(usuario.toString());				
				System.out.println(usuario.getFecha_nac());
				Date currentSqlDate = new Date(System.currentTimeMillis());
				usuario.setFecha_nac(currentSqlDate);
				System.out.println(usuario.getFecha_nac());*/
				diagrama.setUser(usuario);				
				id=diagramaService.save(diagrama).getId().toString();
				
			} catch (Exception e) {
				e.printStackTrace();				
			}						 
		}
		//model.addAttribute("msg", "Archivos subidos exitosamente: "+filenames.toString());
		return "redirect:/diagramas/"+ id;		
	}
	//Subida de imagen diagrama
	@PostMapping("/image")
	public String uploadImageSave(Model model, @RequestParam("image") MultipartFile image, @RequestParam("idDiagrama") Long id) {
		if(image.getSize()<=0) {
			model.addAttribute("msg", "Tiene que subir un archivo");				
			return "/upload/uploadPage";
		}
		//ValidaciÃ³n de archivos mayor a 5 MB
		if(image.getSize()>5000000) {
			model.addAttribute("msg", "El archivo que intenta subir es superior a 5 MB");				
			return "/upload/uploadPage";
		}
		String nombre= id.toString()+ image.getOriginalFilename();
		//Traer diagrama al que se le insertara la imagen
		Diagrama diagrama = diagramaService.find(id).get();
		String uploadImageDirectory=System.getProperty("user.dir")+"/src/main/resources/static/img/diagramasImages" ;
		Path filenameAndPath = Paths.get(uploadImageDirectory,nombre);
		try {
			Files.write(filenameAndPath, image.getBytes());			
			diagrama.setPathImagen("/img/diagramasImages/" +nombre);
			diagramaService.save(diagrama);
		} 
		catch (Exception e) {
			e.printStackTrace();				
		}
		return "redirect:/diagramas/"+ id.toString();
	}
}
