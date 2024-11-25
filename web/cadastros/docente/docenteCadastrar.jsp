<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<form name="docenteCadastrar" action="DocenteCadastrar" method="POST">
    <div align="center">
        <table>
            <thead>
                <tr align="center">
                    <th colspan="2" >Cadastro de Docente</th>
                </tr>
                <tr>
                    <th colspan="2" align="center">${mensagem}</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>ID:</td>
                    <td><input type="text" name="iddocente" id="iddocente" value="${docente.idDocente}" readonly="readonly"/></td>
                </tr>
                <tr>
                    <td>Nome</td>
                    <td><input type="text" name="nomepessoa" id="nomepessoa" value="${docente.nomePessoa}" size="50" maxlength="50"/></td>
                </tr>
                <tr>
                    <td>Contato</td>
                    <td><input type="text" name="contato" id="contato" value="${docente.contato}" size="50" maxlength="50"/></td>
                </tr>
                <tr>
                    <td>numeroMaticula</td>
                    <td><input type="text" name="numeromatricula" id="numeromatricula" value="${docente.numeroMatricula}" size="50" maxlength="50"/></td>
                </tr>
                <tr>
                    <td>situacao</td>
                    <td><input type="hidden" name="situacao" id="situacao" value="${docente.situacao}" readonly="readonly"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" name="cadastrar" id="cadastrar" value="Cadastrar"/>
                        <input type="reset" name="limpar" id="limpar" value="Limpar"/>
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2"><h5><a href="index.jsp">Voltar A pagina inicial</a></h5></td>
                </tr>
            </tbody>
        </table>
    </div>

</form>

<%@include file="/footer.jsp" %>