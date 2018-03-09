package com.bbva.simulador.domain;

public class Fila {
	private int nroCouta;
	private String fecha;
	private double interes;
	private double amortizacion;
	private double totalAmortizado;
	private double cuotaPago;
	private double capitalPendiente;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getInteres() {
		return interes;
	}
	public void setInteres(double interes) {
		this.interes = interes;
	}
	public double getAmortizacion() {
		return amortizacion;
	}
	public void setAmortizacion(double amortizacion) {
		this.amortizacion = amortizacion;
	}
	public double getCuotaPago() {
		return cuotaPago;
	}
	public void setCuotaPago(double cuotaPago) {
		this.cuotaPago = cuotaPago;
	}
	public double getCapitalPendiente() {
		return capitalPendiente;
	}
	public void setCapitalPendiente(double capitalPendiente) {
		this.capitalPendiente = capitalPendiente;
	}
	public int getNroCouta() {
		return nroCouta;
	}
	public void setNroCouta(int nroCouta) {
		this.nroCouta = nroCouta;
	}
	public double getTotalAmortizado() {
		return totalAmortizado;
	}
	public void setTotalAmortizado(double totalAmortizado) {
		this.totalAmortizado = totalAmortizado;
	}

	
}
