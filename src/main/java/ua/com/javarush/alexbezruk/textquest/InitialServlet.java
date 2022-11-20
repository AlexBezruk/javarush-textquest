package ua.com.javarush.alexbezruk.textquest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.com.javarush.alexbezruk.textquest.data.Quest;
import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.data.UserRepository;

@WebServlet(name = "initialServlet", value = "/initial")
public class InitialServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession currentSession = request.getSession(true);
        currentSession.setAttribute("currentQuestion", (new Quest()).getInitialQuestion());
        User user;

        String name = request.getParameter("name");
        UserRepository userRepository = (UserRepository) getServletContext().getAttribute("userRepository");

        if (!userRepository.isExists(name)) {
            user = new User(name, 0);
            userRepository.save(user);
        } else {
            user = userRepository.fetchByName(name);
        }

        currentSession.setAttribute("name", name);
        currentSession.setAttribute("count", user.getGamesNumber());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/quest.jsp")
                .forward(request, response);
    }
}
