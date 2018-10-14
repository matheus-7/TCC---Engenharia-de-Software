
package servlets;

import daos.curso.CursoDao;
import daos.curso.CursoDaoImpl;
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
import models.Curso;

@WebServlet(name = "CursoServlet", urlPatterns = {"/CursoServlet"})
public class CursoServlet extends HttpServlet {

    CursoDao CursoDao = new CursoDaoImpl();
    
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
            
            
            Curso curso = new Curso();
            
            String acao = request.getParameter("acao");

            if (acao == null) redirectCursos(request, response);
            else if (acao.equals("salvar")){
                int id = 0;
                
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    id = 0;
                }
                
                String nome = request.getParameter("nome");
   
                curso = new Curso(id, nome, new Date(System.currentTimeMillis()));
                    
                try {
                    if (!validarCurso(curso, request, response)) return;
                    
                    if (!CursoDao.Existe(curso)){
                        if (id != 0) CursoDao.Atualizar(curso);
                        else CursoDao.Inserir(curso);
                    }
                    else{
                        request.setAttribute("erro", "O curso de " + curso.getNome() +  " já possui registro no banco de dados!");
                        redirectCursoCadastro(request, response, curso);
                    }
                    
                    redirectCursos(request, response);
                } catch (Exception e) {
                    request.setAttribute("erro", "Não foi possível gravar este curso!");
                    redirectCursoCadastro(request, response, curso);
                }
            }
            else if (acao.equals("excluir")){
                int id = Integer.parseInt(request.getParameter("id"));
                
                try {
                    CursoDao.Excluir(id);
                    redirectCursos(request, response);
                } catch (Exception e) {
                    request.setAttribute("erro", "O curso selecionado está sendo usado em outros cadastros e não pode ser excluído!");
                    redirectCursos(request, response);
                }
            }
            else if (acao.equals("editar")){
                int id = Integer.parseInt(request.getParameter("id"));
                curso = CursoDao.Selecionar(id);
                if (curso != null) {
                    request.setAttribute("curso", curso);
                    request.getRequestDispatcher("/FormCurso.jsp").forward(request, response);
                } else {
                    redirectCursos(request, response);
                }
            }   
        }        
    }
    
    private void redirectCursos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Curso> cursos = CursoDao.Listar();
        request.setAttribute("cursos", cursos);
        request.getRequestDispatcher("/ListaCursos.jsp").forward(request, response);
    }
    
    private void redirectCursoCadastro(HttpServletRequest request, HttpServletResponse response, Curso curso)
            throws ServletException, IOException {
        request.setAttribute("curso", curso);
        request.getRequestDispatcher("/FormCurso.jsp").forward(request, response);
    }
    
    private boolean validarCurso(Curso curso, HttpServletRequest request, HttpServletResponse response){
        try {
            if (curso.getNome() == null || curso.getNome() == ""){
                request.setAttribute("erro", "O preenchimento do campo 'Curso' é obrigatório");
                redirectCursoCadastro(request, response, curso);
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
