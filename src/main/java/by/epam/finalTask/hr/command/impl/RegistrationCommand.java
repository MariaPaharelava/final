package by.epam.finalTask.hr.command.impl;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.PageName;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.service.UserService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {
    public static final String ENTER_USER_SURNAME = "enterUserSurname";
    public static final String ENTER_USER_NAME = "enterUserName";
    public static final String ENTER_LOGIN = "enterLogin";
    public static final String ENTER_PASSWORD = "enterPassword";
    public static final String ENTER_ROLE = "enterRole";
    private static final String ERROR_MESSAGES = "errorMessage";
    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(true);
        String surname = request.getParameter(ENTER_USER_SURNAME);
        String name = request.getParameter(ENTER_USER_NAME);
        String login = request.getParameter(ENTER_LOGIN);
        String password = request.getParameter(ENTER_PASSWORD);
        String role = request.getParameter(ENTER_ROLE);

        try {
            User user = null;
            if (role == null) {
                user = userService.registerUser(login, password, surname, name, "candidate");

            } else {
                user = userService.registerUser(login, password, surname, name, role);
            }
            request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage());
            throw new CommandException(e);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
