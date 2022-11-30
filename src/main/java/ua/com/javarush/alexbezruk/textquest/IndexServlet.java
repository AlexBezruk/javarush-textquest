package ua.com.javarush.alexbezruk.textquest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "indexServlet", value = "")
public class IndexServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("start index.jsp");
        getServletContext()
                .getRequestDispatcher("/WEB-INF/index.jsp")
                .forward(request, response);
    }
}
