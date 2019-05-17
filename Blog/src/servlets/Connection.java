package servlets;

import Bean.Utilisateur;
import Dao.HSQLdb.UtilisateurDao;
import listener.StartUpListerner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Connection")
public class Connection extends HttpServlet {

    public static StartUpListerner startUpListerner = new StartUpListerner();
    public static final Logger logger = LogManager.getLogger(Connection.class);
    public static UtilisateurDao monUtilisateurDao = new UtilisateurDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Connexion de l'utilisateur");
        Utilisateur utilisateur = monUtilisateurDao.getUtilisateur(request.getParameter("email"));
        HttpSession session = request.getSession();
        if(utilisateur.getPassword() != null && utilisateur.getPassword().equals(request.getParameter("password"))){
            logger.info("Connexion réussi, redirection en cours");
            session.setAttribute("email", request.getParameter("email"));
            response.sendRedirect(request.getContextPath()+"/TestServlet");
        }else{
            logger.info("connexion down, veuillez essayer de nouoveau");
            response.sendRedirect(request.getContextPath()+"/Connexion");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Get Connection");
        HttpSession session = request.getSession();
        if(session.getAttribute("email") != null){
            logger.info("Utilisateur deja connecte, redirection en cours");
            response.sendRedirect(request.getContextPath()+"/Index");
        }else{
            logger.info("Page de connexion");
            request.getRequestDispatcher("/web/index.jsp").forward(request, response);
        }


    }

}
