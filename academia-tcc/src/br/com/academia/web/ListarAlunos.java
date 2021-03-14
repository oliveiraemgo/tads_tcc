package br.com.academia.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.academia.bean.Aluno;
import br.com.academia.processos.ProcessoAluno;

@WebServlet(urlPatterns="/listarAlunos")
public class ListarAlunos extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		ProcessoAluno pAluno = new ProcessoAluno();
		
		List<Aluno> alunos = new ArrayList<>();
		
		switch (req.getParameter("op")) {
		case "todos":
			
			alunos = pAluno.listarMatriculas();
			req.setAttribute("alunos", alunos);
			req.getRequestDispatcher("/WEB-INF/paginas/lista.jsp").forward(req, resp);
			
			break;
			
		case "pendentes":
			
			alunos = pAluno.listarMatriculasPendentes();
			req.setAttribute("alunos", alunos);
			req.getRequestDispatcher("/WEB-INF/paginas/lista.jsp").forward(req, resp);
			
			break;
			
		case "Pesquisar":
			
			Aluno aluno = new Aluno();
			aluno.setNome(req.getParameter("pesquisar"));
			
			alunos = pAluno.listarMatriculasPeloNome(aluno);
			
			req.setAttribute("alunos", alunos);
			req.getRequestDispatcher("/WEB-INF/paginas/lista.jsp").forward(req, resp);
			
			break;

		default:
			break;
		}
		
	}

}
