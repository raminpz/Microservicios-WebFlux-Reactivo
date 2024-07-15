package com.softrami.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softrami.model.Producto;
import com.softrami.service.ProductoService;

@RestController
public class ProductosController {
	@Autowired
	ProductoService productoService;
	
	@GetMapping(value = "productos",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Producto> productos(){
		return productoService.catalogo();
	}
	
	@GetMapping(value = "productos/{categoria}")
	public List<Producto> productosCategoria(@PathVariable("categoria") String categoria){
		return productoService.productosCategoria(categoria);
	}
	
	@GetMapping(value = "producto")
	public Producto productoCodigo(@RequestParam("cod") int cod) {
		return productoService.productoCodigo(cod);
	}
	
	@PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void altaProducto(@RequestBody Producto producto) {
		productoService.altaProducto(producto);
	}
	
	@DeleteMapping(value = "eliminar")
	public Producto eliminarProducto(@RequestParam("cod") int cod) {
		return productoService.eliminarProducto(cod);
	}

	@PutMapping(value = "actualizar")
	public Producto actualizarProducto(@RequestParam("cod") int cod, @RequestParam("precio") double precio) {
		return productoService.actualizarPrecio(cod, precio);
	}
}
