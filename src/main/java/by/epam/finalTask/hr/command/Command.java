package by.epam.finalTask.hr.command;

import by.epam.finalTask.hr.command.exception.CommandException;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws  ServiceException;
}
