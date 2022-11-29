package ua.com.javarush.alexbezruk.textquest;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.com.javarush.alexbezruk.textquest.data.Quest;
import ua.com.javarush.alexbezruk.textquest.data.Question;
import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.service.InitialService;

@WebServlet(name = "initialServlet", value = "/initial")
public class InitialServlet extends HttpServlet {
    private Question currentQuestion;
    private InitialService initialService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initialService = (InitialService) config.getServletContext().getAttribute("initialService");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession currentSession = request.getSession();

        currentQuestion = initialService.getInitialQuestion();
        currentSession.setAttribute("currentQuestion", currentQuestion);

        String name = request.getParameter("name");
        User user = initialService.initOrCreateUser(name);

        currentSession.setAttribute("name", name);
        currentSession.setAttribute("count", user.getGamesNumber());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/quest.jsp")
                .forward(request, response);
    }
}
