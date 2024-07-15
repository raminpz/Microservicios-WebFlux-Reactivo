package com.softrami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.softrami.model.Envio;
import com.softrami.service.EnviosService;

import reactor.core.publisher.Flux;

@RestController
public class EnviosController {
	@Autowired
	EnviosService enviosService;
	
	public ResponseEntity<Flux<Envio>> enviosPendientes(){
		return new ResponseEntity<>(enviosService.pendientes(), HttpStatus.OK);
	}

}
