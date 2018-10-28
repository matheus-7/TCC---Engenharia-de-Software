<!DOCTYPE html>

<html class="has-navbar-fixed-top">
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
        
        <section class="section">
            <div class="container is-fluid" style="padding-top: 20px; padding-bottom: 20px;">
                <table style="width: 100%">
                    <tr>
                        <td>
                            <h1 class="title">Sistema de Preparação para o ENEM</h1>
                            <h1 class="title">Painel Administrativo - Login</h1>
                        </td>
                    </tr>
                </table>        
            </div>
            
            <div class="container is-fluid">
                
                <label class="label help is-danger">${erro}</label>
                                                
                <form method="POST" action="LoginServlet" accept-charset="iso-8859-1,utf-8">
                    <div class="field">
                        <table class="table is-fullwidth">
                            <tbody>
                                <tr>
                                    <td style="width: 50%">
                                        <label class="label">E-mail:</label>
                                        <div class="control">
                                            <input name="email" class="input" type="email" placeholder="E-mail">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 50%">
                                        <label class="label">Senha:</label>
                                        <div class="control">
                                            <input name="senha" class="input" type="password" placeholder="Senha">
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>                  

                        <div class="buttons is-centered">
                            <button class="button is-success" type="submit">
                                <span class="icon is-small">
                                    <i class="fas fa-sign-in-alt"></i>
                                </span>
                                <span>Login</span>
                            </button>
                        </div>
                    </div>
                </form>
                
            </div>
        </section>

    </body>    
</html>


    