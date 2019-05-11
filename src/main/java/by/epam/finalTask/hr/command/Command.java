package by.epam.finalTask.hr.command;

import by.epam.finalTask.hr.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * interface for all commands
 */

public interface Command {
    /**
     * method for execution particular logic in command
     *
     * @param request
     * @param response
     * @throws CommandException
     */

    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;

}
