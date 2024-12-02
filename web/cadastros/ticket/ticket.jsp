<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<h1 class="h3 mb-4 p-2 text-gray-800 text-center">Ticket</h1>
<a href="index.jsp" class="btn btn-success mb-4"><i class="fas fa-home fa-fw"></i> Voltar à página Inicial</a>
<div class="d-flex justify-content-between mb-4">
    <p class="p-2 h4">Ticket</p>
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo" class="btn btn-primary">Novo</a>-->
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo">Novo</a>-->
    <!--<a href="${pageContext.request.contextPath}/LaboratorioNovo" data-mdb-button-init data-mdb-ripple-init class="btn btn-success" data-mdb-modal-init data-mdb-target="#staticBackdrop4"><i class='bx bx-plus bx-flip-horizontal iconec' style="color: white;" ></i></a>-->
    <a href="${pageContext.request.contextPath}/TicketNovo" class="btn btn-success"><i class='bx bx-plus bx-flip-horizontal iconec' style="color: white;" ></i></a>

    <!--<button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success" data-mdb-modal-init data-mdb-target="#staticBackdrop4"><i class='bx bx-plus bx-flip-horizontal iconec' style="color: white;" ></i></button>-->
</div>
<div class="">
    <table id="example" class="table table-striped" style="width:100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Descição</th>
                <th>Laboratorio</th>
                <th>Docente</th>
                <th>Data-Criação</th>
                <th>Data-encerramento</th>
                <th>Status</th>
                <th>Imagem</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ticket" items="${tickets}">
            <tr>
                <td>${ticket.idTicket}</td>
                <td>${ticket.descricao}</td>
                <td>${ticket.laboratorio.numeroSala}</td>
                <td>${ticket.docente.nomePessoa}</td>
                <td align="center"><fmt:formatDate pattern="dd/MM/yyyy" value="${ticket.dataCriação}"/></td>
                <td align="center"><fmt:formatDate pattern="dd/MM/yyyy" value="${ticket.dataEncerramento}"/></td>
                <td align="left">${ticket.status}</td>
                <td align="left"><img src="${ticket.imagem}" height="100" width="200"  style="cursor: pointer;" /></td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/TicketExcluir?idTicket=${ticket.idTicket}">
                        <button class="btn btn-group-lg
                            <c:out value="${ticket.status == 'A' ? 'btn-danger' :'btn-success'}"/>"
                            ${ticket.status == 'I' ? 'disabled' : ''}
                        >
                        <c:out value="${ticket.status  == 'A' ? 'Finalizar' :'Ticket encerrado'}"/>
                    </button>
                    </a>
                </td>
                <td align="center" ${ticket.status == 'I' ? 'style="display: none;': ''}>
                    <a href="${pageContext.request.contextPath}/TicketCarregar?idticket=${ticket.idTicket}"><i class="bx bx-edit-alt iconec"></i></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
          


    <div class="modal fade" id="staticBackdrop4" tabindex="-1" aria-labelledby="exampleModalLabel4" aria-hidden="true">
        <div class="modal-dialog d-flex justify-content-center">
            <div class="modal-content w-100">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel4">Cadastro de ticket</h5>
                    <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body p-4 d-flex">
                <div class="card-body">
                    <div class="form-group">
                        <center>
                            <img alt="imagem" class="img-thumbnail" 
                                 src="${ticket.imagem}" 
                                
                                 name="target" id="target" width="250" heigth="250">
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
                    <form name="cadastrarticket" action="TicketCadastrar" method="POST">
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" class="form-control" name="idticket" id="idticket" value="${ticket.idTicket}" readonly="readonly" class="form-control" />
                            <label class="form-label" for="name4">ID</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="text" class="form-control" name="descricao" id="descricao" value="${ticket.descricao}" required class="form-control" />
                            <label class="form-label" for="name4">descricao do Problema</label>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="name4">Laboratorio:</label>
                            <select class="custom-select" name="idlaboratorio" id="idlaboratorio" required>
                                <option value="">Selecione</option>
                                <c:forEach var="laboratorio" items="${laboratorios}">
                                     <option value="${laboratorio.idLaboratorio}" ${maquina.laboratorio.idLaboratorio == laboratorio.idLaboratorio || idLaboratorio == laboratorio.idLaboratorio ? "selected": ""}>
                                          ${laboratorio.numeroSala}
                                     </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="name4">Docente</label>
                            <select class="custom-select" name="iddocente" id="iddocente" required>
                                <option value="">Selecione</option>
                                    <c:forEach var="docente" items="${docentes}">
                                        <option value="${docente.idDocente}" ${ticket.docente.idDocente == docente.idDocente ? "selected" : ""}>
                                            ${docente.nomePessoa}
                                        </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group row" style="display: none;">
                            <label for="idstatus" class="col-sm-2 col-form-label">status</label>
                            <select name="status" id="status" ${ticket.status == 'I' ? 'disabled': ''}>
                                <option value="A" ${ticket.status == 'A' ? 'selected' : ''}>Ativo</option>
                                <option value="I" ${ticket.status == 'I' ? 'selected' : ''}>Inativo</option>
                            </select>
                        </div>
                        <div class="d-flex justify-content-end gap-2">
                            <input class="btn btn-primary w-100"  type="submit" name="cadastrar" id="cadastrar" value="Cadastrar"/>
                            <input class="btn btn-danger" type="reset" name="limpar" id="limpar" value="Limpar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
                            
<!-- Modal para Exibir a Imagem Ampliada -->
<div class="modal fade" id="imageModal" tabindex="-1" aria-labelledby="imageModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        
            <div class="modal-body text-center">
                <img id="modalImage" src="" alt="Imagem ampliada" class="img-fluid" style="width: 400px; height: 400px; object-fit: contain;" />
            </div>
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
    
      #modalImage {
         width: 400px;
        height: 400px;
        object-fit: contain;
    }
</style>
                            
<script>
    window.onload = function () {
        const showModal = '<%= request.getAttribute("showModal") %>';
        if (showModal === 'true') {
            const myModal = new bootstrap.Modal(document.getElementById('staticBackdrop4'));
            myModal.show();
        }
    };
    
      document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form[name='cadastrarticket']");

    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Impede o envio padrão do formulário
        gravarDados();
    });
});


function gravarDados() {
    console.log("Gravando dados ....");
    const form = document.querySelector("form[name='cadastrarticket']");
    
    const data = {
        idticket: document.getElementById('idticket').value,
        descricao: document.getElementById('descricao').value,
        idlaboratorio: document.getElementById('idlaboratorio').value,
        iddocente: document.getElementById('iddocente').value,
        status: document.getElementById('status').value,
        imagem: document.getElementById('target').src,
    };

    $.ajax({
        type: 'post',
        url: 'TicketCadastrar',
        data: data,
        success: function (response) {
            console.log("Resposta do servlet ->", response);
            if (response == 1) {
                console.log("Dados enviados com sucesso");
                window.location.href = `${pageContext.request.contextPath}/TicketListar`;
            } else {
                console.log("Falha ao enviar os dados");
                window.location.href = `${pageContext.request.contextPath}/TicketListar`;
            }
        },
        error: function () {
            console.error("Erro ao enviar os dados");
            window.location.href = `${pageContext.request.contextPath}/TicketListar`;
        }
    });
}

  document.addEventListener("DOMContentLoaded", function () {
        // Adiciona evento de clique nas células com imagens
        const imageCells = document.querySelectorAll("td img");

        imageCells.forEach(function (img) {
            img.addEventListener("click", function () {
                // Configura a imagem no modal
                const modalImage = document.getElementById("modalImage");
                modalImage.src = img.src;

                // Mostra o modal
                const imageModal = new bootstrap.Modal(document.getElementById("imageModal"));
                imageModal.show();
            });
        });
    });

</script>

        
<%@include file="/footer.jsp" %>