package br.com.academia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.academia.bean.Aluno;
import br.com.academia.bean.FechamentoMensal;
import br.com.academia.bean.Mensalidade;

public class PagamentoDao {
	
	private Connection con;
	
	public PagamentoDao(Connection con) throws SQLException{
		this.con = con;
		con.setAutoCommit(false);
	}
	
	public void inserirPagamento(Aluno aluno) throws SQLException{
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.INSERT.getQuery())){
			p.setTimestamp(1, new Timestamp(new Date().getTime()));
			p.setDouble(2, aluno.getMensalidade().getValorTotal());
			p.setDouble(3, aluno.getMensalidade().getJuros());
			p.setDouble(4, aluno.getMensalidade().getDesconto());
			p.setInt(5, aluno.getId());
			
			p.execute();
			
			con.commit();
			
			System.out.println("Pagamento realizado com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao realizar pagamento: " + e.getMessage());
			con.rollback();
		}
		
	}
	
	public Mensalidade recuperarPagamento(Aluno aluno){
		
		GregorianCalendar calendar = new GregorianCalendar();
		Mensalidade mensalidade = new Mensalidade();
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SELECT.getQuery())){
			p.setInt(1, calendar.get(Calendar.MONTH));
			p.setInt(2, aluno.getId());
			
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					mensalidade.setValorTotal(r.getDouble("valor_total"));
					mensalidade.setDesconto(r.getDouble("desconto"));
					mensalidade.setJuros(r.getDouble("juros"));
					mensalidade.setDataDoPagamento(r.getDate("dt_pagamento"));
				}
			}
			
			System.out.println("Pagamento recuperado com sucesso");
			
		} catch (Exception e) {
			System.out.println("Erro ao recuperar pagamento: " + e.getMessage());
		}
		return mensalidade;
		
	}
	
	public void desvincularPagamento(Aluno aluno) throws SQLException{
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.UPDATE.getQuery())){
			p.setInt(1, aluno.getId());
			p.execute();
			
			con.commit();
			
			System.out.println("Pagamento desvinculado ao aluno com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao desvincular pagamento: " + e.getMessage());
			con.rollback();
		}
		
	}
	
	public double obterSomaDosPagamentos(FechamentoMensal fechamentoMensal){
		double soma = 0;
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SOMA_PAGAMENTOS_MES_ATUAL.getQuery())){
			p.setInt(1, fechamentoMensal.getMesDoFechamento());
			
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					soma = r.getDouble("sum");
				}
			}
			
			System.out.println("Soma realizada com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao obter soma dos pagamentos: " + e.getMessage());
		}
		return soma;
		
	}
	
	public double obterSomaDosJuros(FechamentoMensal fechamentoMensal){
		
		double juros = 0;
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SOMA_JUROS_MES_ATUAL.getQuery())){
			p.setInt(1, fechamentoMensal.getMesDoFechamento());
			
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					juros = r.getDouble("sum");
				}
			}
			System.out.println("Soma realizada com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao obter soma dos juros: " + e.getMessage());
		}
		return juros;
		
	}

	public double obterSomaDosDescontos(FechamentoMensal fechamentoMensal){
		
		double descontos = 0;
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SOMA_DESCONTOS_MES_ATUAL.getQuery())){
			p.setInt(1, fechamentoMensal.getMesDoFechamento());
			
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					descontos = r.getDouble("sum");
				}
			}
			System.out.println("Soma realizada com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao obter soma dos descontos: " + e.getMessage());
		}
		return descontos;
		
	}
	
	public List<Mensalidade> listarMensalidades(FechamentoMensal fechamentoMensal){
		
		List<Mensalidade> mensalidades = new ArrayList<>();
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SELECT_PAGAMENTOS_MES_ATUAL.getQuery())){
			p.setInt(1, fechamentoMensal.getMesDoFechamento());
			p.setInt(2, fechamentoMensal.getAnoDoFechamento());
			
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					Mensalidade mensalidade = new Mensalidade();
					mensalidade.setDataDoPagamento(r.getDate("dt_pagamento"));
					mensalidade.setDesconto(r.getDouble("desconto"));
					mensalidade.setJuros(r.getDouble("juros"));
					mensalidade.setValorTotal(r.getDouble("valor_total"));
					
					mensalidades.add(mensalidade);
				}
			}
			System.out.println("Mensalidades listadas com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar mensalidades: " + e.getMessage());
		}
		return mensalidades;
		
	}
	
	private enum ComandoSql{
		
		INSERT("insert into pagamento (dt_pagamento, valor_total, juros, desconto, id_aluno) "
				+ "values (?,?,?,?,?)"),
		SELECT("select * from pagamento where extract (month from dt_pagamento) = ?+1 "
				+ "and id_aluno = ?"),
		UPDATE("update pagamento set id_aluno = null where id_aluno = ?"),
		SELECT_PAGAMENTOS_MES_ATUAL("select * from pagamento where extract (month from dt_pagamento) = ? "
				+ "and extract (year from dt_pagamento) = ?"),
		SOMA_PAGAMENTOS_MES_ATUAL("select sum(pagamento.valor_total) from pagamento "
				+ "where extract (month from dt_pagamento) = ?"),
		SOMA_JUROS_MES_ATUAL("select sum(pagamento.juros) from pagamento "
				+ "where extract (month from dt_pagamento) = ?"),
		SOMA_DESCONTOS_MES_ATUAL("select sum(pagamento.desconto) from pagamento "
				+ "where extract (month from dt_pagamento) = ?");
		
		private String query;
		
		ComandoSql(String query){
			this.query = query;
		}
		
		private String getQuery(){
			return this.query;
		}
		
	}

}
