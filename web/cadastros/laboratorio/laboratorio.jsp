<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<h1 class="h3 mb-4 p-2 text-gray-800 text-center">Cadastro de Laboratorio</h1>
<div class="d-flex justify-content-between mb-4">
    <p class="p-2 h4">Laboratorios</p>
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo" class="btn btn-primary">Novo</a>-->
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo">Novo</a>-->
    <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success" data-mdb-modal-init data-mdb-target="#staticBackdrop4"><i class='bx bx-plus bx-flip-horizontal iconec' style="color: white;" ></i></button>
</div>
<div class="">
    <table id="example" class="table table-striped" style="width:100%">
        <thead>
            <tr>
                <th>id</th>
                <th>numero</th>
                <th>Qtd. Computadores</th>
                <th></th>
                <th></th>
                <th>status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="laboratorio" items="${laboratorios}">
            <tr>
                <td>${laboratorio.idLaboratorio}</td>
                <td>LAB ${laboratorio.numeroSala}</td>
                <td>${laboratorio.qtnMaquinas}</td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/LaboratorioCarregar?idLaboratorio=${laboratorio.idLaboratorio}"><i class="bx bx-edit-alt iconec"></i></i></a>
                </td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/LaboratorioVisualizar?idLaboratorio=${laboratorio.idLaboratorio}"><i class='bx bx-glasses bx-flip-horizontal iconec'></i></a>
                </td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/LaboratorioExcluir?idLaboratorio=${laboratorio.idLaboratorio}">
                        <button class="btn btn-group-lg btn-danger"
                            <c:out value="${laboratorio.status == 'A' ? 'btn-danger' :'btn-success'}"/>"
                        >
                            <c:out value="${laboratorio.status  == 'A' ? 'Inativar' :'Ativar'}"/>
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <th>Seq.</th>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </tfoot>
    </table>
          


    <div class="modal fade" id="staticBackdrop4" tabindex="-1" aria-labelledby="exampleModalLabel4" aria-hidden="true">
        <div class="modal-dialog d-flex justify-content-center">
            <div class="modal-content w-75">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel4">Cadastro de Laboratorio</h5>
                    <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body p-4">
                    <form name="LaboratorioCadastrar" action="LaboratorioCadastrar"  method="POST">
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" name="idlaboratorio" id="idlaboratorio" value="${laboratorio.idLaboratorio}" readonly="readonly" class="form-control" />
                            <label class="form-label" for="name4">ID</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" name="numerosala" id="numerosala" value="${laboratorio.numeroSala}"  class="form-control" />
                            <label class="form-label" for="name4">Numero Sala</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" name="qtnmaquinas" id="qtnmaquinas" value="${laboratorio.qtnMaquinas}" class="form-control" />
                            <label class="form-label" for="name4">Quantidade de maquinas</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="name4">Status Laboratorio</label>
                            <select class="custom-select" name="status" id="status">
                                <option value="A" ${laboratorio.status == 'A' ? 'selected' : ''}>Ativo</option>
                                <option value="I" ${laboratorio.status == 'I' ? 'selected' : ''}>Inativo</option>
                            </select>
                        </div>
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
                            

        
<%@include file="/footer.jsp" %>