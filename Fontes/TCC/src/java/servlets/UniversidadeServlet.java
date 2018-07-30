
package servlets;

import daos.universidade.UniversidadeDao;
import daos.universidade.UniversidadeDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Universidade;

@WebServlet(name = "UniversidadeServlet", urlPatterns = {"/UniversidadeServlet"})
public class UniversidadeServlet extends HttpServlet {

    UniversidadeDao UniversidadeDao = new UniversidadeDaoImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Universidade universidade = new Universidade();
            
            String acao = request.getParameter("acao");

            if (acao == null) redirectUniversidades(request, response);
//            else if (acao.equals("salvar")){
//                int id = 0;
//                
//                try {
//                    id = Integer.parseInt(request.getParameter("id"));
//                } catch (NumberFormatException e) {
//                    id = 0;
//                }
//                
//                String nome = request.getParameter("nome");
//   
//                curso = new Curso(id, nome, new Date(System.currentTimeMillis()));
//                    
//                try {
//                    if (!validarCurso(curso, request, response)) return;
//                    
//                    if (!CursoDao.Existe(curso)){
//                        if (id != 0) CursoDao.Atualizar(curso);
//                        else CursoDao.Inserir(curso);
//                    }
//                    else{
//                        request.setAttribute("erro", "O curso de " + curso.getNome() +  " já possui registro no banco de dados!");
//                        redirectCursoCadastro(request, response, curso);
//                    }
//                    
//                    redirectCursos(request, response);
//                } catch (Exception e) {
//                    request.setAttribute("erro", "Não foi possível gravar este curso!");
//                    redirectCursoCadastro(request, response, curso);
//                }
//            }
//            else if (acao.equals("excluir")){
//                int id = Integer.parseInt(request.getParameter("id"));
//                
//                try {
//                    CursoDao.Excluir(id);
//                    redirectCursos(request, response);
//                } catch (Exception e) {
//                    request.setAttribute("erro", "O curso selecionado está sendo usado em outros cadastros e não pode ser excluído!");
//                    redirectCursos(request, response);
//                }
//            }
//            else if (acao.equals("editar")){
//                int id = Integer.parseInt(request.getParameter("id"));
//                curso = CursoDao.Selecionar(id);
//                if (curso != null) {
//                    request.setAttribute("curso", curso);
//                    request.getRequestDispatcher("/FormCurso.jsp").forward(request, response);
//                } else {
//                    redirectCursos(request, response);
//                }
//            }
        }
    }
    
    private void redirectUniversidades(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Universidade> universidades = UniversidadeDao.Listar();
        request.setAttribute("universidades", universidades);
        request.getRequestDispatcher("/ListaUniversidades.jsp").forward(request, response);
    }
    
    private void redirectUniversidadeCadastro(HttpServletRequest request, HttpServletResponse response, Universidade universidade)
            throws ServletException, IOException {
        request.setAttribute("universidade", universidade);
        request.getRequestDispatcher("/FormUniversidade.jsp").forward(request, response);
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
