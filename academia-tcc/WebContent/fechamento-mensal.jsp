<%@page import="br.com.academia.bean.Mensalidade"%>
<%@page import="br.com.academia.bean.FechamentoMensal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="css/style-main.css">
<link rel="stylesheet" href="css/table.css">
</head>
<body>

	<%FechamentoMensal fechamentoMensal = (FechamentoMensal) request.getAttribute("fechamentoMensal"); %>

	<header class="top">
		<form action="listarAlunos" method="get">
			<input type="text" name="pesquisar" class="input-top"
				placeholder="Pesquisar..." /> <input type="submit"
				value="Pesquisar" name="op" class="button-top" />
		</form>
	</header>

	<main class="principal">
	<div class="conteudo">

		<div class="coluna-esquerda">
			<h1>Fechamento mensal</h1>

			<form action="fechamento" method="get">

				<input type="number" name="ano" class="input-form"
					placeholder="escolha o ano" maxlength="4" required="required" /> <select
					name="mes" class="input-form">
					<option value="1">Janeiro</option>
					<option value="2">Fevereiro</option>
					<option value="3">Março</option>
					<option value="4">Abril</option>
					<option value="5">Maio</option>
					<option value="6">Junho</option>
					<option value="7">Julho</option>
					<option value="8">Agosto</option>
					<option value="9">Setembro</option>
					<option value="10">Outubro</option>
					<option value="11">Novembro</option>
					<option value="12">Dezembro</option>
				</select> <br> <br> <input type="submit" value="Pesquisar"
					name="op" class="bt-azul bt-efeito">

			</form>

			<br>
			<h3>
				Mês/ano:
				<%=fechamentoMensal.getMesDoFechamento() %>/<%=fechamentoMensal.getAnoDoFechamento() %></h3>
			<h3>
				Total de juros:
				<%=fechamentoMensal.getTotalJuros()%></h3>
			<h3>
				Total de descontos:
				<%=fechamentoMensal.getTotalDescontos() %></h3>
			<h3>
				Total Pago:
				<%=fechamentoMensal.getTotalPago()%></h3>
			<br>
		</div>

		<div class="coluna-direita">

			<table class="table">
				<tr>
					<td>data do pagamento</td>
					<td>valor total</td>
					<td>juros</td>
					<td>descontos</td>
				</tr>

				<%for(Mensalidade mensalidade: fechamentoMensal.getMensalidades()){ %>
				<tr>
					<td><%=mensalidade.getDataDoPagamentoFormatada() %>
					<td><%=mensalidade.getValorTotal() %>
					<td><%=mensalidade.getJuros() %>
					<td><%=mensalidade.getDesconto() %>
				</tr>
				<%} %>

			</table>
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