package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.UserDAO;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.service.UserService;
import com.google.protobuf.ServiceException;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private static final UserDAO EMPTY_USER_DAO_MOCK = mock(UserDAO.class);
    private final UserService userServiceWithEmptyMocks = new UserServiceImpl(EMPTY_USER_DAO_MOCK);
    private static final User CORRECT_USER = new User(
            null,
            "login",
            "password",
            "surname",
            "name",
            "admin",
            true
    );
    private static final User EMPTY_LOGIN = new User(
            "",
            "password",
            "surname",
            "name",
            "admin"
    );
    private static final User EMPTY_SURNAME = new User(
            "login",
            "password",
            "",
            "name",
            "admin"
    );
    private static final User LONG_PASSWORD = new User(
            "login",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "surname",
            "name",
            "admin"
    );

    @Test
    public void registerUserShouldSaveCorrectUser() throws ServiceException, DAOException {
        userServiceWithEmptyMocks.registerUser(CORRECT_USER.getSurname(),CORRECT_USER.getName(),
                CORRECT_USER.getLogin(),CORRECT_USER.getPassword(),CORRECT_USER.getUserRole().toString());
        verify(EMPTY_USER_DAO_MOCK).save(CORRECT_USER);
        verify(EMPTY_USER_DAO_MOCK).findUserByLogin("login");
        verifyNoMoreInteractions(EMPTY_USER_DAO_MOCK);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void registerShouldThrowServiceExceptionWhenFilmWithEmptyLoginSupplied() throws ServiceException, DAOException {
        userServiceWithEmptyMocks.registerUser(EMPTY_LOGIN.getSurname(),EMPTY_LOGIN.getName(),
                EMPTY_LOGIN.getLogin(),EMPTY_LOGIN.getPassword(),EMPTY_LOGIN.getUserRole().toString());
        verify(EMPTY_USER_DAO_MOCK).save(CORRECT_USER);
        verifyNoMoreInteractions(EMPTY_USER_DAO_MOCK);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void registerShouldThrowServiceExceptionWhenFilmWithEmptySurnameSupplied() throws ServiceException, DAOException {
        userServiceWithEmptyMocks.registerUser(EMPTY_SURNAME.getSurname(),EMPTY_SURNAME.getName(),
                EMPTY_SURNAME.getLogin(),EMPTY_SURNAME.getPassword(),EMPTY_SURNAME.getUserRole().toString());
        verify(EMPTY_USER_DAO_MOCK).save(EMPTY_SURNAME);
        verifyNoMoreInteractions(EMPTY_USER_DAO_MOCK);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void registerShouldThrowServiceExceptionWhenFilmWithLongPasswordSupplied() throws ServiceException, DAOException {
        userServiceWithEmptyMocks.registerUser(LONG_PASSWORD.getSurname(),LONG_PASSWORD.getName(),
                LONG_PASSWORD.getLogin(),LONG_PASSWORD.getPassword(),LONG_PASSWORD.getUserRole().toString());
        verify(EMPTY_USER_DAO_MOCK).save(LONG_PASSWORD);
        verifyNoMoreInteractions(EMPTY_USER_DAO_MOCK);
    }
}