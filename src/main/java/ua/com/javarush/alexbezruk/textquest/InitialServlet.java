package ua.com.javarush.alexbezruk.textquest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.com.javarush.alexbezruk.textquest.data.Quest;

@WebServlet(name = "initialServlet", value = "/initial")
public class InitialServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession currentSession = request.getSession(true);
        currentSession.setAttribute("currentQuestion", (new Quest()).getInitialQuestion());
        currentSession.setAttribute("name", request.getParameter("name"));
        currentSession.setAttribute("ip", request.getRemoteAddr());
        if (currentSession.getAttribute("count") == null) {
            currentSession.setAttribute("count", 0);
        }

        getServletContext()
                .getRequestDispatcher("/WEB-INF/quest.jsp")
                .forward(request, response);
    }
}
