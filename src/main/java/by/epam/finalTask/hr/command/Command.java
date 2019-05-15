package by.epam.finalTask.hr.command;

import by.epam.finalTask.hr.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
