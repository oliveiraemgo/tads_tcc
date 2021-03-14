package br.com.academia.processos;

import java.sql.Connection;

import br.com.academia.bean.Aluno;
import br.com.academia.dao.InscricaoDao;
import br.com.academia.util.ConexaoFB;

public class Inscricao {
	
	public Inscricao(){}
	
	public void inscreverAlunoNaModalidade(Aluno aluno){
		
		try (Connection con = new ConexaoFB().getConexao()){
			
			new InscricaoDao(con).inscrever(aluno);
			
		} catch (Exception e) {
			System.out.println("Erro ao inscrever aluno: " + e.getMessage());
		}
		
	}
	
	public void deletarInscricao(Aluno aluno){
		
		try (Connection con = new ConexaoFB().getConexao()){
			
			new InscricaoDao(con).deletarInscricao(aluno);
			
		} catch (Exception e) {
			System.out.println("Erro ao deletar inscricao: " + e.getMessage());
		}
		
	}

}
