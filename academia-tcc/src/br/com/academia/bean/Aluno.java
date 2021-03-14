package br.com.academia.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aluno {
	
	private int id;
	private String nome;
	private String cpf;
	private Date dt_nascimento;
	
	private Contato contato;
	private Endereco endereco;
	private Mensalidade mensalidade;
	private List<Modalidade> modalidades;
	
	
	public Aluno(){
		modalidades = new ArrayList<>();
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDt_nascimento() {
		return dt_nascimento;
	}
	
	public String getDataNascimentoFormatada(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(dt_nascimento);
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Modalidade> getModalidades() {
		return modalidades;
	}

	public void setModalidades(List<Modalidade> modalidades) {
		this.modalidades = modalidades;
	}
	
	public Mensalidade getMensalidade() {
		return mensalidade;
	}
	
	public void setMensalidade(Mensalidade mensalidade) {
		this.mensalidade = mensalidade;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dt_nascimento=" + dt_nascimento + ", contato="
				+ contato + ", endereco=" + endereco + ", mensalidade=" + mensalidade + ", modalidades=" + modalidades
				+ "]";
	}
	
}
