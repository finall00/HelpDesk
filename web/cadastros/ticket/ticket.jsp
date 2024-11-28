<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>


<h2>Tickets</h2>
<div class="col-8 panel-body">
    <table id="datatable" class="table table-striped table-bordered basic-datatable">
        <thead>
            <tr>
                <th align="left">ID</th>
                <th align="left">Descição</th>
                <th align="left">Laboratorio</th>
                <th align="left">Docente</th>
                <th align="left">Data-Criação</th>
                <th align="left">Data-encerramento</th>
                <th align="left">Status</th>
                <th align="left">Imagem</th>
                <th align="right"></th>
                <th align="right"></th>
            </tr>
        </thead>    
        <body>
            <c:forEach var="ticket" items="${tickets}">
            <tr>
                <td align="left">${ticket.idTicket}</td>
                <td align="left">${ticket.descricao}</td>
                <td align="left">${ticket.laboratorio.numeroSala}</td>
                <td align="left">${ticket.docente.nomePessoa}</td>
                <td align="center"><fmt:formatDate pattern="dd/MM/yyyy" value="${ticket.dataCriação}"/></td>
                <td align="center"><fmt:formatDate pattern="dd/MM/yyyy" value="${ticket.dataEncerramento}"/></td>
                <td align="left">${ticket.status}</td>
                <td align="left"><img src="${ticket.imagem}" height="100" width="200" /></td>
                <td align="center">
                            <a href="${pageContext.request.contextPath}/TicketExcluir?idTicket=${ticket.idTicket}">
                                <button class="btn btn-group-lg
                                    <c:out value="${ticket.status == 'A' ? 'btn-danger' :'btn-success'}"/>"
                                >
                                    <c:out value="${ticket.status  == 'A' ? 'Inativar' :'Ativar'}"/>
                                </button>
                            </a>
                        </td>
            </tr>
            
        </c:forEach>
        </tbody>
    </table> 
</div>
<div align="center">
    <a class="btn btn-group-lg btn-dark" href="${pageContext.request.contextPath}/DespesaNovo">Novo</a>
    <a href="index.jsp" class="btn btn-group-lg btn-secondary">Voltar à página Inicial</a>
</div>

<script>
    $(document).ready(function () {
        console.log('entrei ready');
        //Carregamos a datatable
        //$("#datatable").DataTable(());
        $('#datatable').DataTable({
            "oLanguage": {
                "sProcessing": "Processando...",
                "sLengthMenu": "Mostrar _MENU_ registros",
                "sZeroRecords": "Nenhum registro encontrado.",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                "sInfoFiltered": "",
                "sInfoPostFix": "",
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

     function deletar(codigo){
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Você tem certeza?',
            text: "Você não poderá recuperar depois!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, apague a despesa!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/DespesaExcluir',
                    data:{
                        idDespesa: id
                    },
                    success:
                        function(data){
                            if(data == 1){
                                Swal.fire({
                                    position: 'top-end',
                                    icon: 'success',
                                    title: 'Sucesso',
                                    text: 'Despesa excluída com sucesso!',
                                    showConfirmButton: false,
                                    timer: 2000
                                })
                            } else {
                                Swal.fire({
                                    position: 'top-end',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: 'Não foi possível excluir a despesa!',
                                    showConfirmButton: false,
                                    timer: 2000
                                })
                            }
                            window.location.href = "${pageContext.request.contextPath}/DespesaListar";
                        },
                    error:
                        function(data){
                            window.location.href = "${pageContext.request.contextPath}/DespesaListar";
                        }
                });
            };
        });
    }
</script>


<%@include file="/footer.jsp"%>
