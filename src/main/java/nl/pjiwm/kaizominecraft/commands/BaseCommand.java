package nl.pjiwm.kaizominecraft.commands;

import nl.pjiwm.kaizominecraft.Kaizo;

public class BaseCommand {
    public BaseCommand(Kaizo plugin) {
        plugin.getCommand("kaizo").setExecutor(new KaizoCommand());
    }
}