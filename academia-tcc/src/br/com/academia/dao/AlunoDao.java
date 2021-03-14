package br.com.academia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.bean.Aluno;
import br.com.academia.bean.Contato;
import br.com.academia.bean.Endereco;

public class AlunoDao {
	
	private Connection con;
	
	public AlunoDao(Connection con) throws SQLException{
		this.con = con;
		con.setAutoCommit(false);
	}
	
	public List<Aluno> listarAlunos(){
		List<Aluno> alunos = new ArrayList<>();
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SELECT.getQuery())){
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					Aluno aluno = new Aluno();
					aluno.setId(r.getInt("id_aluno"));
					aluno.setNome(r.getString("nome"));
					aluno.setCpf(r.getString("cpf"));
					aluno.setDt_nascimento(r.getDate("dt_nascimento"));
					alunos.add(aluno);
				}
				System.out.println("Busca realizada com sucesso");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar alunos: " + e.getMessage());
		}
		
		return alunos;
	}
	
	public void novoAluno(Aluno aluno) throws SQLException{
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.INSERT_ALUNO.getQuery(), 
				PreparedStatement.RETURN_GENERATED_KEYS)){
			// RETURN_GENERATED_KEYS, comando utilizado para recuperar a chave primária da tabela
			
			p.setString(1, aluno.getNome());
			p.setString(2, aluno.getCpf());
			p.setDate(3, new Date(aluno.getDt_nascimento().getTime()));
			
			p.execute();
			
			try(ResultSet r = p.getGeneratedKeys()){
				if(r.next()){
					aluno.setId(r.getInt(1));
				}
			}
			
			System.out.println("Aluno cadastrado com sucesso");
			
			novoContato(aluno);
			novoEndereco(aluno);
			
			con.commit();
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
			con.rollback(); //a transação será desfeita caso ocorra algum erro
		}
	}
	
	private void novoEndereco(Aluno aluno) throws SQLException{
		try (PreparedStatement p = con.prepareStatement(ComandoSql.INSERT_ENDERECO_DO_ALUNO.getQuery())){
			p.setString(1, aluno.getEndereco().getLogradouro());
			p.setInt(2, aluno.getEndereco().getNumero());
			p.setString(3, aluno.getEndereco().getComplemento());
			p.setString(4, aluno.getEndereco().getBairro());
			p.setString(5, aluno.getEndereco().getCidade());
			p.setString(6, aluno.getEndereco().getEstado());
			p.setString(7, aluno.getEndereco().getCep());
			p.setInt(8, aluno.getId());
			
			p.execute();
			
			System.out.println("Endereço cadastrado com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar endereço do aluno: " + e.getMessage());
			con.rollback();
		}
	}
	
	private void novoContato(Aluno aluno) throws SQLException{
		try(PreparedStatement p = con.prepareStatement(ComandoSql.INSERT_CONTATO_DO_ALUNO.getQuery())) {
			p.setString(1, aluno.getContato().getTelefone());
			p.setString(2, aluno.getContato().getCelular());
			p.setString(3, aluno.getContato().getEmail());
			p.setInt(4, aluno.getId());
			
			p.execute();
			
			System.out.println("Contato cadastrado com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar contato do aluno: " + e.getMessage());
			con.rollback();
		}
	}
	
	public Aluno selecionarAluno(Aluno aluno){
		Endereco endereco = new Endereco();
		Contato contato = new Contato();
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SELECT_ALUNO.getQuery())){
			p.setInt(1, aluno.getId());
			
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					aluno.setNome(r.getString("nome"));
					aluno.setCpf(r.getString("cpf"));
					aluno.setDt_nascimento(r.getDate("dt_nascimento"));
					
					endereco.setLogradouro(r.getString("logradouro"));
					endereco.setNumero(r.getInt("numero"));
					endereco.setComplemento(r.getString("complemento"));
					endereco.setBairro(r.getString("bairro"));
					endereco.setCidade(r.getString("cidade"));
					endereco.setEstado(r.getString("estado"));
					endereco.setCep(r.getString("cep"));
					
					contato.setTelefone(r.getString("telefone"));
					contato.setCelular(r.getString("celular"));
					contato.setEmail(r.getString("email"));
				}
				aluno.setEndereco(endereco);
				aluno.setContato(contato);
				aluno.setModalidades(new InscricaoDao(con).obterModalidadesDoAluno(aluno));
			}
			
			System.out.println("Aluno selecionado com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao selecionar aluno: " + e.getMessage());
		}
		return aluno;
	}
	
	public void deletarAluno(Aluno aluno) throws SQLException{
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.DELETE_ALUNO.getQuery())){
			p.setInt(1, aluno.getId());
			
			new InscricaoDao(con).deletarInscricao(aluno);
			new PagamentoDao(con).desvincularPagamento(aluno);
			
			p.execute();
			
			con.commit();
			
			System.out.println("Registro do aluno deletado com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar o registro do aluno: " + e.getMessage());
			con.rollback();
		}
	}
	
	public void atualizarAluno(Aluno aluno) throws SQLException{
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.UPDATE_ALUNO.getQuery())){
			p.setString(1, aluno.getNome());
			p.setString(2, aluno.getCpf());
			p.setDate(3, new Date(aluno.getDt_nascimento().getTime()));
			
			p.setString(4, aluno.getEndereco().getLogradouro());
			p.setInt(5, aluno.getEndereco().getNumero());
			p.setString(6, aluno.getEndereco().getComplemento());
			p.setString(7, aluno.getEndereco().getBairro());
			p.setString(8, aluno.getEndereco().getCidade());
			p.setString(9, aluno.getEndereco().getEstado());
			p.setString(10, aluno.getEndereco().getCep());
			
			p.setString(11, aluno.getContato().getTelefone());
			p.setString(12, aluno.getContato().getCelular());
			p.setString(13, aluno.getContato().getEmail());
			
			p.setInt(14, aluno.getId());
			p.execute();
			con.commit();
			System.out.println("Registro do aluno atualizado com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar o registro do aluno: " + e.getMessage());
			con.rollback();
		}
		
	}
	
	public List<Aluno> selecionarAlunosPeloNome(Aluno aluno){
		
		List<Aluno> alunos = new ArrayList<>();
		
		try (PreparedStatement p = con.prepareStatement(ComandoSql.SELECT_ALUNO_PELO_NOME.getQuery())){
			p.setString(1, "%"+aluno.getNome()+"%");
			
			try(ResultSet r = p.executeQuery()){
				while(r.next()){
					Aluno a = new Aluno();
					a.setId(r.getInt("id_aluno"));
					a.setNome(r.getString("nome"));
					a.setCpf(r.getString("cpf"));
					a.setDt_nascimento(r.getDate("dt_nascimento"));
					alunos.add(a);
				}
				System.out.println("Aluno selecionado com sucesso");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao selecionar alunos pelo nome: " + e.getMessage());
		}
		return alunos;
	}
	
	private enum ComandoSql{
		
		SELECT("select * from aluno"),
		INSERT_ALUNO("insert into aluno (nome,cpf,dt_nascimento) values(?,?,?)"),
		INSERT_CONTATO_DO_ALUNO("update aluno set telefone = ?, celular = ?, email = ? where id_aluno = ?"),
		INSERT_ENDERECO_DO_ALUNO("update aluno set logradouro = ?, numero = ?, complemento = ?, bairro = ?, "
				+ "cidade = ?, estado = ?, cep = ? where id_aluno = ?"),
		SELECT_ALUNO("select * from aluno where id_aluno = ?"),
		DELETE_ALUNO("delete from aluno where id_aluno = ?"),
		UPDATE_ALUNO("update aluno set nome = ?, cpf = ?, dt_nascimento = ?, logradouro = ?, "
				+ "numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, cep = ?,"
				+ "telefone = ?, celular = ?, email = ? where id_aluno = ?"),
		SELECT_ALUNO_PELO_NOME("select * from aluno where nome like ?");
		
		private String query;
		
		ComandoSql(String query){
			this.query = query;
		}
		
		private String getQuery(){
			return this.query;
		}
	}

}
