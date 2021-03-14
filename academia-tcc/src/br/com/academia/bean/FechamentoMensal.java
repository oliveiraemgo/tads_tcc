package br.com.academia.bean;

import java.util.List;

public class FechamentoMensal {
	
	private double totalPago;
	private double totalJuros;
	private double totalDescontos;
	private int mesDoFechamento;
	private int anoDoFechamento;
	
	private List<Mensalidade> mensalidades;
	
	public FechamentoMensal(){}

	public double getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
	}

	public double getTotalJuros() {
		return totalJuros;
	}

	public void setTotalJuros(double totalJuros) {
		this.totalJuros = totalJuros;
	}

	public double getTotalDescontos() {
		return totalDescontos;
	}

	public void setTotalDescontos(double totalDescontos) {
		this.totalDescontos = totalDescontos;
	}
	
	public int getMesDoFechamento() {
		return mesDoFechamento;
	}
	
	public void setMesDoFechamento(int mesDoFechamento) {
		this.mesDoFechamento = mesDoFechamento;
	}

	public List<Mensalidade> getMensalidades() {
		return mensalidades;
	}
	
	public void setMensalidades(List<Mensalidade> mensalidades) {
		this.mensalidades = mensalidades;
	}
	
	public int getAnoDoFechamento() {
		return anoDoFechamento;
	}
	
	public void setAnoDoFechamento(int anoDoFechamento) {
		this.anoDoFechamento = anoDoFechamento;
	}

	@Override
	public String toString() {
		return "FechamentoMensal [totalPago=" + totalPago + ", totalJuros=" + totalJuros + ", totalDescontos="
				+ totalDescontos + ", mesDoFechamento=" + mesDoFechamento + ", anoDoFechamento=" + anoDoFechamento
				+ ", mensalidades=" + mensalidades + "]";
	}
	
}
