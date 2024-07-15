package com.softrami.service;

import com.softrami.model.Envio;

import reactor.core.publisher.Flux;

public interface EnviosService {
	Flux<Envio> pendientes();

}
