package com.softrami.service;

import com.softrami.model.Elemento;

import reactor.core.publisher.Flux;

public interface ElementosService {
	Flux<Elemento> elementosPorPrecio(double precioMax);

}
