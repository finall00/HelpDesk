<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container mt-5">

<!--    <a class="btn btn-secondary mb-5" href="${pageContext.request.contextPath}/MaquinaListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>-->
    <button class="btn btn-secondary mb-4" onclick="history.back()">Voltar</button>


    <form name="cadastrarmaquina" action="MaquinaCadastrar" method="POST">
        <div class="card">
            <div class="card-header text-center">
                <h3>Cadastro de Maquina</h3>
            </div>
            <div class="card-body">

                <c:if test="${mensagem != null}">
                    <div class="alert alert-info text-center">
                        ${mensagem}
                    </div>
                </c:if>


                <div class="form-group row">
                    <label for="idmaquina" class="col-sm-2 col-form-label">ID:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="idmaquina" id="idmaquina" value="${maquina.idMaquina}" readonly="readonly"/>
                    </div>
                </div>


                <div class="form-group row">
                    <label for="numeroMaquina" class="col-sm-2 col-form-label">Numero da Maquina</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="numeromaquina" id="numeromaquina" value="${maquina.numeroMaquina}" maxlength="50" required/>
                    </div>
                </div>
                    
                <div class="form-group row">
                    <label for="OS" class="col-sm-2 col-form-label">Sistema Operacional</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="OS" id="OS" value="${maquina.OS}" maxlength="50" required/>
                    </div>
                </div>


                <div class="form-group row">
                    <label for="idlaboratorio" class="col-sm-2 col-form-label">Laboratorio:</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="idlaboratorio" id="idlaboratorio" required>
                            <option value="">Selecione</option>
                            <c:forEach var="laboratorio" items="${laboratorios}">
                                <option value="${laboratorio.idLaboratorio}" ${maquina.laboratorio.idLaboratorio == laboratorio.idLaboratorio || idLaboratorio == laboratorio.idLaboratorio ? "selected": ""}>
                                    ${laboratorio.numeroSala}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>

            <div class="card-footer text-center">
                <button type="submit" class="btn btn-primary" id="cadastrar">Cadastrar</button>
                <button type="reset" class="btn btn-secondary" id="limpar">Limpar</button>
            </div>
        </div>

    </form>


</div>

<%@include file="/footer.jsp" %>