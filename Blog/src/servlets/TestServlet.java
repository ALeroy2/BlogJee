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

@WebServlet(name = "TestServlet")
public class TestServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(TestServlet.class);
    public static final String ATT_NOM = "nom";
    public static final String ATT_PASS = "motdepasse";
    public static final String VUE ="/web/test.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        logger.info("Page de test");
        HttpSession session = request.getSession();
        if(session.getAttribute(ATT_NOM)==null){
            logger.info("Utilisateur non connecte, redirection en cours");
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }

    }
}
