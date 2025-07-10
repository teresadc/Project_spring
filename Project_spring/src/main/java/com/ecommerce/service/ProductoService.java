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
        return productoRepository.findByProducto(productoName);
    }

    public Producto guardarProducto(Producto productoName) {
        return productoRepository.save(productoName);
    }
}
