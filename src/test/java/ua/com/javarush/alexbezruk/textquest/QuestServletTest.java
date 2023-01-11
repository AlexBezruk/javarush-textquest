package ua.com.javarush.alexbezruk.textquest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.javarush.alexbezruk.textquest.data.Question;
import ua.com.javarush.alexbezruk.textquest.service.QuestService;

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
class QuestServletTest {
    private static final String path = "/WEB-INF/quest.jsp";

    private QuestServlet questServlet;

    @Mock
    ServletConfig servletConfig;

    @Mock
    ServletContext servletContext;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    QuestService questService;

    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    Question question;

    @Mock
    Question currentQuestion;

    @BeforeEach
    void setup() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(path)).thenReturn(requestDispatcher);
        when(servletContext.getAttribute(eq("questService"))).thenReturn(questService);

        questServlet = new QuestServlet();
        questServlet.init(servletConfig);
    }

    @Test
    void test_doPost_numberAnswerIsNull() throws ServletException, IOException {
        when(request.getParameter("numberAnswer")).thenReturn(null);
        questServlet.doPost(request, response);

        verify(request, never()).getSession();
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doPost_notWinAndNotLoose() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("currentQuestion")).thenReturn(question);
        when(request.getParameter("numberAnswer")).thenReturn("1");
        when(questService.nextQuestion(question, "1")).thenReturn(currentQuestion);

        questServlet.doPost(request, response);

        verify(session, times(1)).setAttribute("currentQuestion", currentQuestion);
        verify(session, never()).getAttribute("name");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doPost_Win() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("currentQuestion")).thenReturn(question);
        when(request.getParameter("numberAnswer")).thenReturn("1");
        when(questService.nextQuestion(question, "1")).thenReturn(currentQuestion);

        when(currentQuestion.isWin()).thenReturn(true);
        when(session.getAttribute("name")).thenReturn("Sasha");
        when(questService.upgradeStatistics("Sasha")).thenReturn(3);

        questServlet.doPost(request, response);

        verify(session, times(1)).setAttribute("currentQuestion", currentQuestion);
        verify(session, times(1)).setAttribute("count", 3);
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doPost_Loose() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("currentQuestion")).thenReturn(question);
        when(request.getParameter("numberAnswer")).thenReturn("1");
        when(questService.nextQuestion(question, "1")).thenReturn(currentQuestion);

        when(currentQuestion.isLoose()).thenReturn(true);
        when(session.getAttribute("name")).thenReturn("Sasha");
        when(questService.upgradeStatistics("Sasha")).thenReturn(3);

        questServlet.doPost(request, response);

        verify(session, times(1)).setAttribute("currentQuestion", currentQuestion);
        verify(session, times(1)).setAttribute("count", 3);
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
