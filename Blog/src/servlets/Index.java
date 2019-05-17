package servlets;

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

@WebServlet(name = "Index")
public class Index extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final Logger logger = LogManager.getLogger(Index.class);
    public static StartUpListerner monStartupListener = new StartUpListerner();

    public Index() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("GET Home");
        HttpSession session = request.getSession();
        if(session.getAttribute("mail") == null){
            logger.info("Utilisateur non connect�, redirection en cours");
            response.sendRedirect(request.getContextPath() + "/Login");
        } else {
            logger.info("Utilisateur connect�");
            request.getRequestDispatcher("./WEB-INF/Home.jsp").forward(request, response);
        }

    }
}
