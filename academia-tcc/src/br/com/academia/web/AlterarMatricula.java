package br.com.academia.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.academia.bean.Aluno;
import br.com.academia.bean.Contato;
import br.com.academia.bean.Endereco;
import br.com.academia.bean.Modalidade;
import br.com.academia.processos.ProcessoAluno;
import br.com.academia.processos.ProcessoModalidade;

@WebServlet(urlPatterns="/alterarMatricula")
public class AlterarMatricula extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		//recuperando o aluno da sessão
		Aluno alunoSelecionado = new Aluno();
		alunoSelecionado = (Aluno) session.getAttribute("alunoSelecionado");
		
		//obtendo atributos com os dados do formulário
		alunoSelecionado = obterAluno(req, resp, alunoSelecionado);
		
		switch (req.getParameter("acao")) {
		case "Alterar":
			
			new ProcessoAluno().atualizarMatricula(alunoSelecionado);
			
			alunoSelecionado.setModalidades(new ArrayList<Modalidade>());
			
			//salvando o aluno atualizado na sessão
			session.setAttribute("aluno", alunoSelecionado);
			
			//recuperando modalidades disponíveis e salvando na sessão
			session.setAttribute("modalidades", new ProcessoModalidade().recuperarModalidades());
			
			req.getRequestDispatcher("/WEB-INF/paginas/modalidade.jsp").forward(req, resp);
			//resp.sendRedirect("modalidade.jsp");
			
			break;
			
		default:
			break;
		}
		
	}
	
	private Aluno obterAluno(HttpServletRequest req, HttpServletResponse resp, Aluno aluno) 
			throws ServletException, IOException{
		
		
		aluno.setNome(req.getParameter("nome"));
		aluno.setCpf(req.getParameter("cpf"));
		
		String data = req.getParameter("dt_nascimento");
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date d = df.parse(data);
			aluno.setDt_nascimento(d);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		aluno.setContato(new Contato());
		aluno.getContato().setTelefone(req.getParameter("telefone"));
		aluno.getContato().setCelular(req.getParameter("celular"));
		aluno.getContato().setEmail(req.getParameter("email"));
		
		aluno.setEndereco(new Endereco());
		aluno.getEndereco().setLogradouro(req.getParameter("logradouro"));
		aluno.getEndereco().setNumero(Integer.parseInt(req.getParameter("numero")));
		aluno.getEndereco().setComplemento(req.getParameter("complemento"));
		aluno.getEndereco().setBairro(req.getParameter("bairro"));
		aluno.getEndereco().setCidade(req.getParameter("cidade"));
		aluno.getEndereco().setEstado(req.getParameter("estado"));
		aluno.getEndereco().setCep(req.getParameter("cep"));
		
		return aluno;
		
	}

}
