<%@page import="br.com.academia.bean.Aluno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Pagamento</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="css/style-main.css">
<link rel="stylesheet" href="css/table.css">
</head>
<body>

	<%Aluno aluno = (Aluno) session.getAttribute("alunoSelecionado"); %>

	<header class="top">
		<form action="listarAlunos" method="get">
			<input type="text" name="pesquisar" class="input-top"
				placeholder="Pesquisar..." /> <input type="submit" value="Pesquisar"
				name="op" class="button-top" />
		</form>
	</header>

	<main class="principal">
	<div class="conteudo">

		<h1>Pagamento</h1>
		<h2>
			Valor da mensalidade:
			<%=aluno.getMensalidade().getValorMensal() %></h2>
		<h2>
			Juros:
			<%=aluno.getMensalidade().getJuros() %></h2>
		<h2>
			Desconto:
			<%=aluno.getMensalidade().getDesconto() %></h2>
		<h2>
			Valor total:
			<%=aluno.getMensalidade().getValorTotal() %></h2>

		<br> <a href="pagarMensalidade?op=pagarBoleto"><button
				class="bt-roxo bt-efeito">Pagar</button></a> <a
			href="pagarMensalidade?op=voltar"><button
				class="bt-roxo bt-efeito">Voltar</button></a>

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