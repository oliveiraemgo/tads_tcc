<%@page import="br.com.academia.bean.Aluno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Alterar Matr�cula</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="css/style-main.css">
<link rel="stylesheet" href="css/table.css">

<script type="text/javascript" src="js/mascaras.js"></script>

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

	<div class="formulario">

		<h1>Alterar matr�cula</h1>

		<form action="alterarMatricula" method="get">

			<div class="coluna-esquerda">

				<fieldset>
					<legend>Dados pessoais</legend>
					<label for="nome" class="label-form">Nome: </label> <input
						type="text" name="nome" class="input-form" maxlength="30"
						value="<%=aluno.getNome() %>" required /><br> <label
						for="cpf" class="label-form">Cpf: </label> <input type="text"
						name="cpf" class="input-form" onkeypress="mascara(this, mcpf);"
						value="<%=aluno.getCpf() %>" maxlength="14" required /><br> <label
						for="dt_nascimento" class="label-form">Data de nascimento:
					</label> <input type="text" name="dt_nascimento" class="input-form"
						onkeypress="mascara(this, mdata);"
						value="<%=aluno.getDataNascimentoFormatada() %>" maxlength="10"
						required /><br>
				</fieldset>

				<fieldset>
					<legend>Contato</legend>
					<label for="telefone" class="label-form">Telefone: </label> <input
						type="tel" name="telefone" class="input-form"
						placeholder="(11) 0000-0000" onkeypress="mascara(this, mtel);"
						maxlength="14" value="<%=aluno.getContato().getTelefone() %>"
						required /><br> <label for="celular" class="label-form">Celular:
					</label> <input type="tel" name="celular" class="input-form"
						placeholder="(11) 90000-0000" onkeypress="mascara(this, mcel);"
						maxlength="15" value="<%=aluno.getContato().getCelular() %>"
						required /><br> <label for="email" class="label-form">E-mail:
					</label> <input type="email" name="email" class="input-form"
						placeholder="exemplo@exemplo.com"
						value="<%=aluno.getContato().getEmail() %>" required /><br>

				</fieldset>

			</div>

			<div class="coluna-direita">

				<fieldset>
					<legend>Endere�o</legend>
					<label for="logradouro" class="label-form">Logradouro: </label> <input
						type="text" name="logradouro" class="input-form" maxlength="70"
						value="<%=aluno.getEndereco().getLogradouro() %>" required /><br>

					<label for="numero" class="label-form">N�mero: </label> <input
						type="number" name="numero" class="input-form"
						value="<%=aluno.getEndereco().getNumero() %>" required /><br>

					<label for="complemento" class="label-form">Complemento: </label> <input
						type="text" name="complemento" class="input-form" maxlength="19"
						value="<%=aluno.getEndereco().getComplemento() %>" /><br> <label
						for="bairro" class="label-form">Bairro: </label> <input
						type="text" name="bairro" class="input-form" maxlength="30"
						value="<%=aluno.getEndereco().getBairro() %>" required /><br>

					<label for="cidade" class="label-form">Cidade: </label> <input
						type="text" name="cidade" class="input-form" maxlength="30"
						value="<%=aluno.getEndereco().getCidade() %>" required /><br>

					<label for="estado" class="label-form">Estado: </label> <input
						type="text" name="estado" class="input-form" maxlength="30"
						value="<%=aluno.getEndereco().getEstado() %>" required /><br>

					<label for="cep" class="label-form">Cep: </label> <input
						type="text" name="cep" class="input-form" placeholder="00000-000"
						onkeypress="mascara(this, mcep);" maxlength="9"
						value="<%=aluno.getEndereco().getCep() %>" required /><br>

				</fieldset>
				<br>
				<br>
				<br>

			</div>


			<input type="submit" name="acao" value="Alterar"
				class="bt-azul bt-efeito" /> <input type="reset" value="Limpar"
				class="bt-roxo bt-efeito" />

		</form>

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