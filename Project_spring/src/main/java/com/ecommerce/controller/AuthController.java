package com.ecommerce.controller;

import com.ecommerce.model.Usuario;
import com.ecommerce.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String loginForm() {
        return "login"; // muestra login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {
        // lógica de autenticación (básica o simulada)
        Usuario usuario = usuarioService.buscarPorEmail(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            // Guardamos el usuario en sesión
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/mostrarProductos"; // o la página principal
        }
        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "login";
    }

    @GetMapping("/registro")
    public String registroForm() {
        return "registro"; // muestra registro.html
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombre,
            @RequestParam String apellido1,
            @RequestParam String apellido2,
            @RequestParam String email,
            @RequestParam String password) {
        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setApellido1(apellido1);
        nuevo.setApellido2(apellido2);
        nuevo.setEmail(email);
        nuevo.setPassword(password);
        usuarioService.guardarUsuario(nuevo);
        return "redirect:/"; // volver al login
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/"; // vuelve al login
    }
}
