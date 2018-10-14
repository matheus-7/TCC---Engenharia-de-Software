
package servlets;

import daos.usuario.UsuarioDao;
import daos.usuario.UsuarioDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Usuario;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    UsuarioDao UsuarioDao = new UsuarioDaoImpl();
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Usuario usuario = new Usuario();
            HttpSession session = request.getSession(true);
            String acao = request.getParameter("acao");
            
            if (acao == null) {
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                usuario = UsuarioDao.Selecionar(email, senha);
                
                if (usuario.getId() == 0) {
                    request.setAttribute("erro", "Usuário ou senha incorretos!");
                    redirectLogin(request, response);
                    return;
                }
                else if (!usuario.getDireito().equals("Administrador")){
                    request.setAttribute("erro", "Este usuário não possui permissão para acessar o painel admnistrativo!");
                    redirectLogin(request, response);
                    return;
                }
                else if (!usuario.getAtivo()){
                    request.setAttribute("erro", "O usuário informado está inativo!");
                    redirectLogin(request, response);
                    return;
                }
                else{
                    session.setAttribute("acesso", "Permitido");
                    redirect(request, response);
                }
            }          
        }
    }
    
    private void redirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Principal.html");
    }
    
    private void redirectLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
