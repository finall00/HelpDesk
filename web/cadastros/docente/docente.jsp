<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<h2>Docente</h2>
<table id="datatable" class="display">
        <thead>
            <tr>
                <th align="left">ID</th>
                <th align="left">Nome</th>
                <th align="left">Contato</th>
                <th align="left">Numero Matricula</th>
                <th align="left">Suitacao</th>
                <th align="right"></th>
                <th align="right"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="docente" items="${docentes}">
                <tr>
                    <td align="left">${docente.idDocente}</td>
                    <td align="left">${docente.nomePessoa}</td>
                    <td align="left">${docente.contato}</td>
                    <td align="left">${docente.numeroMatricula}</td>
                    <td align="center">
                            <a href="${pageContext.request.contextPath}/DocenteExcluir?idDocente=${docente.idDocente}">
                                <button class="btn btn-group-lg
                                    <c:out value="${docente.situacao == 'A' ? 'btn-danger' :'btn-success'}"/>"
                                >
                                    <c:out value="${docente.situacao == 'A' ? 'Inativar' :'Ativar'}"/>
                                </button>
                            </a>
                        </td>
                         <td align="center">
                            <a href="${pageContext.request.contextPath}/DocenteCarregar?idDocente=${docente.idDocente}">
                                <button class="btn btn-group-lg btn-success">Alterar</button>
                            </a>
                        </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div align="center">
        <a href="${pageContext.request.contextPath}/DocenteNovo">Novo</a>
        <a href="index.jsp">Voltar ? Pagina Inicial</a>
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