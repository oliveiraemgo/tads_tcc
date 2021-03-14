package br.com.academia.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensalidade {
	
	private double valorMensal;
	private double valorTotal;
	private double valores;
	private double juros;
	private double desconto;
	private Date dataDoPagamento;
	
	public Mensalidade(){
		this.valorTotal = 0;
		this.juros = 0;
		this.desconto = 0;
	}

	public double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setValores(double valores) {
		this.valores = this.valores + valores;
	}
	
	public double getValores() {
		return valores;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	
	public double getValorMensal() {
		return valorMensal;
	}
	
	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}

	public Date getDataDoPagamento() {
		return dataDoPagamento;
	}
	
	public String getDataDoPagamentoFormatada(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(dataDoPagamento);
	}
	
	public void setDataDoPagamento(Date dataDoPagamento) {
		this.dataDoPagamento = dataDoPagamento;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
