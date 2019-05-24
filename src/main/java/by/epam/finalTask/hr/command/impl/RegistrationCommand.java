package by.epam.finalTask.hr.command.impl;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.service.UserService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
    private static final String ENTER_USER_SURNAME = "enterUserSurname";
    private static final String ENTER_USER_NAME = "enterUserName";
    private static final String ENTER_LOGIN = "enterLogin";
    private static final String ENTER_PASSWORD = "enterPassword";
    private static final String ENTER_ROLE = "enterRole";
    private static final String ERROR_MESSAGES = "errorMessage";
    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(true);

        String surname = request.getParameter(ENTER_USER_SURNAME);
        String name = request.getParameter(ENTER_USER_NAME);
        String login = request.getParameter(ENTER_LOGIN);
        String password = request.getParameter(ENTER_PASSWORD);
        String role = request.getParameter(ENTER_ROLE);
        if (role == null) {
            User user = userService.registerUser(surname, name, login, password,"candidate");
            if (user == null) {
                session.setAttribute(ERROR_MESSAGES, "This login is already in use");
                LOGGER.info("Attempting to enter an existing login");
            }
        } else {
            userService.registerUser(surname, name, login, password, role);
            LOGGER.info("Registered user with login - " + login);
        }
        return PageName.INDEX_JSP;
    }
}
