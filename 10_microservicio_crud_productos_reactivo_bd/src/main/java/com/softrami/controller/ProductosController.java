package com.softrami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
public class ProductosController {
	@Autowired
	ProductoService productoService;
	
	@GetMapping(value = "productos")
	public ResponseEntity<Flux<Producto>> productos(){
		return new ResponseEntity<> (productoService.catalogo(),HttpStatus.OK);
	}
	
	@GetMapping(value="productos/{categoria}")
	public ResponseEntity<Flux<Producto>> productosCategoria(@PathVariable("categoria") String categoria){
		return new ResponseEntity<>(productoService.productosCategoria(categoria),HttpStatus.OK);
	}
	
	@GetMapping(value = "producto")
	public ResponseEntity<Mono<Producto>> productoCodigo(@RequestParam("cod") int cod) {
		return new ResponseEntity<> (productoService.productoCodigo(cod),HttpStatus.OK);
	}
	
	@PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<Void>> altaProducto(@RequestBody Producto producto) {
		producto.setNuevo(true);
		return new ResponseEntity<> (productoService.altaProducto(producto),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "eliminar")
	public Mono<ResponseEntity<Producto>> eliminarProducto(@RequestParam("cod") int cod) {
		return productoService.eliminarProducto(cod)//Mono<Producto>
				.map(p -> new ResponseEntity<>(p,HttpStatus.OK))//Mono<ResponseEntity<Producto>> --> Aqui transformamos para dar respuesta Ok, cuando el producto existe
				.switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND))); // Pero cuando no existe dicho codigo de producto para eliminar, se ejecuta esta linea, con httpstatus NOT_FOUND
	}

	@PutMapping(value = "actualizar")
	public Mono<ResponseEntity<Producto>> actualizarProducto(@RequestParam("cod") int cod, @RequestParam("precio") double precio) {
		return productoService.actualizarPrecio(cod, precio)//Mono<Producto>
				.map(p -> new ResponseEntity<>(p,HttpStatus.OK))//Mono<ResponseEntity<Producto>> --> Aqui transformamos para dar respuesta Ok, cuando el producto existe
				.switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND))); // Pero cuando no existe dicho codigo de producto para actualizar, se ejecuta esta linea, con httpstatus NOT_FOUND
	}

}
