package br.com.academia.processos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.academia.bean.Aluno;
import br.com.academia.bean.FechamentoMensal;
import br.com.academia.bean.Mensalidade;
import br.com.academia.bean.Modalidade;
import br.com.academia.dao.PagamentoDao;
import br.com.academia.util.ConexaoFB;

public class Pagamento {
	
	public Mensalidade gerarMensalidade(Aluno aluno){
		
		Mensalidade mensalidade = new Mensalidade();
		
		for(Modalidade m: aluno.getModalidades()){
			mensalidade.setValores(m.getValor());
		}
		
		mensalidade.setValorMensal(mensalidade.getValores());
		
		GregorianCalendar calendar = new GregorianCalendar();
		
		if(calendar.get(Calendar.DAY_OF_MONTH) > 19){
			mensalidade.setJuros(mensalidade.getValorMensal() * 0.08);
			mensalidade.setValorTotal(mensalidade.getValorMensal() + mensalidade.getJuros());
		}else{
			mensalidade.setDesconto(mensalidade.getValorMensal() * 0.08);
			mensalidade.setValorTotal(mensalidade.getValorMensal() - mensalidade.getDesconto());
		}
		
		return mensalidade;
		
	}
	
	public void pagarMensalidade(Aluno aluno){
		
		try (Connection con = new ConexaoFB().getConexao()){
			
			new PagamentoDao(con).inserirPagamento(aluno);
			
		} catch (Exception e) {
			System.out.println("Erro ao pagar mensalidade: " + e.getMessage());
		}
		
	}

	public Mensalidade recuperarPagamento(Aluno aluno){
		
		Mensalidade mensalidade = new Mensalidade();
		
		try (Connection con = new ConexaoFB().getConexao()){
			
			mensalidade = new PagamentoDao(con).recuperarPagamento(aluno);
			
		} catch (Exception e) {
			System.out.println("Erro ao recuperara pagamento: " + e.getMessage());
		}
		return mensalidade;
		
	}

	public FechamentoMensal obterFechamentoMensal(FechamentoMensal fechamentoMensal){
		
		try (Connection con = new ConexaoFB().getConexao()){
			PagamentoDao dao = new PagamentoDao(con);
			
			fechamentoMensal.setTotalPago(dao.obterSomaDosPagamentos(fechamentoMensal));
			fechamentoMensal.setTotalJuros(dao.obterSomaDosJuros(fechamentoMensal));
			fechamentoMensal.setTotalDescontos(dao.obterSomaDosDescontos(fechamentoMensal));
			
		} catch (Exception e) {
			System.out.println("Erro ao obter fechamento mensal: " + e.getMessage());
		}
		
		return fechamentoMensal;
	}

	public List<Mensalidade> obterMensalidades(FechamentoMensal fechamentoMensal){
		
		List<Mensalidade> mensalidades = new ArrayList<>();
		try (Connection con = new ConexaoFB().getConexao()){
			
			mensalidades = new PagamentoDao(con).listarMensalidades(fechamentoMensal);
			
		} catch (Exception e) {
			System.out.println("Erro ao obter mensalidades: " + e.getMessage());
		}
		return mensalidades;
		
	}
}
