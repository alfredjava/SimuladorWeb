package com.bbva.simulador.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bbva.simulador.domain.Fila;
import com.bbva.simulador.domain.Request;
import com.bbva.simulador.domain.Response;
@Component
public class ConsumerServiceImpl implements ConsumerService{

	@Override
	public List<Fila> obtenerSimulacion(Request request2) {
		List<Fila> lista=null;
		try {
		     RestTemplate restTemplate = new RestTemplate();
		     HttpEntity<Request> httpRequestRest = new HttpEntity<>(request2);
		     ResponseEntity<Response> response = restTemplate
		       .exchange("http://localhost:8080/simulador", HttpMethod.POST, httpRequestRest, Response.class);
		     lista=response.getBody().getData();
		} catch (Exception e) {
			// TODO: handle exception
		}

	     return lista;
	}

}
