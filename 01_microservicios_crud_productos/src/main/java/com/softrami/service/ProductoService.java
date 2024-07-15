package com.softrami.service;

import java.util.List;

import com.softrami.model.Producto;

public interface ProductoService {
	List<Producto> catalogo();
	List<Producto> productosCategoria(String categoria);
	Producto productoCodigo(int cod);
	void altaProducto(Producto producto);
	Producto eliminarProducto(int cod);
	Producto actualizarPrecio(int cod, double precio);

}
