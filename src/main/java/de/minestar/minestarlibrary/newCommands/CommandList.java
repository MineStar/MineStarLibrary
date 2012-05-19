package de.minestar.minestarlibrary.newCommands;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

import de.minestar.minestarlibrary.annotations.Arguments;
import de.minestar.minestarlibrary.annotations.Description;
import de.minestar.minestarlibrary.annotations.Permission;
import de.minestar.minestarlibrary.annotations.Syntax;
import de.minestar.minestarlibrary.utils.ConsoleUtils;

public class CommandList {

    private Set<AbstractCommand> commandSet = new HashSet<AbstractCommand>();

    @SafeVarargs
    public CommandList(String pluginName, Class<? extends AbstractCommand>... commands) {
        this.init(pluginName, commands);
    }

    @SuppressWarnings("unchecked")
    private void init(String pluginName, Class<? extends AbstractCommand>... commands) {
        try {
            // TEMP VARS
            String syntax = null;
            String[] args = null;
            String permissions = null;
            String description = null;
            Constructor<? extends AbstractCommand> constructor = null;

            // ITERATE OVER ALL COMMAND CLASSES
            for (Class<? extends AbstractCommand> command : commands) {

                // GET SYNTAX - EVERY COMMAND MUST HAVE A SYNTAX
                syntax = getSyntax(command);
                if (syntax == null) {
                    ConsoleUtils.printError(pluginName, "No syntax annotation found for the command " + command.toString() + " ! Command is not loaded!");
                    continue;
                }

                // GET SYNTAX - EVERY COMMAND MUST HAVE A SYNTAX
                description = getDescription(command);
                if (description == null) {
                    ConsoleUtils.printError(pluginName, "No description annotation found for the command " + command.toString() + " ! Command is not loaded!");
                    continue;
                }

                // GET ARGUMENTS - WHEN NO ARGUMENT IS NEED THE ARRAY.LENGTH = 0
                args = getArguments(command);
                if (args == null) {
                    ConsoleUtils.printError(pluginName, "No arguments annotation found for the command " + command.toString() + " ! Command is not loaded!");
                    continue;
                }

                // GET PERMISSIONS - CAN BE AN EMPTY STRING ""
                permissions = getPermission(command);
                if (permissions == null) {
                    ConsoleUtils.printError(pluginName, "No permissions annotation found for the command " + command.toString() + " ! Command is not loaded!");
                    continue;
                }
                // EMPTY PERMISSIONS = EVERYONE CAN USE IT
                if (permissions.equals(""))
                    permissions = null;

                // GET THE CONSTRUCTOR OF THE COMMAND
                constructor = command.getConstructor(String.class, String[].class, String.class, String.class, String.class);

                // CREATE AN INSTANCE OF THE COMMAND AND ADD IT TO THE SET
                commandSet.add(constructor.newInstance(syntax, args, permissions, description, pluginName));
            }

        } catch (Exception e) {
            ConsoleUtils.printException(e, pluginName, "Can't create the commandlist!");
        }
    }

    // GET THE SYNTAX FROM THE SYNTAX ARGUMENTS
    private String getSyntax(Class<? extends AbstractCommand> commandClass) {
        Syntax syntax = commandClass.getAnnotation(Syntax.class);
        if (syntax != null)
            return syntax.value();
        else
            return null;
    }

    // GET THE ARGUMENTS FROM THE ANNOTATION ARGUMENTS
    private String[] getArguments(Class<? extends AbstractCommand> commandClass) {
        Arguments arguments = commandClass.getAnnotation(Arguments.class);
        if (arguments != null)
            return arguments.value();
        else
            return null;
    }

    // GET THE PERMISSION FROM THE PERMISSION ARGUMENTS
    private String getPermission(Class<? extends AbstractCommand> commandClass) {
        Permission permission = commandClass.getAnnotation(Permission.class);
        if (permission != null)
            return permission.value();
        else
            return null;
    }

    // GET THE PERMISSION FROM THE PERMISSION ARGUMENTS
    private String getDescription(Class<? extends AbstractCommand> commandClass) {
        Description description = commandClass.getAnnotation(Description.class);
        if (description != null)
            return description.value();
        else
            return null;
    }

}
