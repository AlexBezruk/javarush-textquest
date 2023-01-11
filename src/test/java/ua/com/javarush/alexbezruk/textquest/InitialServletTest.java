package ua.com.javarush.alexbezruk.textquest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.javarush.alexbezruk.textquest.data.Question;
import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.service.InitialService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InitialServletTest {
    private static final String path = "/WEB-INF/quest.jsp";

    private InitialServlet initialServlet;

    @Mock
    ServletConfig servletConfig;

    @Mock
    ServletContext servletContext;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    InitialService initialService;

    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    User user;

    @Mock
    Question question;

    @BeforeEach
    void setup() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(path)).thenReturn(requestDispatcher);
        when(servletContext.getAttribute("initialService")).thenReturn(initialService);
        when(servletContext.getAttribute("initialQuestion")).thenReturn(question);

        initialServlet = new InitialServlet();
        initialServlet.init(servletConfig);
    }

    @Test
    void test_doPost() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("name")).thenReturn("Sasha");
        when(initialService.initOrCreateUser("Sasha")).thenReturn(user);
        when(user.getGamesNumber()).thenReturn(3);

        initialServlet.doPost(request, response);

        verify(session, times(1)).setAttribute("currentQuestion", question);
        verify(session, times(1)).setAttribute("name", "Sasha");
        verify(session, times(1)).setAttribute("count", 3);
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
