
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Painel Administrativo</title>
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
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
                    <a class="navbar-item" href="#">
                        Home
                    </a>
                    
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
                            <a class="navbar-item" href="AvaliacoesServlet">
                                Avaliações
                            </a>
                            <a class="navbar-item" href="RelatorioServlet">
                                Geral
                            </a>
                        </div>
                    </div>
                    
                    <a class="navbar-item" href="#">
                        Sair
                    </a>
                </div>
            </div>
        </nav>
        
        
        
        <section class="section">
            <div class="container" style="padding-top: 20px; padding-bottom: 20px;">
                <table style="width: 100%">
                    <tr>
                        <td>
                            <h1 class="title">Cadastro de Universidade</h1>
                        </td>
                    </tr>
                </table>        
            </div>
            
            <div class="container">
                
                <label class="label help is-danger">${erro}</label>
                                                
                <form method="POST" action="UniversidadeServlet?acao=salvar">
                    <div class="field">
                        <table class="table is-fullwidth">
                            <tbody>
                                <tr>
                                    <td style="width:60%">
                                        <label class="label">Nome:</label>
                                        <div class="control">
                                            <input name="nome" class="input" value="${universidade.getNome()}" type="text" placeholder="Nome">
                                        </div>
                                    </td>
                                    <td style="width:20%">
                                        <label class="label">Estado:</label>
                                        <div class="control">
                                            <div class="select">
                                                <select name="estado" id="estado">
                                                    <c:forEach var="estado" items="${estados}" varStatus="inf">    
                                                        <option value="${estado.getId()}" <c:if test="${estado.getId() == universidade.getCidade().getEstado().getId()}">selected</c:if>>${estado.getNome()}</option>
                                                    </c:forEach>    
                                                </select>
                                            </div>
                                        </div>
                                    </td>
                                    <td style="width:20%">
                                        <label class="label">Cidade:</label>
                                        <div class="control">
                                            <div class="select">
                                                <select name="cidade" id="cidade">
                                                    <c:forEach var="cidade" items="${cidades}" varStatus="inf">    
                                                        <option value="${cidade.getId()}" <c:if test="${cidade.getId() == universidade.getCidade().getId()}">selected</c:if>>${cidade.getNome()}</option>
                                                    </c:forEach>    
                                                </select>
                                            </div>    
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                                        
                        <input name="id" type="hidden" value="${universidade.getId()}" />     

                        <div class="buttons is-centered">
                            <button class="button is-success" type="submit">
                                <span class="icon is-small">
                                    <i class="fas fa-save"></i>
                                </span>
                                <span>Gravar</span>
                            </button>
                            <a href="UniversidadeServlet" class="button is-danger">
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
