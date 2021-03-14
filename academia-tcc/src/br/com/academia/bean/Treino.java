package br.com.academia.bean;

import br.com.academia.util.Semana;

public class Treino {
	
	private Integer id;
	private String nome;
	private Integer serie;
	private String repeticao;
	private Semana diaSemana;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getSerie() {
		return serie;
	}
	public void setSerie(Integer serie) {
		this.serie = serie;
	}
	public String getRepeticao() {
		return repeticao;
	}
	public void setRepeticao(String repeticao) {
		this.repeticao = repeticao;
	}
	public Semana getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(Semana diaSemana) {
		this.diaSemana = diaSemana;
	}


}
