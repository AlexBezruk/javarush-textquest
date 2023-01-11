package ua.com.javarush.alexbezruk.textquest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndexServletTest {
    private static final String path = "/WEB-INF/index.jsp";

    private IndexServlet indexServlet;

    @Mock
    ServletConfig servletConfig;

    @Mock
    ServletContext servletContext;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @BeforeEach
    void setup() throws ServletException {
        indexServlet = new IndexServlet();
        indexServlet.init(servletConfig);
    }

    @Test
    void test_doGet() throws ServletException, IOException {
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(path)).thenReturn(requestDispatcher);

        indexServlet.doGet(request, response);

        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
