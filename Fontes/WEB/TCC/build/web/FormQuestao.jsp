
<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Painel Administrativo</title>
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="UTF-8">
        
        <link rel="stylesheet" href="bulma-0.7.0\css\bulma.min.css">
        <link rel="stylesheet" href="Jquery/jquery-ui.css" />
    
        <script defer="" src="js/images.js"></script>
        <script src="js/jquery-1.9.1.js" type="text/javascript"></script>
        <script src="js/funcoes.js" type="text/javascript"></script>
        
        <script src="Jquery/jquery-ui.js"></script>
        <script src="Jquery/jquery-ui.min.js"></script>
    </head>
    <body>
        <nav class="navbar is-primary is-fixed-top">
            <div class="navbar-brand">
                <a class="navbar-item" href="#">
                    <img src="#" alt="" width="112" height="28">
                </a>

                <div class="navbar-burger burger" data-target="navMenu" >
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
            
            <div class="navbar-menu" id="navMenu">
                <div class="navbar-start">
                    
                    <div class="navbar-item has-dropdown is-hoverable">
                        <a class="navbar-link" href="#">
                            Cadastros
                        </a>
                        <div class="navbar-dropdown is-boxed">
                            <a class="navbar-item" href="CursoServlet">
                                Cursos
                            </a>
                            <a class="navbar-item" href="QuestaoServlet">
                                Questões
                            </a>
                            <a class="navbar-item" href="UniversidadeServlet">
                                Universidades
                            </a>
                            <a class="navbar-item" href="UsuarioServlet">
                                Usuários
                            </a>
                        </div>
                    </div>
                    
                    <div class="navbar-item has-dropdown is-hoverable">
                        <a class="navbar-link" href="#">
                            Relatórios
                        </a>
                        <div class="navbar-dropdown is-boxed">
                            <a class="navbar-item" href="AvaliacaoServlet">
                                Avaliações
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </nav>
        
        
        
        <section class="section">
            <div class="container" style="padding-top: 20px; padding-bottom: 20px;">
                <table style="width: 100%">
                    <tr>
                        <td>
                            <h1 class="title">Cadastro de Questão</h1>
                        </td>
                    </tr>
                </table>        
            </div>
            
            <div class="container">
                
                <label class="label help is-danger">${erro}</label>
                                                
                <form method="POST" action="QuestaoServlet?acao=salvar" accept-charset="iso-8859-1,utf-8">
                    <div class="field">
                        <table class="table is-fullwidth">
                            <tbody>
                                <tr>
                                    <td style="width: 30%">
                                        <label class="label">Área de Conhecimento:</label>
                                        <div class="control">
                                            <div class="select">
                                                <select name="area" id="area">
                                                    <c:forEach var="area" items="${areas}" varStatus="inf">    
                                                        <option value="${area.getId()}" <c:if test="${area.getId() == questao.getAreaConhecimento().getId()}">selected</c:if>>${area.getNome()}</option>
                                                    </c:forEach>    
                                                </select>
                                            </div>
                                        </div>
                                    </td>
                                    <td style="width: 70%">
                                        <label class="label">Status:</label>
                                        <div class="control">
                                            <div class="select">
                                                <select name="status" id="status">
                                                    <option value="Ativa" <c:if test="${questao.getAtiva()}">selected</c:if>>Ativa</option> 
                                                    <option value="Inativa" <c:if test="${!questao.getAtiva()}">selected</c:if>>Inativa</option>
                                                </select>
                                            </div>    
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 100%" colspan="3">
                                        <label class="label">Descrição:</label>
                                        <div class="control">
                                            <textarea name="descricao" rows="2" class="textarea" placeholder="Descrição">${questao.getDescricao()}</textarea>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        
                        <label class="label">Alternativas:</label>                
                        <table class="table is-fullwidth">
                            <tbody>
                                <tr>
                                    <td style="width: 80%" >
                                        <div class="control">
                                            <input name="alternativa1Id" type="hidden" value="${questao.getAlternativas().get(0).getId()}" />
                                            <input name="alternativa1" class="input" value="${questao.getAlternativas().get(0).getDescricao()}" placeholder="Alternativa 1"></input>
                                        </div>
                                    </td>
                                    <td style="width: 20%">
                                        <label class="checkbox">
                                            <input type="radio" name="correta" value="correta1" <c:if test="${questao.getAlternativas().get(0).getCorreta()}">checked</c:if>>
                                            Alternativa Correta
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 80%" >
                                        <div class="control">
                                            <input name="alternativa2Id" type="hidden" value="${questao.getAlternativas().get(1).getId()}" />
                                            <input name="alternativa2" class="input" value="${questao.getAlternativas().get(1).getDescricao()}" placeholder="Alternativa 2"></input>
                                        </div>
                                    </td>
                                    <td style="width: 20%">
                                        <label class="checkbox">
                                            <input type="radio" name="correta" value="correta2" <c:if test="${questao.getAlternativas().get(1).getCorreta()}">checked</c:if>>
                                            Alternativa Correta
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 80%" >
                                        <div class="control">
                                            <input name="alternativa3Id" type="hidden" value="${questao.getAlternativas().get(2).getId()}" />
                                            <input name="alternativa3" class="input" value="${questao.getAlternativas().get(2).getDescricao()}" placeholder="Alternativa 3"></input>
                                        </div>
                                    </td>
                                    <td style="width: 20%">
                                        <label class="checkbox">
                                            <input type="radio" name="correta" value="correta3" <c:if test="${questao.getAlternativas().get(2).getCorreta()}">checked</c:if>>
                                            Alternativa Correta
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 80%" >
                                        <div class="control">
                                            <input name="alternativa4Id" type="hidden" value="${questao.getAlternativas().get(3).getId()}" />
                                            <input name="alternativa4" class="input" value="${questao.getAlternativas().get(3).getDescricao()}" placeholder="Alternativa 4"></input>
                                        </div>
                                    </td>
                                    <td style="width: 20%">
                                        <label class="checkbox">
                                            <input type="radio" name="correta" value="correta4" <c:if test="${questao.getAlternativas().get(3).getCorreta()}">checked</c:if>>
                                            Alternativa Correta
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 80%" >
                                        <div class="control">
                                            <input name="alternativa5Id" type="hidden" value="${questao.getAlternativas().get(4).getId()}" />
                                            <input name="alternativa5" class="input" value="${questao.getAlternativas().get(4).getDescricao()}" placeholder="Alternativa 5"></input>
                                        </div>
                                    </td>
                                    <td style="width: 20%">
                                        <label class="checkbox">
                                            <input type="radio" name="correta" value="correta5" <c:if test="${questao.getAlternativas().get(4).getCorreta()}">checked</c:if>>
                                            Alternativa Correta
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                                        
                        <input name="id" type="hidden" value="${questao.getId()}" />                    

                        <div class="buttons is-centered">
                            <button class="button is-success" type="submit">
                                <span class="icon is-small">
                                    <i class="fas fa-save"></i>
                                </span>
                                <span>Gravar</span>
                            </button>
                            <a href="QuestaoServlet" class="button is-danger">
                                <span class="icon is-small">
                                    <i class="fas fa-times circle"></i>
                                </span>
                                <span>Cancelar</span>
                            </a>
                        </div>
                    </div>
                </form>
                
            </div>
        </section>
    </body>
</html>
