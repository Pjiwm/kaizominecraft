package nl.pjiwm.kaizominecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KaizoCommand implements CommandExecutor {

    // This method is called, when somebody uses our command

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        commandSender.sendMessage("pong");

        return true;
    }
}