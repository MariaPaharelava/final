package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.UserDAO;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.service.UserService;
import by.epam.finalTask.hr.service.exception.LoginAlreadyExistsException;
import by.epam.finalTask.hr.service.exception.PasswordNotEquals;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private final UserDAO userDAO;
    private Optional<User> userOptional;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User registerUser(String surname, String name, String login, String password, String role) throws ServiceException {
        User user = null;
        try {
            userOptional = ((UserDAO) userDAO).findUserByLogin(login);
            if (userOptional.isPresent()) {
                LOGGER.warn("Warning the login already exists");
                throw new LoginAlreadyExistsException("Warning the login already exists");
            }
            user = new User(login, password, surname, name, role);
            ((UserDAO) userDAO).save(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User authorization(String login, String password) throws ServiceException {
        User user = null;
        try {
            userOptional = ((UserDAO) userDAO).findUserByLogin(login);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                if (!user.getPassword().equals(password)) {
                    throw new PasswordNotEquals("Password not equals");
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void removeUser(String login, String password) throws ServiceException {
        User user = null;
        try {
            userOptional = ((UserDAO) userDAO).findUserByLogin(login);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                ((UserDAO) userDAO).delete(user.getID());
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findById(Integer id) throws ServiceException {
        User user = null;
        try {
            userOptional = ((UserDAO) userDAO).findEntityById(id);
            if (userOptional.isPresent()) {
                user = userOptional.get();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return ((UserDAO) userDAO).findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

