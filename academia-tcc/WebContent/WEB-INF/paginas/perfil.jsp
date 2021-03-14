<%@page import="br.com.academia.bean.Treino"%>
<%@page import="br.com.academia.bean.Modalidade"%>
<%@page import="br.com.academia.bean.Aluno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Perfil do aluno</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="css/style-main.css">
<link rel="stylesheet" href="css/efeitos.css">
<link rel="stylesheet" href="css/table-treino.css">
</head>
<body>

	<%
		Aluno alunoSelecionado = (Aluno) session.getAttribute("alunoSelecionado");
	%>

	<header class="top">
		<form action="listarAlunos" method="get">
			<input type="text" name="pesquisar" class="input-top"
				placeholder="Pesquisar..." /> <input type="submit"
				value="Pesquisar" name="op" class="button-top" />
		</form>
	</header>

	<main class="principal">
	<div class="conteudo">

		<div class="aluno">

			<a href="redirect?acao=alterarMatricula" class="config">atualizar</a>

			<h1 class="dados-do-aluno"><%=alunoSelecionado.getNome().charAt(0)%></h1>

			<h2 class="dados-do-aluno"><%=alunoSelecionado.getNome()%></h2>

			<a href="deletarAluno" class="img-lixeira">excluir</a>

		</div>

		<div class="modalidade">
			<h2>Modalidades</h2>

			<%
				for (int i = 0; i < alunoSelecionado.getModalidades().size(); i++) {
			%>
			<%
				if (alunoSelecionado.getModalidades().get(i).getNome().equals("Muay thai")) {
			%>
			<div class="box-luta"></div>
			<%
				} else if (alunoSelecionado.getModalidades().get(i).getNome().equals("Musculação")) {
			%>
			<div class="box-musculacao"></div>
			<%
				} else if (alunoSelecionado.getModalidades().get(i).getNome().equals("Dança sertaneja")) {
			%>
			<div class="box-danca"></div>
			<%
				}
			%>
			<%
				}
			%>

		</div>

		<div class="endereco">

			<h2>Endereço</h2>
			<p><%=alunoSelecionado.getEndereco().getLogradouro()%>
				<%=alunoSelecionado.getEndereco().getComplemento()%>
				<%=alunoSelecionado.getEndereco().getNumero()%></p>
			<p>
			<p><%=alunoSelecionado.getEndereco().getBairro()%>
				-
				<%=alunoSelecionado.getEndereco().getCidade()%>
				/
				<%=alunoSelecionado.getEndereco().getEstado()%></p>
			<p><%=alunoSelecionado.getEndereco().getCep()%></p>

		</div>

		<div class="contato">

			<h2>Contato</h2>
			<p>
				Telefone:
				<%=alunoSelecionado.getContato().getTelefone()%></p>
			<p>
				Celular:
				<%=alunoSelecionado.getContato().getCelular()%></p>
			<p><%=alunoSelecionado.getContato().getEmail()%></p>
			<br>

		</div>

		<div class="status-pagamento">

			<h3>
				Status da mensalidade atual:
				<%
				if (alunoSelecionado.getMensalidade().getValorTotal() == 0) {
			%>Em
				aberto
				<%
				} else {
			%>Pago<%
				}
			%>
			</h3>

			<%
				if (alunoSelecionado.getMensalidade().getValorTotal() == 0) {
			%>
			<a href="pagarMensalidade?op=gerarBoleto"><button
					class="bt-roxo bt-efeito">Gerar boleto</button></a>
			<%
				}
			%>
		</div>
		
	</div>

	</main>

	<aside class="aux">

		<ul class="ca-menu">
			<li><a href="listarAlunos?op=todos"> <span
					class="ca-icon img-perfil">Home</span>
			</a></li>
			<li><a href="novo-aluno.html"> <span class="ca-icon img-add">Novo
						aluno</span>
			</a></li>
			<li><a href="fechamento?op=mesAtual"> <span
					class="ca-icon img-grafico">Fechamento</span>
			</a></li>
		</ul>

	</aside>

	<footer class="rodape"> &copy; FitnessApp </footer>

</body>
</html>