package com.ruta.sanJuanDePuelenje.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	//@GetMapping("/login")
	@RequestMapping(value = {"/login", "/"} , method = RequestMethod.GET )
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {
		// validamos si el usuario ya ha iniciado seccion anteriormente
		if (principal != null) {
			flash.addFlashAttribute("info", "Ya ha iniciado sesión anteriormente");
			return "redirect:/";
		}
		if (error != null) {
			model.addAttribute("error", "Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo");
		}
		if(logout != null) {
			model.addAttribute("sucess", "Ha cerrado sesión con éxito");
		}
		return "login";
	}
}
