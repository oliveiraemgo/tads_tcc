package br.com.academia.bean;

public class Modalidade {
	
	private int id;
	private String nome;
	private double valor;
	private String descricao;
	
	public Modalidade(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Modalidade [id=" + id + ", nome=" + nome + ", valor=" + valor + ", descricao=" + descricao + "]";
	}

}
