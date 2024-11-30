<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>
<div class="container-fluid">
    <h1 class="h3 mb-2 text-gray-800">Tickets</h1>
         
    <p class="mb-4">Formulario de Cadastro</p>
    <a class="btn btn-secondary mb-4" href="${pageContext.request.contextPath}/TicketListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>
    <div class="row">
        <div class="col">
            <div class="card shadow mb-4"> 
                <div class="card-body">
                    <div class="form-group">
                        <center>
                            <img alt="imagem" class="img-thumbnail" 
                                 src="${ticket.imagem}" 
                                 name="target" id="target" width="170" heigth="200">
                            <br><br>
                            <input type="file" id="gallery-photo-add" 
                                   class="inputfile" onchange="uploadFile()"/>
                            <label for="gallery-photo-add" class="btn btn-success">
                                <i class="fas fa-file-upload"></i> 
                                Selecionar Documento...
                            </label>
                        </center>
                    </div>
                </div>
            </div>

            <form name="cadastrarticket" action="TicketCadastrar" method="POST">
                <div class="card">
                    <div class="card-body">
                        <div class="form-group row">
                            <label for="idticket" class="col-sm-2 col-form-label">ID:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="idticket" id="idticket" value="${ticket.idTicket}" readonly="readonly"/>
                            </div>
                        </div>


                        <div class="form-group row">
                            <label for="descTicket" class="col-sm-2 col-form-label">descricao do Problema</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="descricao" id="descricao" value="${ticket.descricao}" required/>
                            </div>
                        </div>


                        <div class="form-group row">
                            <label for="idlaboratorio" class="col-sm-2 col-form-label">Laboratorio:</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="idlaboratorio" id="idlaboratorio" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="laboratorio" items="${laboratorios}">
                                        <option value="${laboratorio.idLaboratorio}" ${ticket.laboratorio.idLaboratorio == laboratorio.idLaboratorio || idLaboratorio == laboratorio.idLaboratorio ? "selected": ""}>
                                            ${laboratorio.numeroSala}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="iddocente" class="col-sm-2 col-form-label">Docente</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="iddocente" id="iddocente" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="docente" items="${docentes}">
                                        <option value="${docente.idDocente}">
                                            ${docente.nomePessoa}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="idstatus" class="col-sm-2 col-form-label">status</label>
                            <select name="status" id="status">
                                <option value="A" ${ticket.status == 'A' ? 'selected' : ''}>Ativo</option>
                                <option value="I" ${ticket.status == 'I' ? 'selected' : ''}>Inativo</option>
                            </select>
                        </div>
                    </div>

                    <div class="card-footer text-center">
                        <button type="submit" class="btn btn-primary" id="cadastrar" onclick="gravarDados()"> Cadastrar </button>
                        <button type="reset" class="btn btn-secondary" id="limpar">Limpar</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<style type="text/css">
    .inputfile {
        /* visibility: hidden etc. wont work */
        width: 0.1px;
        height: 0.1px;
        opacity: 0;
        overflow: hidden;
        position: absolute;
        z-index: -1;
    }
    .inputfile:focus + label {
        /* keyboard navigation */
        outline: 1px dotted #000;
        outline: -webkit-focus-ring-color auto 5px;
    }
    .inputfile + label * {
        pointer-events: none;
    }
    .borda{
        position: relative;
        margin: 0 20px 30px 0;
        padding: 10px;
        border: 1px solid #e1e1e1;
        border-radius: 3px;
        background: #fff;
        -webkit-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        -moz-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
    }
</style>

<script>  
    console.log("aaaaaaaaaaaaaa")
    function gravarDados() {
        console.log("Gravando dados ....");
        var target = document.getElementById("target").src;
        $.ajax({
            type: 'post',
            url: 'TicketCadastrar',
            data: {
                idticket :$('#idticket').val(),
                descricao :$('#descricao').val(),
                idlaboratorio: $('#idlaboratorio').val(),
                iddocente :$('#iddocente').val(),
                status :$('#status').val(),
                imagem: target
            },
            success:
                    function (data) {
                        console.log("reposta servlet->");
                        console.log(data);
                        if (data == 1) {
                           console.log("enviou os dados").then(function(){
                                window.location.href = "${pageContext.request.contextPath}/TicketListar";
                            })
                        } else {
                            console.log("nao enviou os dados").then(function(){
                                window.location.href = "${pageContext.request.contextPath}/TicketListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/TicketListar";
                    }
        });
    }
</script>   
<jsp:include page="/footer.jsp"/>