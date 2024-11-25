<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    <h1 class="h3 mb-2 text-gray-800">Visualização do Laboratório</h1>
    
    <!-- Botão para adicionar nova máquina -->
    <a href="${pageContext.request.contextPath}/MaquinaNovo?laboratorioId=${laboratorio.idLaboratorio}" class="btn btn-success mb-4"><i class="fas fa-plus fa-fw"></i> Adicionar Nova Máquina</a>
    <a href="index.jsp" class="btn btn-success mb-4"><i class="fas fa-home fa-fw"></i> Voltar à página Inicial</a>

    <div class="col-8 panel-body">
        <h3>Máquinas no Laboratório: ${laboratorio.numeroSala}</h3> <!-- Nome do laboratório ou número da sala -->

        <table id="datatable" class="table table-striped table-bordered basic-datatable">
            <thead>
                <tr>
                    <th align="left">ID</th>
                    <th align="left">Número da Máquina</th>
                    <th align="left">SO</th>
                    <th align="left">Laboratório</th>
                    <th align="right">Ações</th>
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
            // Configuração da tabela para facilitar a pesquisa e paginação
            $('#datatable').DataTable({
                "oLanguage": {
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "Nenhum registro encontrado.",
                    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                    "sInfoFiltered": "",
                    "sSearch": "Buscar:",
                    "sUrl": "",
                    "oPaginate": {
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Seguinte",
                        "sLast": "Último"
                    }
                }
            });
        });
    </script>
</div>

<%@include file="/footer.jsp" %>
