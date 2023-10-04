package com.example.demo.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.Libro;
import com.example.demo.bean.Usuario;
import com.example.demo.repository.BaseDatos;

@Controller  //Lo convertimos en un servlet atiende peticiones http
@RequestMapping("")
public class Controlador {
	
	BaseDatos bd = new BaseDatos();
	Usuario usuario;
	
	@GetMapping("/")
	public String iniciar(Model model) {
		model.addAttribute("titulo","FORMULARIO DE ACCESO");
		return "login";
	}
	
	@PostMapping("/")
	public String login(Usuario usuario, Model model) {
		if (usuario.getNombre().equals("edu") && usuario.getPassword().equals("edu")) {
			ArrayList<Libro> libros = bd.getLibros();
			model.addAttribute("usuario",usuario);
			this.usuario = usuario;
			model.addAttribute("libros",libros);
			return "consulta";
	}else
			return "login";
	}
	
	@PostMapping("/insertar")
	public String insertar(Libro libro, Model model) {
		bd.inserta(libro);
		ArrayList<Libro> libros = bd.getLibros();
		model.addAttribute("usuario",this.usuario);
		model.addAttribute("libros",libros);
		return "consulta";
	}
	
	
	
	
}
