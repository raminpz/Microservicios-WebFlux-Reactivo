package com.softrami.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.softrami.model.Elemento;

import reactor.core.publisher.Flux;

@Service
public class ElementoServiceImpl implements ElementosService {
	String url="http://localhost:8081";
	@Autowired
	WebClient webClient;
	@Override
	public Flux<Elemento> elementosPorPrecio(double precioMax) {
		//WebClient webClient=WebClient.create(url);
		return webClient
				.get()
				.uri(url+"/elementos/"+precioMax)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Elemento.class);
	}

}
