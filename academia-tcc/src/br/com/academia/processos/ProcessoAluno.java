package br.com.academia.processos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.bean.Aluno;
import br.com.academia.dao.AlunoDao;
import br.com.academia.dao.PagamentoDao;
import br.com.academia.util.ConexaoFB;

public class ProcessoAluno {
	
	public List<Aluno> listarMatriculas(){
		List<Aluno> alunos = new ArrayList<>();
		try (Connection con = new ConexaoFB().getConexao()){
			
			alunos = new AlunoDao(con).listarAlunos();
			
		} catch (Exception e) {
			System.out.println("Erro ao listar matrículas: " + e.getMessage());
		}
		return alunos;
	}
	
	public void novaMatricula(Aluno aluno){
		
		try (Connection con = new ConexaoFB().getConexao()){
			
			new AlunoDao(con).novoAluno(aluno);
			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Erro ao matricular aluno: " + e.getMessage());
		}
		
	}
	
	public Aluno obterMatricula(Aluno aluno){
		
		try (Connection con = new ConexaoFB().getConexao()){
			
			aluno = new AlunoDao(con).selecionarAluno(aluno);
			
		} catch (Exception e) {
			System.out.println("Erro ao obter matrícula: " + e .getMessage());
		}
		return aluno;
	}

	public void deletarMatricula(Aluno aluno){
		
		try (Connection con = new ConexaoFB().getConexao()){
			
			new AlunoDao(con).deletarAluno(aluno);
			
		} catch (Exception e) {
			System.out.println("Erro ao deletar matrícula: " + e.getMessage());
		}
		
	}

	public void atualizarMatricula(Aluno aluno){
		
		try(Connection con = new ConexaoFB().getConexao()) {
			
			new AlunoDao(con).atualizarAluno(aluno);
			
		} catch (Exception e) {
			System.out.println("Erro ao atualizar matrícula: " + e.getMessage());
		}
		
	}

	public List<Aluno> listarMatriculasPendentes(){
		
		List<Aluno> alunos = new ArrayList<>();
		List<Aluno> alunosPendentes = new ArrayList<>();
		
		try (Connection con = new ConexaoFB().getConexao()){
			
			alunos = new AlunoDao(con).listarAlunos();
			
			PagamentoDao pagamentoDao = new PagamentoDao(con);
			
			for(int i = 0 ; i<alunos.size(); i++){
				alunos.get(i).setMensalidade(pagamentoDao.recuperarPagamento(alunos.get(i)));
			}
			
			for(int i = 0; i<alunos.size(); i++){
				if(alunos.get(i).getMensalidade().getValorTotal() == 0){
					alunosPendentes.add(alunos.get(i));
				}
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar matrículas pendentes: " + e.getMessage());
		}
		
		return alunosPendentes;
		
	}

	public List<Aluno> listarMatriculasPeloNome(Aluno aluno){
		List<Aluno> alunos = new ArrayList<>();
		
		try (Connection con = new ConexaoFB().getConexao()){
			
			alunos = new AlunoDao(con).selecionarAlunosPeloNome(aluno);
			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Erro ao listar matrículas pelo nome do aluno: " + e.getMessage());
		}
		
		return alunos;
		
	}
}

