package Controller;

import DAO.CategoriaDAO;
import DAO.CategoriaDAOImplementar;
import Model.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Categorias extends HttpServlet {

    protected void listaCategorias(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
   
        CategoriaDAO categoria = new CategoriaDAOImplementar();
        
        HttpSession session = request.getSession(true);
        session.setAttribute("lista", categoria.Listar()); 
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Vistas-Categorias/listarCategorias.jsp");
        dispatcher.forward(request, response);
        
    }
    
    protected void borrarCategoria(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Vistas-Categorias/listarCategorias.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parametro = request.getParameter("opcion");
        if(parametro.equals("crear")){
            String pagina = "/Vistas-Categorias/crearCategoria.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
            dispatcher.forward(request, response);
            
        }else if(parametro.equals("listar")){
            this.listaCategorias(request, response);
            
        }else if(parametro.equals("modificar")){
            int id_categoria = Integer.parseInt(request.getParameter("id_cat"));
            String nom_categoria = request.getParameter("nombre_cat");
            int estado_categoria = Integer.parseInt(request.getParameter("estado_cat"));
            
            String pagina = "/Vistas-Categorias/crearCategoria.jsp?id_c="+id_categoria+"&&nombre_c="+nom_categoria+"&&estado_c="+estado_categoria+"&&senal=1";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
            dispatcher.forward(request, response);
            
        }else if(parametro.equals("eliminar")){
            int del_id = Integer.parseInt(request.getParameter("id"));
            CategoriaDAO categoria = new CategoriaDAOImplementar();
            categoria.borrarCat(del_id);    
            this.listaCategorias(request, response);
        }
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Categoria categoria = new Categoria();
        int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
        String nom_categoria = request.getParameter("txtNomCategoria");
        int estado_categoria = Integer.parseInt(request.getParameter("txtEstadoCategoria"));
        
        categoria.setId_categoria(id_categoria);
        categoria.setNom_categoria(nom_categoria);
        categoria.setEstado_categoria(estado_categoria);
        
        CategoriaDAO guardarCategoria = new CategoriaDAOImplementar();
        guardarCategoria.guardarCat(categoria);
        
        this.listaCategorias(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }


}
