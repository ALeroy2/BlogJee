package servlets;

import Bean.Utilisateur;
import Dao.HSQLdb.UtilisateurDao;
import listener.StartUpListerner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "Inscription")
public class Inscription extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final Logger logger = LogManager.getLogger(Inscription.class);
    public static StartUpListerner monStartupListener = new StartUpListerner();
    public static UtilisateurDao myUserDao = new UtilisateurDao();

    public static final String VUE = " /web/index.jsp";

    protected void doPost(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Récupération des champs du formulaire. */
        logger.info("POST Inscription");
        String email = request.getParameter( "email" );
        String motDePasse = request.getParameter( "password" );
        String nom = request.getParameter("nom");
        Date dateCreation = new Date(2019,05,17);
        Boolean admin = false;

        if(!email.isEmpty() && !nom.isEmpty() && !motDePasse.isEmpty()){
            try {
              myUserDao.createUtilisateur(new Utilisateur(email,nom,motDePasse,dateCreation,admin));
              logger.info("Utilisateur cree");
              response.sendRedirect(request.getContextPath()+"/Index");
            } catch (SQLException e) {
                logger.info("erreur utilisateur non cree");
                response.sendRedirect(request.getContextPath()+"/Inscription");
            }

            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect(request.getContextPath()+"/Home");
        }else{
            logger.info("Formulaire non valide / utilisateur non cree");
            response.sendRedirect(request.getContextPath()+"/Inscription");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

       logger.info("Get Inscription");
       request.getRequestDispatcher("/web/index.jsp").forward(request,response);

    }



}
