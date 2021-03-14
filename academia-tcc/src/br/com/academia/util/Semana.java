package br.com.academia.util;

public enum Semana {
	
	SEGUNDA_FEIRA("SEGUNDA_FEIRA"),
	TERCA_FEIRA("TERCA_FEIRA"),
	QUARTA_FEIRA("QUARTA_FEIRA"),
	QUINTA_FEIRA("QUINTA_FEIRA"),
	SEXTA_FEIRA("SEXTA_FEIRA"),
	SABADO("SABADO");
	
	private String dia;
	
	Semana(String dia){
		this.dia = dia;
	}
	
	public String getDia(){
		return this.dia;
	}

}
