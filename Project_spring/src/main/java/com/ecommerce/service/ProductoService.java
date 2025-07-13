package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.model.Producto;
import com.ecommerce.repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    public Producto getProductoByName(String productoName) {
        return productoRepository.findByNombre(productoName);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProductoPorId(Long id) {
        productoRepository.deleteById(id);
    }

    public void eliminarProductoPorNombre(String nombre) {
        productoRepository.deleteByNombre(nombre);
    }
}
