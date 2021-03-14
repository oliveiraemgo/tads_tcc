package br.com.academia.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.academia.bean.Aluno;
import br.com.academia.bean.Contato;
import br.com.academia.bean.Endereco;
import br.com.academia.bean.Mensalidade;
import br.com.academia.bean.Modalidade;
import br.com.academia.processos.ProcessoAluno;
import br.com.academia.processos.ProcessoModalidade;


@WebServlet(urlPatterns="/novaMatricula")
public class NovaMatricula extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		Aluno aluno = obterAluno(req, resp);
		
		ProcessoModalidade pModalidade = new ProcessoModalidade();
		List<Modalidade> modalidades = new ArrayList<>();
		modalidades = pModalidade.recuperarModalidades();
		
		Mensalidade mensalidade = new Mensalidade();
		aluno.setMensalidade(mensalidade);
		
		HttpSession session = req.getSession();
		session.setAttribute("aluno", aluno);
		session.setAttribute("modalidades", modalidades);
		
		req.getRequestDispatcher("/WEB-INF/paginas/modalidade.jsp").forward(req, resp);
		
	}
	
	
	private Aluno obterAluno(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		Aluno aluno = new Aluno();
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
		
		ProcessoAluno pAluno = new ProcessoAluno();
		pAluno.novaMatricula(aluno);
		
		return aluno;
	}

}
