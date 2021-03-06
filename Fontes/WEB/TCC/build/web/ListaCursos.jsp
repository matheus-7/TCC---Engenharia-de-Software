
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
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
                            <h1 class="title">Lista de Cursos</h1>
                        </td>
                        <td style="text-align: right">
                            <a href="FormCurso.jsp" class="button is-primary">
                                <span class="icon is-small">
                                    <i class="fas fa-plus"></i>
                                </span>
                                <span>Novo Curso</span>
                            </a>
                        </td>
                    </tr>
                </table>        
            </div>
            <div class="container">
                <div class="field">
                    <div class="control">
                        <input class="input" type="text" placeholder="Palavra-chave" id="tbPalavraChave" onkeyup="buscar(this, 'tbCursos')">
                    </div>
                </div>
                
                <label class="label help is-danger">${erro}</label>
                
                <table class="table is-fullwidth" id="tbCursos">
                    <thead>
                        <tr id="header"> 
                            <th style="width: 10%">
                                ID
                            </th>
                            <th style="width: 90%">
                                Nome
                            </th>
                            <th/>
                            <th/>
                        </tr>
                    </thead>
                    
                    <tbody>
                        
                        <c:forEach var="curso" items="${cursos}" varStatus="inf">
                            <tr>
                                <td>
                                    ${curso.getId()}
                                </td>
                                <td>
                                    ${curso.getNome()}
                                </td>
                                <td>
                                    <a href="CursoServlet?acao=editar&id=${curso.getId()}" class="button is-success is-outlined">
                                        <span class="icon is-small">
                                            <i class="fas fa-pencil-alt"></i>
                                        </span>
                                    </a>
                                </td>
                                <td>
                                    <a href="CursoServlet?acao=excluir&id=${curso.getId()}" class="button is-danger is-outlined">
                                        <span class="icon is-small">
                                            <i class="fas fa-trash-alt"></i>
                                        </span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    
                    </tbody>
                </table>
            </div>
        </section>
        
    </body>
</html>
