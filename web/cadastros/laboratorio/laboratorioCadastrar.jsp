<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

    <form name="LaboratorioCadastrar" action="LaboratorioCadastrar"  method="POST">
        <table align="center">
            <thead>
                <tr>
                    <th colspan="2" align="center">Cadastro de Laboratorio</th>
                </tr>
                <tr>
                    <th colspan="2" align="center">${mensagem}</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>ID:</td>
                    <td><input type="text" name="idlaboratorio" id="idlaboratorio" value="${laboratorio.idLaboratorio}" readonly="readonly"/></td>
                </tr>
                <tr>
                    <td>Numero Sala</td>
                    <td><input type="text" name="numerosala" id="numerosala" value="${laboratorio.numeroSala}" /></td>
                </tr>
                <tr>
                    <td>Quantidade de maquinas</td>
                    <td><input type="text" name="qtnmaquinas" id="qtnmaquinas" value="${laboratorio.qtnMaquinas}"/></td>
                </tr>
                <tr>
                    <td>Status Laboratório</td>
                    <td>
                        <select name="status" id="status">
                            <option value="A" ${laboratorio.status == 'A' ? 'selected' : ''}>Ativo</option>
                            <option value="I" ${laboratorio.status == 'I' ? 'selected' : ''}>Inativo</option>
                        </select>
                    </td>
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
    </form>

<%@include file="/footer.jsp" %>