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
import java.util.ArrayList;
import java.util.List;

public class BlockedUserCommand implements Command {
    private static final String USERS = "users";
    private static final String NUMBER_OF_USER = "index";
    private static final Logger LOGGER = LogManager.getLogger(BlockedUserCommand.class);
    private UserService userService;

    public BlockedUserCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        Integer numberOfUser = Integer.parseInt(request.getParameter(NUMBER_OF_USER));
        List<User> userList = userService.getAllUsers();
        User user = userList.get(numberOfUser);
        changeBlocked(user);
        blockedUserInDB(user);
        blockedUserInSession(session, numberOfUser, user);
        return PageName.WORK_WITH_USER;
    }

    private void blockedUserInSession(HttpSession session, Integer numberOfUser, User user) {
        List<User> userList = (ArrayList<User>) session.getAttribute(USERS);
        userList.set(numberOfUser, user);
        session.setAttribute(USERS, userList);
    }

    private void blockedUserInDB(User user) throws ServiceException {
        userService.changeUser(user.getID(),user.getSurname(), user.getName(), user.getLogin(),
                user.getPassword(), user.getUserRole().toString(),user.getBlocked());
    }

    private void changeBlocked(User user){
        if(user.getBlocked()){
            user.setBlocked(false);
        }else{
            user.setBlocked(true);
        }
    }
}
