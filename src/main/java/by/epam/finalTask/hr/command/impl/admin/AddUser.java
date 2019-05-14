package by.epam.finalTask.hr.command.impl.admin;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.PageName;
import by.epam.finalTask.hr.entity.Interview;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AddUser implements Command {
    private static final String ENTER_SURNAME = "enterSurname";
    private static final String ENTER_NAME = "enterName";
    private static final String ENTER_LOGIN = "enterLogin";
    private static final String ENTER_PASSWORD = "enterPassword";
    private static final String ENTER_ROLE = "enterStatus";
    private static final String USERS = "users";
    private static final Logger LOGGER = LogManager.getLogger(AddUser.class);
    private UserService userService;

    public AddUser(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);

        String surname = request.getParameter(ENTER_SURNAME);
        String name = request.getParameter(ENTER_NAME);
        String login = request.getParameter(ENTER_LOGIN);
        String password = request.getParameter(ENTER_PASSWORD);
        String role = request.getParameter(ENTER_ROLE);
        try {
            try {
                User user = new User(login, password, surname, name, role);
                addUserToSession(session, user);
                addUserToDB(user);
                request.getRequestDispatcher(PageName.WORK_WITH_USER).forward(request, response);
            } catch (ServiceException e) {
                request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
                LOGGER.error(e.getMessage());
                throw new CommandException(e);
            }
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage());
            throw new CommandException(e);        }


    }
    private void addUserToSession(HttpSession session, User user){
        List<User> userArrayList = (ArrayList<User>)session.getAttribute(USERS);
        userArrayList.add(user);
        session.setAttribute(USERS, userArrayList);
    }

    private void addUserToDB(User user) throws ServiceException {

        userService.registerUser(user.getSurname(), user.getName(), user.getLogin(),
                user.getPassword(), user.getUserRole().toString());
    }
}
