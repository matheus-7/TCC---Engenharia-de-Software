
package servlets;

import daos.curso.CursoDao;
import daos.usuario.UsuarioDao;
import daos.usuario.UsuarioDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Usuario;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    UsuarioDao UsuarioDao = new UsuarioDaoImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession(true);
            String acesso = (String)session.getAttribute("acesso");
            
            if (acesso == null || !acesso.equals("Permitido")){
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }
            
            Usuario usuario = new Usuario();
            String acao = request.getParameter("acao");
            
            if (acao == null) redirectUsuarios(request, response);
            else if (acao.equals("novo")){
                session.setAttribute("usuarioAnterior", new Usuario());
                
                redirectUsuarioCadastro(request, response, usuario);
            }          
            else if (acao.equals("editar")){
                int id = Integer.parseInt(request.getParameter("id"));
                usuario = UsuarioDao.Selecionar(id);

                session.setAttribute("usuarioAnterior", usuario);
                
                if (usuario != null) {
                    redirectUsuarioCadastro(request, response, usuario);
                } else {
                    redirectUsuarios(request, response);
                }
            }
            else if (acao.equals("salvar")){
                int id = 0;
                
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    id = 0;
                }
                
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                String direito = request.getParameter("direito");
                Boolean ativo = request.getParameter("status").equals("Ativo");
                
                usuario = new Usuario(
                        id, 
                        nome, 
                        email, 
                        senha, 
                        direito, 
                        ativo, 
                        null, 
                        null, 
                        null, 
                        null, 
                        null, 
                        null, 
                        null,
                        new Date(System.currentTimeMillis()), 
                        null,
                        0
                );
                
                try {
                    if (!validarUsuario(usuario, request, response)) return;

                    Usuario usuarioAnterior = (Usuario)session.getAttribute("usuarioAnterior");
                    
                    int idAnterior = usuarioAnterior.getId();
                    
                    if (!UsuarioDao.Existe(usuario, idAnterior)){
                        if (id != 0) UsuarioDao.Atualizar(usuario);
                        else UsuarioDao.Inserir(usuario);
                    }
                    else{
                        request.setAttribute("erro", "O e-mail " + usuario.getEmail() +  " já possui registro no banco de dados!");
                        redirectUsuarioCadastro(request, response, usuario);
                    }
                    
                    redirectUsuarios(request, response);
                } catch (Exception e) {
                    request.setAttribute("erro", "Não foi possível gravar este usuário!" + e.getMessage());
                    redirectUsuarioCadastro(request, response, usuario);
                }
            }
        }
    }
    
    private void redirectUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> usuarios = UsuarioDao.Listar();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/ListaUsuarios.jsp").forward(request, response);
    }
    
    private void redirectUsuarioCadastro(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
            throws ServletException, IOException {
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/FormUsuario.jsp").forward(request, response);
    }
    
    private boolean validarUsuario(Usuario usuario, HttpServletRequest request, HttpServletResponse response){
        try {
            if (usuario.getNome() == null || usuario.getNome().equals("")){
                request.setAttribute("erro", "O preenchimento do campo 'Nome' é obrigatório");
                redirectUsuarioCadastro(request, response, usuario);
                return false;
            }
            else if (usuario.getEmail() == null || usuario.getEmail().equals("")){
                request.setAttribute("erro", "O preenchimento do campo 'E-mail' é obrigatório");
                redirectUsuarioCadastro(request, response, usuario);
                return false;
            }
            else if (usuario.getSenha() == null || usuario.getSenha().equals("")){
                request.setAttribute("erro", "O preenchimento do campo 'Senha' é obrigatório");
                redirectUsuarioCadastro(request, response, usuario);
                return false;
            }
            if (usuario.getDireito() == null || usuario.getDireito().equals("")){
                request.setAttribute("erro", "O preenchimento do campo 'Direito' é obrigatório");
                redirectUsuarioCadastro(request, response, usuario);
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
        
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
