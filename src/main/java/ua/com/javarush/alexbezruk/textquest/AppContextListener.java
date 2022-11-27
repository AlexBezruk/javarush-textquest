package ua.com.javarush.alexbezruk.textquest;

import ua.com.javarush.alexbezruk.textquest.data.UserRepository;
import ua.com.javarush.alexbezruk.textquest.service.InitialService;
import ua.com.javarush.alexbezruk.textquest.service.QuestService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        UserRepository userRepository = new UserRepository(null);
        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("initialService", new InitialService(userRepository));
        servletContext.setAttribute("questService", new QuestService(userRepository));
    }
}
