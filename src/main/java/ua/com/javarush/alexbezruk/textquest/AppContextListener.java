package ua.com.javarush.alexbezruk.textquest;

import ua.com.javarush.alexbezruk.textquest.data.Quest;
import ua.com.javarush.alexbezruk.textquest.repository.UserRepository;
import ua.com.javarush.alexbezruk.textquest.service.InitialService;
import ua.com.javarush.alexbezruk.textquest.service.QuestService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("initialQuestion", new Quest().getInitialQuestion());
        UserRepository userRepository = new UserRepository(new HashMap<>());
        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("initialService", new InitialService(userRepository));
        servletContext.setAttribute("questService", new QuestService(userRepository));
    }
}
