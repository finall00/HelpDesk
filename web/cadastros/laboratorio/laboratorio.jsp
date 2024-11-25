<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<h2>Laboratorios</h2>
<table id="datatable"  class="display">
        <thead>
            <tr>
                <th align="left">ID</th>
                <th align="left">Numero Sala</th>
                <th align="left">Quantidade de Computadores</th>
                <th>Status</th>
                <th align="right"></th>
                <th align="right"></th>
                <th align="right"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="laboratorio" items="${laboratorios}">
                <tr>
                    <td align="left">${laboratorio.idLaboratorio}</td>
                    <td align="left">LAB ${laboratorio.numeroSala}</td>
                    <td align="left"> ${laboratorio.qtnMaquinas}</td>
                    <td align="left">${laboratorio.status == 'A' ? 'Ativo' : 'Inativo'}</td>
                    
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/LaboratorioExcluir?idLaboratorio=${laboratorio.idLaboratorio}">Excluir</a>
                    </td>
                     <td align="center">
                        <a href="${pageContext.request.contextPath}/LaboratorioCarregar?idLaboratorio=${laboratorio.idLaboratorio}">Alterar</a>
                    </td>
                     <td align="center">
                        <a href="${pageContext.request.contextPath}/LaboratorioVisualizar?idLaboratorio=${laboratorio.idLaboratorio}">Visualizar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div align="center">
        <a href="${pageContext.request.contextPath}/LaboratorioNovo">Novo</a>
        <a href="index.jsp">Voltar á Pagina Inicial</a>
    </div>
  
    <script>
        $(document).ready(function (){
            console.log('entrei ready')
             $('#datatable').DataTable({
                "oLanguage":{
                    "sProcessing":"Processando..",
                    "sLengthMenu":"Mostrar _MENU_ registros",
                    "sZeroRecords":"Nenhum registro encontrado",
                    "sInfo":"Mostrando _START_ ate _END_ de _TOTAL_ registros",
                    "sInfoEmpty":"Mostrando de 0 ate 0 registros",
                    "sInfoFiletred":"",
                    "sInfoPostFix":"",
                    "sSearch":"Buscar",
                    "sUrl":"",
                    "oPaginate":{
                        "sFirst":"Primeiro",
                        "sPrevious":"Anterior",
                        "sNext":"Seguinte",
                        "sLast":"ultimo",
                    }
                }
            })
        })
    </script>
          
        
<%@include file="/footer.jsp" %>