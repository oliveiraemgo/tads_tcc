<%@page import="br.com.academia.bean.Mensalidade"%>
<%@page import="br.com.academia.bean.Modalidade"%>
<%@page import="java.util.List"%>
<%@page import="br.com.academia.bean.Aluno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Matrícula | Modalidade</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="css/style-main.css">
<link rel="stylesheet" href="css/table.css">
</head>
<body>

	<header class="top">
		<form action="listarAlunos" method="get">
			<input type="text" name="pesquisar" class="input-top"
				placeholder="Pesquisar..." /> <input type="submit"
				value="Pesquisar" name="op" class="button-top" />
		</form>
	</header>

	<%
		Aluno aluno = (Aluno) session.getAttribute("aluno");
	%>
	<%
		List<Modalidade> modalidades = (List<Modalidade>) session.getAttribute("modalidades");
	%>

	<main class="principal">
	<div class="conteudo">

		<div class="coluna-esquerda">

			<table class="table">
				<tr>
					<td>Modalidades Disponíveis</td>
					<td>Valor</td>
					<td></td>
				</tr>

				<%
					for (Modalidade modalidade : modalidades) {
				%>
				<tr>
					<td><%=modalidade.getNome()%></td>
					<td><%=modalidade.getValor()%></td>
					<td><a
						href="selecionarModalidade?op=selecionar&id=<%=modalidade.getId()%>"
						class="img-plus">add</a></td>
				</tr>
				<%
					}
				%>

			</table>
		</div>

		<div class="coluna-direita">

			<table class="table">
				<tr>
					<td>Modalidades do(a) aluno(a) <%=aluno.getNome()%></td>
					<td>Valor total: <%=aluno.getMensalidade().getValorMensal()%></td>
				</tr>

				<%
					for (Modalidade modalidade : aluno.getModalidades()) {
				%>
				<tr>
					<td><%=modalidade.getNome()%></td>
					<td><%=modalidade.getValor()%></td>
				</tr>
				<%
					}
				%>

			</table>
			<%
				if (aluno.getModalidades().size() == 0) {
			%>

			<%
				} else if (aluno.getModalidades().size() != 0) {
			%>
			<a href="selecionarModalidade?op=avancar"><button
					class="bt-roxo bt-efeito">Avançar</button></a> <a
				href="selecionarModalidade?op=limparLista"><button
					class="bt-roxo bt-efeito">Limpar lista</button></a>
			<%
				} else
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