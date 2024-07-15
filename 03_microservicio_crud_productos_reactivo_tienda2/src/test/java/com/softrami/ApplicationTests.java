package com.softrami;

import java.util.Arrays;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.softrami.model.Producto;
import com.softrami.service.ProductoService;

import reactor.test.StepVerifier;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ApplicationTests {
	@Autowired
	ProductoService service;
	

	@Test
	@Order(1)
	void testProductosCategoria() {
		StepVerifier.create(service.productosCategoria("Alimentación"))
        .expectNextSequence(Arrays.asList(
            new Producto(100, "Azucar", "Alimentación", 1.10, 20),
            new Producto(101, "Leche", "Alimentación", 1.20, 15),
            new Producto(105, "Huevos", "Alimentación", 2.20, 30)
        ))
        .verifyComplete();
	}
	
	@Test
	@Order(2)
	void testEliminarProducto() {
		StepVerifier.create(service.eliminarProducto(103))
		.expectNextMatches(p->p.getNombre().equals("Mesa"))
		.verifyComplete();
	}
	
	@Test
	@Order(3)
	void testAltaProducto() {
		Producto pr = new Producto(250,"ptest","cat1", 10, 2);
		StepVerifier.create(service.altaProducto(pr))
		.expectComplete()
		.verify();
	}
	
	@Test
	@Order(4)
	void testCatalogo() {
		StepVerifier.create(service.catalogo())
		.expectNextCount(8)
		.verifyComplete();
	}

}
