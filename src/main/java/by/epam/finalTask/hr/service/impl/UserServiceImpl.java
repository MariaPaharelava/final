package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.UserDAO;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.service.UserService;
import by.epam.finalTask.hr.service.exception.LoginAlreadyExistsException;
import by.epam.finalTask.hr.service.exception.PasswordNotEquals;
import by.epam.finalTask.hr.util.Validator;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private final UserDAO userDAO;
    private Optional<User> userOptional;
    private Validator validator = new Validator();


    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User registerUser(String surname, String name,
                             String login, String password, String role) throws ServiceException {
        User user ;
        try {
            validator.stringInformationIsBetweenNullAndNotMuchMoreMaxLength(surname,
                    name,login,password,role);
            userOptional = userDAO.findUserByLogin(login);
            if (userOptional != null) {
                LOGGER.warn("Warning the login already exists");
                throw new LoginAlreadyExistsException("Warning the login already exists");
            }
            user = new User(login, password, surname, name, role);
            userDAO.save(user);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User authorization(String login, String password) throws ServiceException {
        User user = null;
        try {
            userOptional = userDAO.findUserByLogin(login);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                if (!user.getPassword().equals(password)) {
                    throw new PasswordNotEquals("Password not equals");
                }
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return user;
    }


    @Override
    public void removeUser(String login, String password) throws ServiceException {
        User user;
        try {
            userOptional = userDAO.findUserByLogin(login);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                userDAO.delete(user.getID());
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public User findById(Integer id) throws ServiceException {
        User user = null;
        try {
            userOptional = userDAO.findEntityById(id);
            if (userOptional.isPresent()) {
                user = userOptional.get();
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDAO.findAll();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeUser(Integer id, String surname, String name, String login, String password, String role, Boolean isBlocked) throws ServiceException {
        try {
            User user = new User(id,surname,name,login,password,role,isBlocked);
            userDAO.save(user);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }
}

