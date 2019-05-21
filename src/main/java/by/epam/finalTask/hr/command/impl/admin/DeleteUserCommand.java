package by.epam.finalTask.hr.command.impl.admin;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.service.UserService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteUserCommand implements Command {
    private static final String USERS = "users";
    private static final String NUMBER_OF_USER = "index";
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserCommand.class);
    private UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException {
        HttpSession session = request.getSession(false);

        Integer numberOfUser = Integer.parseInt(request.getParameter(NUMBER_OF_USER));

        try {
            deleteUserFromDB(numberOfUser);
            deleteUserFromSession(session, numberOfUser);
            response.sendRedirect(PageName.WORK_WITH_USER);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new CommandException(e);
        }
    }

    private void deleteUserFromSession(HttpSession session, Integer numberOfUser) {
        List<User> userList = (ArrayList<User>) session.getAttribute(USERS);
        userList.remove(numberOfUser.intValue());
        session.setAttribute(USERS, userList);
    }

    private void deleteUserFromDB(Integer numberOfUser) throws ServiceException {
        List<User> userList = userService.getAllUsers();
        User user = userList.get(numberOfUser);
        userService.removeUser(user.getLogin(), user.getPassword());

    }
}
