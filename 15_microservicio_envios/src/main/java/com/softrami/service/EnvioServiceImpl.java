package com.softrami.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.softrami.model.Envio;
import com.softrami.repository.EnviosRepository;

import reactor.core.publisher.Flux;

@Service
public class EnvioServiceImpl implements EnviosService {
	@Autowired
	EnviosRepository enviosRepository;

	@Override
	public Flux<Envio> pendientes() {
		return enviosRepository.findByPendientes();
	}

	@KafkaListener(topics = "pedidosTopic", groupId = "myGroup2")
	public void gestionEnvios(Envio envio) {
		envio.setFecha(LocalDateTime.now());
		envio.setEstado("pendiente");
		enviosRepository.save(envio).subscribe();
	}

}
