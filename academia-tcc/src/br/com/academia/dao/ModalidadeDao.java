package br.com.academia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.bean.Modalidade;

public class ModalidadeDao {
	
	private Connection con;
	
	public ModalidadeDao(Connection con) throws SQLException{
		this.con = con;
		con.setAutoCommit(false);
	}
	
	public List<Modalidade> listarModalidades(){
		List<Modalidade> modalidades = new ArrayList<>();
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SELECT.getQuery())){
			
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					Modalidade modalidade = new Modalidade();
					modalidade.setId(r.getInt("id_modalidade"));
					modalidade.setNome(r.getString("nome"));
					modalidade.setValor(r.getDouble("valor"));
					modalidades.add(modalidade);
				}
				System.out.println("Modalidades listadas com sucesso");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar modalidades: " + e.getMessage());
		}
		return modalidades;
		
	}
	
	public Modalidade selecionarModalidade(Modalidade modalidade){
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SELECT_PELO_ID.getQuery())){
			p.setInt(1, modalidade.getId());
			
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					modalidade.setNome(r.getString("nome"));
					modalidade.setValor(r.getDouble("valor"));
				}
			}
			System.out.println("Modalidade selecionada pelo id com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao selecionar modalidade pelo id: " + e.getMessage());
		}
		return modalidade;
	}
	
	private enum ComandoSql{
		
		SELECT("select * from modalidade"),
		SELECT_PELO_ID("select * from modalidade where id_modalidade = ?");
		
		private String query;
		
		ComandoSql(String query){
			this.query = query;
		}
		
		private String getQuery(){
			return this.query;
		}
	}

}
