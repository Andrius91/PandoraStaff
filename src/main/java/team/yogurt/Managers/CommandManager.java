package team.yogurt.Managers;

import net.md_5.bungee.api.CommandSender;

public interface CommandManager {
    String getName();
    String getDescription();
    String getSyntax();
    void perform(CommandSender sender, String[] args);
}
