package by.epam.finalTask.hr.controller;


import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.dao.connectionpool.ConnectionPool;
import by.epam.finalTask.hr.dao.connectionpool.PooledConnection;
import by.epam.finalTask.hr.factory.*;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * HttpServlet receives all data from view (JSP-pages) and invokes appropriate
 * command.
 */

@WebServlet(asyncSupported = true, urlPatterns = {"/FontController"})
@MultipartConfig
public class FontController extends HttpServlet {
    private static final int SERVER_ERROR = 500;
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
        LOGGER.info("Take command: ", commandName);
        Command command;
        Connection connection;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connection = connectionPool.getConnection();
            CommandFactory commandFactory = createCommandFactory(connection);
            command = commandFactory.create(commandName);
            String pageName = command.execute(request, response);
            response.sendRedirect(pageName);
            ConnectionPool.getInstance().closeConnection(connection);
        } catch (CommandException e) {
            response.sendError(SERVER_ERROR);
            LOGGER.error(e.getMessage());
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private CommandFactory createCommandFactory(Connection connection) {
        BuilderFactory builderFactory = new BuilderFactory();
        DAOFactory daoFactory = new DAOFactory(connection, builderFactory);
        ServiceFactory serviceFactory = new ServiceFactory(daoFactory);
        LanguageFactory languageFactory = new LanguageFactory();
        return new CommandFactory(serviceFactory, languageFactory);
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().dispose();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
