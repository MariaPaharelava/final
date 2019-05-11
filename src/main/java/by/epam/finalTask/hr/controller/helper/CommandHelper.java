package by.epam.finalTask.hr.controller.helper;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains all commands and their names in HashMap.
 */

public class CommandHelper {
	private static final CommandHelper instance = new CommandHelper();
	private Map<CommandName, Command> commands = new HashMap<>();
	private static final Logger LOGGER = LogManager.getLogger(CommandHelper.class);

	private CommandHelper() {
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.GO_TO_FOR_USER, new GoToForUserCommand());
		commands.put(CommandName.VACANCIES, new GoToForUserCommand("vacancirs.jsp"));
	}

	/**
	 * Give command by name.
	 * 
	 * @param name
	 * @return Command - command for execution particular action
	 * @throws CommandException
	 */

	public Command getCommand(String name) throws CommandException {
		if (name == null || name.isEmpty()) {
			LOGGER.error("Error in command name");
			throw new CommandException("Error in command name");
		}
		name = name.replace('-', '_');
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		Command command = commands.get(commandName);
		return command;
	}

	public static CommandHelper getInstance() {
		return instance;
	}

}
