package by.epam.finalTask.hr.controller;


import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.helper.CommandHelper;
import by.epam.finalTask.hr.dao.connectionpool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HttpServlet receives all data from view (JSP-pages) and invokes appropriate
 * command.
 */

@WebServlet(asyncSupported = true, urlPatterns = {"/FontController"})
@MultipartConfig
public class FontController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String COMMAND = "command";
    private static final Logger LOGGER = LogManager.getLogger(FontController.class);


    public FontController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Forward HttpServletRequest request, HttpServletResponse response for
     * appropriate command
     *
     * @param request
     * @param response
     * @throws ServletException, IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);
        Command command = null;
        try {
            command = CommandHelper.getInstance().getCommand(commandName);
            command.execute(request, response);
        } catch (CommandException e) {
            request.getRequestDispatcher(PageName.ERROR_505_PAGE).forward(request, response);
            LOGGER.error(e.getMessage());
        }
    }
}
