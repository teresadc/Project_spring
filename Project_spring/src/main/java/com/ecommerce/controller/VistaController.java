package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.model.Producto;
import com.ecommerce.model.Usuario;
import com.ecommerce.service.ProductoService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class VistaController {

    @Autowired
    private ProductoService productoService; // inyectamos el servicio

    @GetMapping("/mostrarProductos")
    public String mostrarFormulario(Model model, HttpSession session) {
        // Recuperamos el usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        model.addAttribute("usuario", usuario);

        // Si el usuario no está logueado, redirige al login
        if (usuario == null) {
            return "redirect:/";
        }
        // obtenemos la lista de productos desde la base de datos
        List<Producto> productos = productoService.getAllProducto();
        model.addAttribute("productos", productos);
        try {
            return "mostrarProductos";
        } catch (Exception e) {
            e.printStackTrace(); // esto debería mostrar si no encuentra la vista
            return "error"; // solo si existe otra plantilla "error.html"
        }
    }

    @PostMapping("/guardar")
    public String guardarProducto(@RequestParam("nombre") String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            // creamos un nuevo producto con el nombre recibido
            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(nombre);

            // guardamos el producto en la base de datos via servicio
            productoService.guardarProducto(nuevoProducto);
        }
        return "redirect:/mostrarProductos"; // redirigimos para que se actualice la lista
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProductoPorId(id);
        return "redirect:/mostrarProductos"; // Redirige a la lista principal
    }

}