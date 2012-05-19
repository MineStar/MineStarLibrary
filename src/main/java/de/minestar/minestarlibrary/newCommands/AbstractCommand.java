package de.minestar.minestarlibrary.newCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.minestar.minestarlibrary.utils.ChatUtils;
import de.minestar.minestarlibrary.utils.ConsoleUtils;

public abstract class AbstractCommand {

    private static final char OPTIONAL_ARGUMENT = '(';

    protected static final String NO_PERMISSION = "You don't have the permission for this command!";
    protected static final String WRONG_SYNTAX = "Wrong syntax for this command! Use it the followning:";

    private String syntax = null;
    private String arguments = null;
    private String permission = null;
    private String description = null;
    protected String pluginName = null;

    private int argumentCount = 0;

    protected AbstractCommand() {
        // NOTHING TO DO HERE -
    }

    // THIS CONSTRUCTOR WILL BE CALLED BY COMMANDLIST USING REFLECTIONS
    public AbstractCommand(String syntax, String[] arguments, String permission, String description, String pluginName) {
        this.syntax = syntax;
        this.permission = permission;
        this.pluginName = pluginName;

        this.arguments = convertToString(arguments);
        this.argumentCount = countNeededArgument(arguments);
    }

    /**
     * Convert the array of arguments to one single line for the help message.
     * Each argument will be wrapped by tags
     * 
     * @param arguments
     *            Array of arguments. Can be empty when no arguments are needed
     * @return A string of the array. When array is empty it will return an
     *         empty string
     */
    private String convertToString(String[] arguments) {
        // NO ARGUMENTS
        if (arguments.length == 0)
            return "";

        StringBuilder sBuilder = new StringBuilder();
        for (String arg : arguments) {
            sBuilder.append('<');
            sBuilder.append(arg);
            sBuilder.append("> ");
        }

        return sBuilder.substring(0, sBuilder.length() - 1);
    }

    // COUNT ALL ARGUMENTS WHICH ARE NEEDED TO EXECUTE THE COMMAND
    // THIS ARGUMENTS DON'T START WITH A '('
    private int countNeededArgument(String[] arguments) {
        int counter = 0;
        for (String arg : arguments) {
            if (arg.charAt(0) != OPTIONAL_ARGUMENT)
                ++counter;
        }

        return counter;
    }

    public String getArguments() {
        return arguments;
    }

    public String getPermission() {
        return permission;
    }

    public String getSyntax() {
        return syntax;
    }

    public String getDescription() {
        return description;
    }

    public int getArgumentCount() {
        return argumentCount;
    }

    public String getHelp() {
        return getSyntax() + " " + getArguments() + " : " + getDescription();
    }

    protected boolean hasCorrectSyntax(String[] args) {
        return args.length == argumentCount;
    }

    protected boolean hasRights(CommandSender sender) {
        if (sender instanceof Player) {
            // TODO: IMPLEMENT THE PERMISSIONS API
            return permission == null || true;
        } else
            return true;
    }

    public void call(String[] args, CommandSender sender) {
        if (!hasRights(sender)) {
            ChatUtils.writeError(sender, pluginName, NO_PERMISSION);
            return;
        }

        if (!hasCorrectSyntax(args)) {
            ChatUtils.writeError(sender, pluginName, WRONG_SYNTAX);
            ChatUtils.writeInfo(sender, getHelp());
            return;
        }

        if (sender instanceof ConsoleCommandSender)
            execute(args, (ConsoleCommandSender) sender);
        else if (sender instanceof Player)
            execute(args, (Player) sender);
        else
            ConsoleUtils.printError(pluginName, "Unknown command sender '" + sender.getClass().getName() + "'!");
    }

    public abstract void execute(String[] args, Player player);

    public void execute(String[] args, ConsoleCommandSender console) {
        ConsoleUtils.printError(pluginName, "The command '" + getSyntax() + "' can't be executed by console!");
    }
}
