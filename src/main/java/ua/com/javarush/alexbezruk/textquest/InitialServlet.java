package ua.com.javarush.alexbezruk.textquest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.com.javarush.alexbezruk.textquest.data.Quest;
import ua.com.javarush.alexbezruk.textquest.data.Question;
import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.data.UserRepository;

@WebServlet(name = "initialServlet", value = "/initial")
public class InitialServlet extends HttpServlet {
    private UserRepository userRepository;
    private Question currentQuestion;

    @Override
    public void init() {
        userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
        currentQuestion = new Quest().getInitialQuestion();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession currentSession = request.getSession();
        currentSession.setAttribute("currentQuestion", currentQuestion);
        User user;

        String name = request.getParameter("name");

        if (userRepository.isExists(name)) {
            user = userRepository.fetchByName(name);
        } else {
            user = new User(name, 0);
            userRepository.save(user);
        }

        currentSession.setAttribute("name", name);
        currentSession.setAttribute("count", user.getGamesNumber());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/quest.jsp")
                .forward(request, response);
    }
}
