
package servlets;

import daos.cidade.CidadeDao;
import daos.cidade.CidadeDaoImpl;
import daos.curso.CursoDao;
import daos.curso.CursoDaoImpl;
import daos.estado.EstadoDao;
import daos.estado.EstadoDaoImpl;
import daos.universidade.UniversidadeDao;
import daos.universidade.UniversidadeDaoImpl;
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
import models.Cidade;
import models.Curso;
import models.Estado;
import models.Universidade;

@WebServlet(name = "UniversidadeServlet", urlPatterns = {"/UniversidadeServlet"})
public class UniversidadeServlet extends HttpServlet {

    UniversidadeDao UniversidadeDao = new UniversidadeDaoImpl();
    CursoDao CursoDao = new CursoDaoImpl();
    CidadeDao CidadeDao = new CidadeDaoImpl();
    EstadoDao estadoDao = new EstadoDaoImpl();
    
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
            
            Universidade universidade = new Universidade();
            String acao = request.getParameter("acao");

            if (acao == null) redirectUniversidades(request, response);
            else if (acao.equals("novo")){
                session.setAttribute("universidadeAnterior", new Universidade());
                                
                redirectUniversidadeCadastro(request, response, universidade);
            }
            else if (acao.equals("filtrarCidades")){
                int idEstado = Integer.valueOf(request.getParameter("idEstado"));

                List<Cidade> cidades = CidadeDao.Listar(idEstado);
                                
                PrintWriter pw =  response.getWriter();
                
                for (Cidade cidade: cidades) {
                    pw.println("<option value='"+cidade.getId()+"'>");
                    pw.println(cidade.getNome());
                    pw.println("</option>");
                }
                
                pw.close();               
            }
            else if (acao.equals("salvar")){
                int id = 0;
                
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    id = 0;
                }
                
                String nome = request.getParameter("nome");
                int idCidade = Integer.parseInt(request.getParameter("cidade"));
                
                Cidade cidade = new Cidade();
                cidade = CidadeDao.Selecionar(idCidade);
                
                universidade = new Universidade(id, nome, cidade, null, new Date(System.currentTimeMillis()));
                
                try {
                    if (!validarUniversidade(universidade, request, response)) return;

                    Universidade universidadeAnterior = (Universidade)session.getAttribute("universidadeAnterior");
                    
                    int idAnterior = universidadeAnterior.getId();
                    
                    if (!UniversidadeDao.Existe(universidade, idAnterior)){
                        if (id != 0) UniversidadeDao.Atualizar(universidade);
                        else UniversidadeDao.Inserir(universidade);
                    }
                    else{
                        request.setAttribute("erro", "A universidade " + universidade.getNome() +  " já possui registro no banco de dados!");
                        redirectUniversidadeCadastro(request, response, universidade);
                    }
                    
                    redirectUniversidades(request, response);
                } catch (Exception e) {
                    request.setAttribute("erro", "Não foi possível gravar esta universidade!");
                    redirectUniversidadeCadastro(request, response, universidade);
                }
            }
            else if (acao.equals("excluir")){
                int id = Integer.parseInt(request.getParameter("id"));
                
                try {
                    UniversidadeDao.Excluir(id);
                    redirectUniversidades(request, response);
                } catch (Exception e) {
                    request.setAttribute("erro", "A universidade selecionada está sendo usada em outros cadastros e não pode ser excluída!");
                    redirectUniversidades(request, response);
                }
            }
            else if (acao.equals("editar")){
                int id = Integer.parseInt(request.getParameter("id"));
                universidade = UniversidadeDao.Selecionar(id);

                session.setAttribute("universidadeAnterior", universidade);
                
                if (universidade != null) {
                    redirectUniversidadeCadastro(request, response, universidade);
                } else {
                    redirectUniversidades(request, response);
                }
            }
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
        List<Estado> estados = estadoDao.Listar();
        List<Curso> cursos = CursoDao.Listar();
        
        request.setAttribute("cursos", cursos);        
        request.setAttribute("estados", estados);
        request.setAttribute("universidade", universidade);
        
        if (universidade.getId() == 0){
            request.setAttribute("cidades", estados.get(0).getCidades());
        }
        else{
            for (Estado estado : estados){
                if (estado.getId() == universidade.getCidade().getEstado().getId()){
                    request.setAttribute("cidades", estado.getCidades());
                }
            } 
        }
        
        request.getRequestDispatcher("/FormUniversidade.jsp").forward(request, response);
    }
    
    private boolean validarUniversidade(Universidade universidade, HttpServletRequest request, HttpServletResponse response){
        try {
            if (universidade.getNome() == null || universidade.getNome() == ""){
                request.setAttribute("erro", "O preenchimento de todos os campos é obrigatório");
                redirectUniversidadeCadastro(request, response, universidade);
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
