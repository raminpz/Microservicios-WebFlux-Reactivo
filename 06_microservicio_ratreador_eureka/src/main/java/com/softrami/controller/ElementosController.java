package com.softrami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.softrami.model.Elemento;
import com.softrami.service.ElementosService;

import reactor.core.publisher.Flux;

@RestController
public class ElementosController {
	@Autowired
	ElementosService elementosService;
	
	@GetMapping(value = "elementos/{precio}")
	public ResponseEntity<Flux<Elemento>> elementosPrecio(@PathVariable("precio") double precioMax){
		return new ResponseEntity<>(elementosService.elmentosPrecioMax(precioMax), HttpStatus.OK);
	}

}
