package br.com.academia.processos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.bean.Modalidade;
import br.com.academia.dao.ModalidadeDao;
import br.com.academia.util.ConexaoFB;

public class ProcessoModalidade {
	
	public ProcessoModalidade(){}
	
	public List<Modalidade> recuperarModalidades(){
		List<Modalidade> modalidades = new ArrayList<>();
		try (Connection con = new ConexaoFB().getConexao()){
			
			modalidades = new ModalidadeDao(con).listarModalidades();
			
		} catch (Exception e) {
			System.out.println("Erro ao recuperar modalidades: " + e.getMessage());
		}
		return modalidades;
	}
	
	public Modalidade obterModalidade(Modalidade modalidade){
		try (Connection con = new ConexaoFB().getConexao()){
			modalidade = new ModalidadeDao(con).selecionarModalidade(modalidade);
		} catch (Exception e) {
			System.out.println("Erro ao obter modalidade: " + e.getMessage());
		}
		return modalidade;
	}

}
