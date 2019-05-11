package by.epam.finalTask.hr.service;

import by.epam.finalTask.hr.entity.User;
import com.google.protobuf.ServiceException;

import java.util.Optional;

public interface UserService {
    User registerUser(String surname, String name, String login, String password, String role) throws ServiceException;
    User authorization(String login, String password) throws ServiceException;
    void removeUser(String login, String password) throws ServiceException;
}
