<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<h1 class="h3 mb-4 p-2 text-gray-800 text-center">Docente</h1>
<a href="index.jsp" class="btn btn-success mb-4"><i class="fas fa-home fa-fw"></i> Voltar à página Inicial</a>
<div class="d-flex justify-content-between mb-4">
    <p class="p-2 h4">Docente</p>
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo" class="btn btn-primary">Novo</a>-->
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo">Novo</a>-->
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo" data-mdb-button-init data-mdb-ripple-init class="btn btn-success" data-mdb-modal-init data-mdb-target="#staticBackdrop4"><i class='bx bx-plus bx-flip-horizontal iconec' style="color: white;" ></i></a>-->
    <a href="${pageContext.request.contextPath}/DocenteNovo" class="btn btn-success"><i class='bx bx-plus bx-flip-horizontal iconec' style="color: white;" ></i></a>

    <!--<button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success" data-mdb-modal-init data-mdb-target="#staticBackdrop4"><i class='bx bx-plus bx-flip-horizontal iconec' style="color: white;" ></i></button>-->
</div>
<div class="">
    <table id="example" class="table table-striped" style="width:100%">
        <thead>
            <tr>
                <th>id</th>
                <th>Nome</th>
                <th>Contato</th>
                <th>Numero Matricula</th>
                <th>Situacao</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="docente" items="${docentes}">
            <tr>
                <td>${docente.idDocente}</td>
                <td>${docente.nomePessoa}</td>
                <td>${docente.contato}</td>
                <td>${docente.numeroMatricula}</td>
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
                    <a href="${pageContext.request.contextPath}/DocenteCarregar?idDocente=${docente.idDocente}"><i class="bx bx-edit-alt iconec"></i></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
          


    <div class="modal fade" id="staticBackdrop4" tabindex="-1" aria-labelledby="exampleModalLabel4" aria-hidden="true">
        <div class="modal-dialog d-flex justify-content-center">
            <div class="modal-content w-75">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel4">Docente</h5>
                    <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body p-4">
                    <form name="docenteCadastrar" action="DocenteCadastrar" method="POST">
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" name="iddocente" id="iddocente" value="${docente.idDocente}" readonly="readonly" class="form-control" />
                            <label class="form-label" for="name4">ID</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" name="nomepessoa" id="nomepessoa" value="${docente.nomePessoa}" size="50" maxlength="50" class="form-control" />
                            <label class="form-label" for="name4">Nome</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" name="contato" id="contato" value="${docente.contato}" size="50" maxlength="50" class="form-control" />
                            <label class="form-label" for="name4">Contato</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" name="numeromatricula" id="numeromatricula" value="${docente.numeroMatricula}" size="50" maxlength="50" class="form-control" />
                            <label class="form-label" for="name4">Numero Matricula</label>
                        </div> 
                            <input type="hidden" name="situacao" id="situacao" value="${docente.situacao}" readonly="readonly" class="form-control" />                    
                        <div class="d-flex justify-content-end gap-2">
                            <input class="btn btn-primary"  type="submit" name="cadastrar" id="cadastrar" value="Cadastrar"/>
                            <input class="btn btn-danger" type="reset" name="limpar" id="limpar" value="Limpar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    </div>
                            
<script>
    window.onload = function () {
        const showModal = '<%= request.getAttribute("showModal") %>';
        if (showModal === 'true') {
            const myModal = new bootstrap.Modal(document.getElementById('staticBackdrop4'));
            myModal.show();
        }
    };
</script>

        
<%@include file="/footer.jsp" %>