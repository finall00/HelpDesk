<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    <h1 class="h3 mb-2 text-gray-800">Visualiza��o do Laborat�rio</h1>
    
    <!-- Bot�o para adicionar nova m�quina -->
    <a href="${pageContext.request.contextPath}/MaquinaNovo?laboratorioId=${laboratorio.idLaboratorio}" class="btn btn-success mb-4"><i class="fas fa-plus fa-fw"></i> Adicionar Nova M�quina</a>
    <a href="index.jsp" class="btn btn-success mb-4"><i class="fas fa-home fa-fw"></i> Voltar � p�gina Inicial</a>

    <div class="col-8 panel-body">
        <h3>M�quinas no Laborat�rio: ${laboratorio.numeroSala}</h3> <!-- Nome do laborat�rio ou n�mero da sala -->

        <table id="datatable" class="table table-striped table-bordered basic-datatable">
            <thead>
                <tr>
                    <th align="left">ID</th>
                    <th align="left">N�mero da M�quina</th>
                    <th align="left">SO</th>
                    <th align="left">Laborat�rio</th>
                    <th align="right">A��es</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="maquina" items="${maquinas}">
                    <tr>
                        <td align="left">${maquina.idMaquina}</td>
                        <td align="left">${maquina.numeroMaquina}</td>
                        <td align="left">${maquina.OS}</td>
                        <td align="left">LAB ${maquina.laboratorio.numeroSala}</td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath}/MaquinaExcluir?idMaquina=${maquina.idMaquina}">
                                <button class="btn btn-group-lg btn-danger">Excluir</button>
                            </a>
                        </td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath}/MaquinaCarregar?idMaquina=${maquina.idMaquina}"> 
                                <button class="btn btn-group-lg btn-success">Alterar</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script>
        $(document).ready(function () {
            // Configura��o da tabela para facilitar a pesquisa e pagina��o
            $('#datatable').DataTable({
                "oLanguage": {
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "Nenhum registro encontrado.",
                    "sInfo": "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 at� 0 de 0 registros",
                    "sInfoFiltered": "",
                    "sSearch": "Buscar:",
                    "sUrl": "",
                    "oPaginate": {
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Seguinte",
                        "sLast": "�ltimo"
                    }
                }
            });
        });
    </script>
</div>

<%@include file="/footer.jsp" %>
