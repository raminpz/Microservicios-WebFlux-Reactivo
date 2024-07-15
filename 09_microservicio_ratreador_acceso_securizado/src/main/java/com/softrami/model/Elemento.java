package com.softrami.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Elemento {
	private String nombre;
	private String categoria;
	private double precioUnitario;
	private String tienda;

}
