package ua.com.javarush.alexbezruk.textquest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.com.javarush.alexbezruk.textquest.data.Question;
import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.data.UserRepository;

@WebServlet(name = "questServlet", value = "/quest")
public class QuestServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession currentSession = request.getSession();
        if (request.getParameter("numberAnswer") != null) {
            int number = Integer.parseInt(request.getParameter("numberAnswer"));
            Question currentQuestion = (Question)currentSession.getAttribute("currentQuestion");
            currentQuestion = (currentQuestion.getAnswers().get(number)).getNextQuestion();
            currentSession.setAttribute("currentQuestion", currentQuestion);
            if (currentQuestion.isWin() || currentQuestion.isLoose()) {
                int count = (Integer)currentSession.getAttribute("count");
                currentSession.setAttribute("count", count + 1);

                String name = (String) currentSession.getAttribute("name");
                UserRepository userRepository = (UserRepository) getServletContext().getAttribute("userRepository");

                User user = userRepository.fetchByName(name);
                user.setGamesNumber((Integer) currentSession.getAttribute("count"));
                userRepository.save(user);
            }
        }

        getServletContext()
                .getRequestDispatcher("/WEB-INF/quest.jsp")
                .forward(request, response);
    }
}
