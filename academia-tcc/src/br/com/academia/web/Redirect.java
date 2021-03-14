package br.com.academia.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/redirect")
public class Redirect extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {


		switch (req.getParameter("acao")) {
		case "alterarMatricula":
			
			req.getRequestDispatcher("/WEB-INF/paginas/alterar-matricula.jsp").forward(req, resp);
			
			break;

		default:
			break;
		}
		
	}

}
