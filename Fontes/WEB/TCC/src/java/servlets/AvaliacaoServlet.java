
package servlets;

import daos.avaliacao.AvaliacaoDao;
import daos.avaliacao.AvaliacaoDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Avaliacao;


@WebServlet(name = "AvaliacaoServlet", urlPatterns = {"/AvaliacaoServlet"})
public class AvaliacaoServlet extends HttpServlet {

    AvaliacaoDao AvaliacaoDao = new AvaliacaoDaoImpl();
    
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
            
            Avaliacao avaliacao = new Avaliacao();
            
            String acao = request.getParameter("acao");   
            if (acao == null) redirectAvaliacoes(request, response);
        }
    }
    
    private void redirectAvaliacoes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Avaliacao> avaliacoes = AvaliacaoDao.Listar();
        request.setAttribute("avaliacoes", avaliacoes);
        request.getRequestDispatcher("/ListaAvaliacoes.jsp").forward(request, response);
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
