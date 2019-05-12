package by.epam.finalTask.hr.command.impl;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.FontController;
import by.epam.finalTask.hr.controller.PageName;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.factory.ServiceFactory;
import by.epam.finalTask.hr.service.UserService;
import by.epam.finalTask.hr.service.exception.LoginAlreadyExistsException;
import by.epam.finalTask.hr.service.exception.LoginAlreadyNoExistsException;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {
    private static final String ENTER_LOGIN        = "enterLogin";
    private static final String ENTER_PASSWORD     = "enterPassword";
    private static final String User               = "user";
    private static final String ERROR_MESSAGES     = "errorMessage";
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);

    private UserService userService;

    public AuthorizationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(true);

        String login = request.getParameter(ENTER_LOGIN);
        String password = request.getParameter(ENTER_PASSWORD);

        try {
            User user = null;
            try {
                user = userService.authorization(login, password);

                session.setAttribute(User, user);
                if (user == null) {
                    throw new LoginAlreadyNoExistsException("Not Exist");
                }
                switch (user.getUserRole()) {
                    case HR:
                        request.getRequestDispatcher(PageName.HR_VACANCY_PAGE).forward(request, response);
                        break;
                    case ADMIN:
                        request.getRequestDispatcher(PageName.WORK_WITH_USER).forward(request, response);
                        break;
                    case CANDIDATE:
                        request.getRequestDispatcher(PageName.USER_VACANCY_PAGE).forward(request, response);
                        break;
                }
                return;
            } catch (LoginAlreadyExistsException e) {
                LOGGER.error("The login already no exists.");
                request.setAttribute(ERROR_MESSAGES, "The login already exists.");
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            request.setAttribute(ENTER_LOGIN, login);
            request.setAttribute(ENTER_PASSWORD, password);
            request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage());
            throw new CommandException(e);
        }
    }
}
