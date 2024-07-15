package com.softrami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.softrami.model.Pedido;
import com.softrami.service.PedidoService;

@RestController
public class PedidosController {
	@Autowired
	PedidoService pedidoService;
	
	@PostMapping(value="alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> nuevoPedido(@RequestBody Pedido pedido){
		try {
			pedidoService.registrarPedido(pedido);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
