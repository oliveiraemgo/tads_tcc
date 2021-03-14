<%@page import="java.util.ArrayList"%>
<%@page import="br.com.academia.bean.Aluno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Matrículas</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="css/style-main.css">
<link rel="stylesheet" href="css/table.css">
</head>
<body>

	<%List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos"); %>

	<header class="top">
		<form action="listarAlunos" method="get">
			<input type="text" name="pesquisar" class="input-top"
				placeholder="Pesquisar..." /> <input type="submit" value="Pesquisar"
				name="op" class="button-top" />
		</form>
	</header>

	<main class="principal">

	<div class="conteudo">

		<br> <a href="listarAlunos?op=todos"><button
				class="bt-azul bt-efeito">Todos os alunos</button></a> <a
			href="listarAlunos?op=pendentes"><button
				class="bt-azul bt-efeito">Alunos pendentes</button></a> <br>
		<br>
		<table class="table">

			<tr>
				<td>Id</td>
				<td>Nome</td>
				<td>Cpf</td>
				<td>Selecionar ficha</td>
			</tr>

			<%for(Aluno aluno: alunos){ %>
			<tr>
				<td><%=aluno.getId() %></td>
				<td><%=aluno.getNome() %></td>
				<td><%=aluno.getCpf() %></td>
				<td><a href="selecionarAluno?id=<%=aluno.getId()%>"
					class="img-plus">selecionar</a></td>
			</tr>
			<%} %>
		</table>

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