
package servlets;

import daos.area.AreaDao;
import daos.area.AreaDaoImpl;
import daos.questao.QuestaoDao;
import daos.questao.QuestaoDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Alternativa;
import models.AreaDeConhecimento;
import models.Questao;
import models.Usuario;


@WebServlet(name = "QuestaoServlet", urlPatterns = {"/QuestaoServlet"})
public class QuestaoServlet extends HttpServlet {
    
    QuestaoDao QuestaoDao = new QuestaoDaoImpl();
    AreaDao AreaDao = new AreaDaoImpl();
    
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
            
            
            Questao questao = new Questao();
            String acao = request.getParameter("acao");
            
            if (acao == null) redirectQuestoes(request, response); 
            else if (acao.equals("novo")){
                session.setAttribute("questaoAnterior", new Questao());
                
                redirectQuestaoCadastro(request, response, questao);
            }
            else if (acao.equals("editar")){
                int id = Integer.parseInt(request.getParameter("id"));
                questao = QuestaoDao.Selecionar(id);

                session.setAttribute("questaoAnterior", questao);
                
                if (questao != null) {
                    redirectQuestaoCadastro(request, response, questao);
                } else {
                    redirectQuestoes(request, response);
                }
            }
            else if (acao.equals("salvar")){
                int id = 0;
                
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    id = 0;
                }
                
                String area = request.getParameter("area");
                Boolean ativo = request.getParameter("status").equals("Ativa");
                String descricao = request.getParameter("descricao");
                
                questao = new Questao(
                        id, 
                        descricao, 
                        ativo, 
                        AreaDao.Selecionar(Integer.parseInt(area)),
                        getAlternativas(request),
                        new Date(System.currentTimeMillis())
                );
                      
                try {
                    if (!validarQuestao(questao, request, response)) return;
                    
                    if (id != 0) QuestaoDao.Atualizar(questao);
                    else QuestaoDao.Inserir(questao);
                    
                    redirectQuestoes(request, response);
                } catch (Exception e) {
                    request.setAttribute("erro", "Não foi possível gravar esta questão!" + e.getMessage());
                    redirectQuestaoCadastro(request, response, questao);
                }
            }
        }
    }
    
    private List<Alternativa> getAlternativas (HttpServletRequest request){
        int id1 = 0;
        int id2 = 0;
        int id3 = 0;
        int id4 = 0;
        int id5 = 0;
        
        Boolean correta1 = false;
        Boolean correta2 = false;
        Boolean correta3 = false;
        Boolean correta4 = false;
        Boolean correta5 = false;
                                
        try { id1 = Integer.parseInt(request.getParameter("alternativa1Id"));} 
        catch (NumberFormatException e) { id1 = 0;}
        
        try { id2 = Integer.parseInt(request.getParameter("alternativa2Id"));} 
        catch (NumberFormatException e) { id2 = 0;}
        
        try { id3 = Integer.parseInt(request.getParameter("alternativa3Id"));} 
        catch (NumberFormatException e) { id3 = 0;}
        
        try { id4 = Integer.parseInt(request.getParameter("alternativa4Id"));} 
        catch (NumberFormatException e) { id4 = 0;}
        
        try { id5 = Integer.parseInt(request.getParameter("alternativa5Id"));} 
        catch (NumberFormatException e) { id5 = 0;}
        
        String desc1 = request.getParameter("alternativa1");
        String desc2 = request.getParameter("alternativa2");
        String desc3 = request.getParameter("alternativa3");
        String desc4 = request.getParameter("alternativa4");
        String desc5 = request.getParameter("alternativa5");
        
        try { correta1 = request.getParameter("correta").equals("correta1");} 
        catch (Exception e) { }
        
        try { correta2 = request.getParameter("correta").equals("correta2");} 
        catch (Exception e) { }
        
        try { correta3 = request.getParameter("correta").equals("correta3");} 
        catch (Exception e) { }
        
        try { correta4 = request.getParameter("correta").equals("correta4");} 
        catch (Exception e) { }
        
        try { correta5 = request.getParameter("correta").equals("correta5");} 
        catch (Exception e) { }
        
        Alternativa alternativa1 = new Alternativa(id1, desc1, correta1, null);
        Alternativa alternativa2 = new Alternativa(id2, desc2, correta2, null);
        Alternativa alternativa3 = new Alternativa(id3, desc3, correta3, null);
        Alternativa alternativa4 = new Alternativa(id4, desc4, correta4, null);
        Alternativa alternativa5 = new Alternativa(id5, desc5, correta5, null);
        
        List<Alternativa> alternativas = new ArrayList();
        
        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(alternativa5);
        
        return alternativas;
    }
    
    private void redirectQuestoes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Questao> questoes = QuestaoDao.Listar();
        request.setAttribute("questoes", questoes);
        request.getRequestDispatcher("/ListaQuestoes.jsp").forward(request, response);
    }
    
    private void redirectQuestaoCadastro(HttpServletRequest request, HttpServletResponse response, Questao questao)
            throws ServletException, IOException {
        List<AreaDeConhecimento> areas = AreaDao.Listar();
                
        request.setAttribute("areas", areas);
        request.setAttribute("questao", questao);
        
        request.getRequestDispatcher("/FormQuestao.jsp").forward(request, response);
    }
    
    private boolean validarQuestao(Questao questao, HttpServletRequest request, HttpServletResponse response){
        try {
            Boolean possuiRespostaCorreta = false;
            Boolean possuiDescEmBranco = false;
            
            for (Alternativa alternativa : questao.getAlternativas()){
                if (alternativa.getCorreta()) possuiRespostaCorreta = true;
                if (alternativa.getDescricao() == null || alternativa.getDescricao().equals("")) possuiDescEmBranco = true;
            }
            
            if (!possuiRespostaCorreta){
                request.setAttribute("erro", "A marcação de uma alternativa como correta é obrigatória");
                redirectQuestaoCadastro(request, response, questao);
                return false;
            }
            
            if (possuiDescEmBranco){
                request.setAttribute("erro", "O preenchimento de todos os campos é obrigatório");
                redirectQuestaoCadastro(request, response, questao);
                return false;
            }
            
            if (questao.getDescricao() == null || questao.getDescricao().equals("")){
                request.setAttribute("erro", "O preenchimento de todos os campos é obrigatório");
                redirectQuestaoCadastro(request, response, questao);
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
