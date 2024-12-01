<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<h1 class="h3 mb-4 p-2 text-gray-800 text-center">Laboratorio vizualizar</h1>
<a href="index.jsp" class="btn btn-success mb-4"><i class="fas fa-home fa-fw"></i> Voltar à página Inicial</a>
<a href="${pageContext.request.contextPath}/MaquinaNovo?laboratorioId=${laboratorio.idLaboratorio}" class="btn btn-success mb-4"><i class="fas fa-plus fa-fw"></i> Adicionar Nova Máquina</a>

<div class="d-flex justify-content-between mb-4">
    <p class="p-2 h4">Máquinas no Laboratório: ${laboratorio.numeroSala}</p>
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo" class="btn btn-primary">Novo</a>-->
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo">Novo</a>-->
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo" data-mdb-button-init data-mdb-ripple-init class="btn btn-success" data-mdb-modal-init data-mdb-target="#staticBackdrop4"><i class='bx bx-plus bx-flip-horizontal iconec' style="color: white;" ></i></a>-->
    <!-- <a href="${pageContext.request.contextPath}/MaquinaNovo" class="btn btn-success"><i class='bx bx-plus bx-flip-horizontal iconec' style="color: white;" ></i></a> -->
    <!--<button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success" data-mdb-modal-init data-mdb-target="#staticBackdrop4"><i class='bx bx-plus bx-flip-horizontal iconec' style="color: white;" ></i></button>-->
</div>
<div class="">
    <table id="example" class="table table-striped" style="width:100%">
        <thead>
            <tr>
                <th>Id</th>
                <th>Numero Maquina</th>
                <th>S0</th>
                <th>Laboratorio</th>
                <th>Ações</th>
                <th></th>
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
                        <i class='bx bx-trash iconec'></i>
                    </a>
                </td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/MaquinaCarregar?idMaquina=${maquina.idMaquina}"><i class="bx bx-edit-alt iconec"></i></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
          


     <div class="modal fade" id="staticBackdrop4" tabindex="-1" aria-labelledby="exampleModalLabel4" aria-hidden="true">
        <div class="modal-dialog d-flex justify-content-center">
            <div class="modal-content w-75">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel4">Cadastro de Maquina</h5>
                    <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body p-4">
                <form name="cadastrarmaquina" action="MaquinaCadastrar" method="POST">
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" name="idmaquina" id="idmaquina" value="${maquina.idMaquina}" readonly="readonly" class="form-control" />
                            <label class="form-label" for="name4">ID</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input  type="text" name="numeromaquina" id="numeromaquina" value="${maquina.numeroMaquina}" maxlength="50" required class="form-control" />
                            <label class="form-label" for="name4">Numero da Maquina</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text"  name="OS" id="OS" value="${maquina.OS}" maxlength="50" required class="form-control" />
                            <label class="form-label" for="name4">Sistema Operacional</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="name4">Laboratorio:</label>
                            <select class="custom-select" name="idlaboratorio" id="idlaboratorio" required>
                                <option value="">Selecione</option>
                                <c:forEach var="laboratorio" items="${laboratorios}">
                                     <option value="${laboratorio.idLaboratorio}" ${maquina.laboratorio.idLaboratorio == laboratorio.idLaboratorio || idLab == laboratorio.idLaboratorio ? "selected": ""}>
                                          ${laboratorio.numeroSala}
                                     </option>
                                </c:forEach>
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
                            
<script>
    window.onload = function () {
        const showModal = '<%= request.getAttribute("showModalVizualizar") %>';
        if (showModal === 'true') {
            const myModal = new bootstrap.Modal(document.getElementById('staticBackdrop4'));
            myModal.show();
        }
    };
</script>

        
<%@include file="/footer.jsp" %>