package com.softrami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.softrami.model.Producto;
import com.softrami.service.ProductoService;

//@CrossOrigin("*")
//@RestController
@Configuration
public class ProductosController {
	@Autowired
	ProductoService productoService;

	/*
	 * @GetMapping(value = "productos") public ResponseEntity<Flux<Producto>>
	 * productos(){ return new ResponseEntity<>
	 * (productoService.catalogo(),HttpStatus.OK); }
	 * 
	 * @GetMapping(value="productos/{categoria}") public
	 * ResponseEntity<Flux<Producto>> productosCategoria(@PathVariable("categoria")
	 * String categoria){ return new
	 * ResponseEntity<>(productoService.productosCategoria(categoria),HttpStatus.OK)
	 * ; }
	 * 
	 * @GetMapping(value = "producto") public ResponseEntity<Mono<Producto>>
	 * productoCodigo(@RequestParam("cod") int cod) { return new ResponseEntity<>
	 * (productoService.productoCodigo(cod),HttpStatus.OK); }
	 * 
	 * @PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	 * public ResponseEntity<Mono<Void>> altaProducto(@RequestBody Producto
	 * producto) { producto.setNuevo(true); return new ResponseEntity<>
	 * (productoService.altaProducto(producto),HttpStatus.OK); }
	 * 
	 * @DeleteMapping(value = "eliminar") public Mono<ResponseEntity<Producto>>
	 * eliminarProducto(@RequestParam("cod") int cod) { return
	 * productoService.eliminarProducto(cod)//Mono<Producto> .map(p -> new
	 * ResponseEntity<>(p,HttpStatus.OK))//Mono<ResponseEntity<Producto>> --> Aqui
	 * transformamos para dar respuesta Ok, cuando el producto existe
	 * .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND))); //
	 * Pero cuando no existe dicho codigo de producto para eliminar, se ejecuta esta
	 * linea, con httpstatus NOT_FOUND }
	 * 
	 * @PutMapping(value = "actualizar") public Mono<ResponseEntity<Producto>>
	 * actualizarProducto(@RequestParam("cod") int cod, @RequestParam("precio")
	 * double precio) { return productoService.actualizarPrecio(cod,
	 * precio)//Mono<Producto> .map(p -> new
	 * ResponseEntity<>(p,HttpStatus.OK))//Mono<ResponseEntity<Producto>> --> Aqui
	 * transformamos para dar respuesta Ok, cuando el producto existe
	 * .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND))); //
	 * Pero cuando no existe dicho codigo de producto para actualizar, se ejecuta
	 * esta linea, con httpstatus NOT_FOUND }
	 */

	@Bean
	public RouterFunction<ServerResponse> respuestas() {
		return RouterFunctions.route(RequestPredicates.GET("productos"), req -> ServerResponse.ok() // BodyBuilder
				.body(productoService.catalogo(), Producto.class)// Mono<ServerResponse>
		)// RouterFunction<ServerResponse>
				.andRoute(RequestPredicates.GET("productos/{categoria}"), req -> ServerResponse.ok()// BodyBuilder
						.body(productoService.productosCategoria(req.pathVariable("categoria")), Producto.class))// RouterFunction<ServerResponse>
				.andRoute(RequestPredicates.GET("producto"), req -> ServerResponse.ok()// BodyBuilder
						.body(productoService.productoCodigo(req.queryParam("cod").map(s -> Integer.parseInt(s)).get()),
								Producto.class))// RouterFunction<ServerResponse>
				.andRoute(RequestPredicates.POST("alta"), req -> req.bodyToMono(Producto.class)// Mono<Producto>
						.flatMap(p -> {
							p.setNuevo(true);
							return productoService.altaProducto(p);
						})// Mono<Void>
						.flatMap(v -> ServerResponse.ok()// BodyBuilder
								.build()// Mono<ServerResponse>
						)// Mono<ServerResponse>
				)// RouterFunction<ServerResponse>
				.andRoute(RequestPredicates.DELETE("eliminar"),
						req -> productoService
								.eliminarProducto(req.queryParam("cod").map(s -> Integer.parseInt(s)).get())// Mono<Producto>
								.flatMap(p -> ServerResponse.ok()// BodyBuilder
										.bodyValue(p)// Mono<ServerResponse>
								)// Mono<ServerResponse>
								.switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)// BodyBuilder
										.build()// Mono<ServerResponse>
								)// Mono<ServerResponse>
				)// RouterFunction<ServerResponse>
				.andRoute(RequestPredicates.PUT("actualizar"),
						req -> productoService
								.actualizarPrecio(req.queryParam("cod").map(s -> Integer.parseInt(s)).get(),
										req.queryParam("precio").map(s -> Double.parseDouble(s)).get())// Mono<Producto>
								.flatMap(p -> ServerResponse.ok()// BodyBuilder
										.bodyValue(p)// Mono<ServerResponse>
								)// BodyBuilder
								.switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)// BodyBuilder
										.build()// Mono<ServerResponse>
								)// Mono<ServerResponse>
				);
	}
	
	@Bean
	CorsWebFilter corsFilter() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return new CorsWebFilter(source);
	}

}
