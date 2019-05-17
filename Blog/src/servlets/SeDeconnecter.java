package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SeDeconnecter")
public class SeDeconnecter extends HttpServlet {

    public static final Logger logger = LogManager.getLogger(TestServlet.class);
    public static String VUE ="/web/index.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Page de déconnexion");
        HttpSession session = request.getSession();
        session.invalidate();
        logger.info("Renvoi vers la page de connexion");
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
