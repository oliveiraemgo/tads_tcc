package br.com.academia.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.academia.bean.Aluno;
import br.com.academia.bean.Mensalidade;
import br.com.academia.bean.Modalidade;
import br.com.academia.processos.Inscricao;
import br.com.academia.processos.ProcessoModalidade;

@WebServlet(urlPatterns="/selecionarModalidade")
public class NovaModalidade extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		//recuperando aluno da sessão
		Aluno aluno = (Aluno) session.getAttribute("aluno");
		
		//recuperando modalidades da sessão
		List<Modalidade> modalidades = (List<Modalidade>) session.getAttribute("modalidades");
		
		ProcessoModalidade pModalidade = new ProcessoModalidade();
		
		switch (req.getParameter("op")) {
		case "selecionar":
			
			Modalidade modalidade = new Modalidade();
			modalidade.setId(Integer.parseInt(req.getParameter("id")));
			
			modalidade = pModalidade.obterModalidade(modalidade);
			
			aluno.getModalidades().add(modalidade);
			
			//atribuindo o valor da modalidade
			aluno.getMensalidade().setValores(modalidade.getValor());
			aluno.getMensalidade().setValorMensal(aluno.getMensalidade().getValores());
			
			for(int i = 0; i < modalidades.size(); i++){
				if(modalidades.get(i).getId() == Integer.parseInt(req.getParameter("id"))){
					modalidades.remove(i);
				}
			}
			
			req.getRequestDispatcher("/WEB-INF/paginas/modalidade.jsp").forward(req, resp);
			
			
			break;
			
		case "avancar":
			
			Inscricao inscricao = new Inscricao();
			
			//excluindo a inscrição anterior para incluir a atual inscrição
			inscricao.deletarInscricao(aluno);
			inscricao.inscreverAlunoNaModalidade(aluno);
			
			session.invalidate();
			
			req.getRequestDispatcher("listarAlunos?op=todos").forward(req, resp);
			//resp.sendRedirect("home.html");
			
			break;
			
		case "limparLista":
			
			modalidades = pModalidade.recuperarModalidades();
			session.setAttribute("modalidades", modalidades);
			
			aluno.getModalidades().clear();
			
			aluno.setMensalidade(new Mensalidade());
			
			req.getRequestDispatcher("/WEB-INF/paginas/modalidade.jsp").forward(req, resp);
			
			break;

		default:
			break;
		}
		
		
	}
	
}
