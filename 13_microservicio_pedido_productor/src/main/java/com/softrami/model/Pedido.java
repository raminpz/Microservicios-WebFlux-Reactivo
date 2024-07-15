package com.softrami.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {
	private int codProducto;
	private String nombre;
	private int inidades;
	private String direccion;	

}
