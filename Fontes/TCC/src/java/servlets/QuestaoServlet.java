
package servlets;

import daos.questao.QuestaoDao;
import daos.questao.QuestaoDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Questao;


@WebServlet(name = "QuestaoServlet", urlPatterns = {"/QuestaoServlet"})
public class QuestaoServlet extends HttpServlet {
    
    QuestaoDao QuestaoDao = new QuestaoDaoImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         
            Questao questao = new Questao();
            HttpSession session = request.getSession(true);
            String acao = request.getParameter("acao");
            
            if (acao == null) redirectQuestoes(request, response);            
        }
    }
    
    private void redirectQuestoes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        List<Questao> questoes = QuestaoDao.Listar();
//        request.setAttribute("questoes", questoes);
        request.getRequestDispatcher("/ListaQuestoes.jsp").forward(request, response);
    }
    
    private void redirectQuestaoCadastro(HttpServletRequest request, HttpServletResponse response, Questao questao)
            throws ServletException, IOException {
        request.setAttribute("questao", questao);
        request.getRequestDispatcher("/FormQuestao.jsp").forward(request, response);
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
