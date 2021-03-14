<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<!-- diretiva utilizada para indicar que esta é uma página de controle de erro -->
<%@page isErrorPage="true"%>

<html>
<head>

<title>erro</title>

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

	<main class="principal">

	<div class="conteudo">
		<br>
		<h1>Desculpe, ocorreu um erro, por favor tente novamente:
			${pageContext.errorData.throwable}</h1>

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