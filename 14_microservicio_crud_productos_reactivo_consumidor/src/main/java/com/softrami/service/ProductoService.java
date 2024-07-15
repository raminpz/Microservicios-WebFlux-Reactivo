package com.softrami.service;


import com.softrami.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {
	Flux<Producto> catalogo();
	Flux<Producto> productosCategoria(String categoria);
	Mono<Producto> productoCodigo(int cod);
	Mono<Void> altaProducto(Producto producto);
	Mono<Producto> eliminarProducto(int cod);
	Mono<Producto> actualizarPrecio(int cod, double precio);

}
