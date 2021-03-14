package br.com.academia.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.academia.bean.Aluno;
import br.com.academia.bean.Treino;
import br.com.academia.processos.Pagamento;
import br.com.academia.processos.ProcessoAluno;


@WebServlet(urlPatterns="/selecionarAluno")
public class SelecionarAluno extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		
		Aluno alunoSelecionado = new Aluno();
		alunoSelecionado.setId(Integer.parseInt(req.getParameter("id")));
		
		//obtendo matricula do aluno
		alunoSelecionado = new ProcessoAluno().obterMatricula(alunoSelecionado);
		
		//recuperando a mensalidade atual
		alunoSelecionado.setMensalidade(new Pagamento().recuperarPagamento(alunoSelecionado));

		
		HttpSession session = req.getSession();
		session.setAttribute("alunoSelecionado", alunoSelecionado);
		
		//redirecionamento no lado servidor
		req.getRequestDispatcher("/WEB-INF/paginas/perfil.jsp").forward(req, resp);
		
	}

}
