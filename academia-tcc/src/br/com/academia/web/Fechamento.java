package br.com.academia.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.academia.bean.FechamentoMensal;
import br.com.academia.processos.Pagamento;

@WebServlet(urlPatterns="/fechamento")
public class Fechamento extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		Pagamento pagamento = new Pagamento();
		FechamentoMensal fechamentoMensal = new FechamentoMensal();
		
		switch (req.getParameter("op")) {
		case "mesAtual":
			
			GregorianCalendar calendar = new GregorianCalendar();
			
			fechamentoMensal.setMesDoFechamento(calendar.get(Calendar.MONTH)+1);
			fechamentoMensal.setAnoDoFechamento(calendar.get(Calendar.YEAR));
			
			fechamentoMensal = pagamento.obterFechamentoMensal(fechamentoMensal);
			fechamentoMensal.setMensalidades(pagamento.obterMensalidades(fechamentoMensal));
			
			req.setAttribute("fechamentoMensal", fechamentoMensal);
			req.getRequestDispatcher("fechamento-mensal.jsp").forward(req, resp);
			
			break;
			
		case "Pesquisar":
			
			fechamentoMensal.setMesDoFechamento(Integer.parseInt(req.getParameter("mes")));
			fechamentoMensal.setAnoDoFechamento(Integer.parseInt(req.getParameter("ano")));
			
			fechamentoMensal = pagamento.obterFechamentoMensal(fechamentoMensal);
			fechamentoMensal.setMensalidades(pagamento.obterMensalidades(fechamentoMensal));
			
			req.setAttribute("fechamentoMensal", fechamentoMensal);
			req.getRequestDispatcher("fechamento-mensal.jsp").forward(req, resp);
			
			break;

		default:
			break;
		}
		
	}

}
