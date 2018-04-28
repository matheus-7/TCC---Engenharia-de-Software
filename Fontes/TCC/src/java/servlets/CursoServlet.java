
package servlets;

import daos.curso.CursoDao;
import daos.curso.CursoDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Curso;

@WebServlet(name = "CursoServlet", urlPatterns = {"/CursoServlet"})
public class CursoServlet extends HttpServlet {

    CursoDao CursoDao = new CursoDaoImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Curso curso = new Curso();
            
            String acao = request.getParameter("acao");

            if (acao == null) redirectCursos(request, response);
//            else if (acao.equals("salvar")){
//                int id = 0;
//                
//                try {
//                    id = Integer.parseInt(request.getParameter("id"));
//                } catch (NumberFormatException e) {
//                    id = 0;
//                }
//                
//                String cpf = request.getParameter("cpf");
//                String nome = request.getParameter("nome");
//                String sobrenome = request.getParameter("sobrenome");
//
//                cliente = new Cliente(id, cpf, nome, sobrenome);
//                    
//                try {
//                    if (id != 0) {
//                        clienteDao.atualizar(cliente);
//                    } else {
//                        clienteDao.criar(cliente);
//                    }
//                    redirectClientes(request, response);
//                } catch (Exception e) {
//                    request.setAttribute("erro", "Não foi possível gravar este cliente");
//                    redirectClientesCadastro(request, response);
//                }
//            }
//            else if (acao.equals("remover")){
//                String cpf = request.getParameter("cpf");
//                
//                try {
//                    clienteDao.removerPorCpf(cpf);
//                    redirectClientes(request, response);
//                } catch (Exception e) {
//                    request.setAttribute("erro", "Não foi possível remover este cliente");
//                    redirectClientes(request, response);
//                }
//            }
//            else if (acao.equals("editar")){
//                int id = Integer.parseInt(request.getParameter("id"));
//                cliente = clienteDao.consultarPorId(id);
//                if (cliente != null) {
//                    request.setAttribute("cliente", cliente);
//                    request.getRequestDispatcher("/cliente-form.jsp").forward(request, response);
//                } else {
//                    redirectClientes(request, response);
//                }
//            }   
        }        
    }
    
    private void redirectCursos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Curso> cursos = CursoDao.Listar();
        request.setAttribute("cursos", cursos);
        request.getRequestDispatcher("/ListaCursos.jsp").forward(request, response);
    }
    
    private void redirectClientesCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/CadCursos.jsp").forward(request, response);
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
