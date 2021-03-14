package br.com.academia.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.academia.bean.Aluno;
import br.com.academia.processos.ProcessoAluno;

@WebServlet(urlPatterns="/deletarAluno")
public class DeletarAluno extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Aluno alunoSelecionado = new Aluno();
		
		//recuperando aluno da sessão
		alunoSelecionado = (Aluno) session.getAttribute("alunoSelecionado");
		
		new ProcessoAluno().deletarMatricula(alunoSelecionado);
		
		req.getRequestDispatcher("listarAlunos?op=todos").forward(req, resp);
		//resp.sendRedirect("home.html");
		
	}
}
