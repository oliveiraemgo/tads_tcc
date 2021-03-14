package br.com.academia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.bean.Aluno;
import br.com.academia.bean.Modalidade;

public class InscricaoDao {
	
	private Connection con;
	
	public InscricaoDao(Connection con) throws SQLException{
		this.con = con;
		con.setAutoCommit(false);
	}
	
	public void inscrever(Aluno aluno) throws SQLException{
		
		for(int i = 0; i < aluno.getModalidades().size(); i++){
			
			try (PreparedStatement p = con.prepareStatement(ComandoSql.INSERT.getQuery())){
				
				p.setInt(1, aluno.getId());
				p.setInt(2, aluno.getModalidades().get(i).getId());
				
				p.execute();
				
				con.commit();
				
				System.out.println("Inscricao realizada com sucesso");
				
			} catch (SQLException e) {
				System.out.println("Erro ao realizar inscrição: " + e.getMessage());
				con.rollback();
			}
			
		}
		
	}
	
	public List<Modalidade> obterModalidadesDoAluno(Aluno aluno){
		List<Modalidade> modalidades = new ArrayList<>();
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SELECT_MODALIDADE_DO_ALUNO.getQuery())){
			p.setInt(1, aluno.getId());
			
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					Modalidade modalidade = new Modalidade();
					modalidade.setId(r.getInt("id_modalidade"));
					modalidade.setNome(r.getString("nome"));
					modalidade.setDescricao(r.getString("descricao"));
					modalidade.setValor(r.getDouble("valor"));
					modalidades.add(modalidade);
				}
			}
			
			System.out.println("Modalidades selecionadas com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao obter modalidades do aluno" + e.getMessage());
		}
		return modalidades;
	}
	
	public void deletarInscricao(Aluno aluno) throws SQLException{
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.DELETE_INSCRICAO.getQuery())){
			p.setInt(1, aluno.getId());
			p.execute();
			
			con.commit();
			
			System.out.println("Inscrição deletada com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar inscricao: " + e.getMessage());
			con.rollback();
		}
	}
	
	private enum ComandoSql{
		
		INSERT("insert into inscricao values (?,?)"),
		SELECT_MODALIDADE_DO_ALUNO("select modalidade.id_modalidade, modalidade.nome, modalidade.valor, "
				+ "modalidade.descricao from aluno, modalidade, inscricao "
				+ "where aluno.id_aluno = inscricao.id_aluno and modalidade.id_modalidade = inscricao.id_modalidade "
				+ "and aluno.id_aluno = ?"),
		DELETE_INSCRICAO("delete from inscricao where id_aluno = ?");
		
		private String query;
		
		ComandoSql(String query){
			this.query = query;
		}
		
		private String getQuery(){
			return query;
		}
	}

}
