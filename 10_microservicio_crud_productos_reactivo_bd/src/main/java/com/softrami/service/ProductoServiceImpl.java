package com.softrami.service;

import java.time.Duration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softrami.model.Producto;
import com.softrami.repository.ProductosRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	ProductosRepository productosRepository;

	@Override
	public Flux<Producto> catalogo() {
		return productosRepository.findAll() // Flux<Producto>
				.delayElements(Duration.ofMillis(500));
	}

	@Override
	public Flux<Producto> productosCategoria(String categoria) {
		return productosRepository.findByCategoria(categoria);
	}

	@Override
	public Mono<Producto> productoCodigo(int cod) {
		return productosRepository.findById(cod);
	}

	@Override
	public Mono<Void> altaProducto(Producto producto) {
		return productoCodigo(producto.getCodProducto()) //Mono<Producto>
				.switchIfEmpty(Mono.just(producto).flatMap(p ->productosRepository.save(p)))//Mono<Producto>
				.then(); //Mono<Void>
	}

	@Override
	public Mono<Producto> eliminarProducto(int cod) {
		return productoCodigo(cod) //Mono<Producto>
				.flatMap(p -> productosRepository.deleteById(cod) //Mono<Void>
						.then(Mono.just(p))//Mono<Producto>	
				); //Mono<Producto>				
	}

	@Override
	public Mono<Producto> actualizarPrecio(int cod, double precio) {
		return productoCodigo(cod) //Mono<Producto>
				.flatMap(p ->{
					p.setPrecioUnitario(precio);
					return productosRepository.save(p);// Mono<Producto>
				}); //Mono<Producto>
	}

}
