package com.bbva.simulador.domain;

import java.util.List;

public class Contenido {
	public List<Fila> data;
	public int recordsTotal;
	public int recordsFiltered;
	public String sEcho;
	
	public List<Fila> getData() {
		return data;
	}
	public void setData(List<Fila> data) {
		this.data = data;
	}
	

}
