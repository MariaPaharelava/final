package by.epam.finalTask.hr.entity;

import by.epam.finalTask.hr.entity.enums.UserRole;

import java.util.Objects;

public class User implements Indentifable {
    private Integer userID = null;
    private String login;
    private String password;
    private String surname;
    private String name;
    private UserRole userRole;

    public User(String login, String password, String surname, String name, String userRole) {
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        setUserRole(userRole);
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setUserRole(String userRole) {
        userRole = userRole.toUpperCase();
        switch (userRole) {
            case "ADMIN":
                this.userRole = UserRole.ADMIN;
                break;
            case "CANDIDATE":
                this.userRole = UserRole.CANDIDATE;
                break;
            case "HR":
                this.userRole = UserRole.HR;
                break;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userID.equals(user.userID) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(name, user.name) &&
                userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, login, password, surname, name, userRole);
    }

    @Override
    public Integer getID() {
        return userID;
    }
}
