package com.bbva.simulador.service;

import java.util.List;

import com.bbva.simulador.domain.Fila;
import com.bbva.simulador.domain.Request;

public interface ConsumerService {
	public List<Fila> obtenerSimulacion( Request request2);
}
