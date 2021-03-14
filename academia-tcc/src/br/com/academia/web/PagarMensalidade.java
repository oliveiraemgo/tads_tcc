package br.com.academia.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.academia.bean.Aluno;
import br.com.academia.processos.Pagamento;

@WebServlet(urlPatterns="/pagarMensalidade")
public class PagarMensalidade extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Aluno aluno = (Aluno) session.getAttribute("alunoSelecionado");
		
		Pagamento pagamento = new Pagamento();
		
		switch (req.getParameter("op")) {
		case "gerarBoleto":
			
			aluno.setMensalidade(pagamento.gerarMensalidade(aluno));
			
			req.getRequestDispatcher("/WEB-INF/paginas/pagamento.jsp").forward(req, resp);
			
			
			break;
			
		case "pagarBoleto":
			
			pagamento.pagarMensalidade(aluno);
			
			req.getRequestDispatcher("listarAlunos?op=todos").forward(req, resp);
			//resp.sendRedirect("home.html");
			
			break;
			
		case "voltar":
			
			req.getRequestDispatcher("listarAlunos?op=todos").forward(req, resp);
			
			break;

		default:
			break;
		}
		
	}

}
