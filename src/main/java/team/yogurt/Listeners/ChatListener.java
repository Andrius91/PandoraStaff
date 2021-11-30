package team.yogurt.Listeners;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import team.yogurt.Commands.StaffCommand;
import team.yogurt.PandoraStaff;
import team.yogurt.Utilities;

public class ChatListener implements Listener {
    @EventHandler
    public void onSendMessage(ChatEvent e){
        String message = e.getMessage();
        ProxiedPlayer sender = (ProxiedPlayer) e.getSender();
        String[] command = e.getMessage().split(":");
        if(PandoraStaff.getSql().isToggled(sender.getName()) && !e.isCommand()){
            StaffCommand.sendStaffChat(sender.getName(), message);
            e.setCancelled(true);
        }
        if (e.isCommand() && command[0].contains("/")){
            sender.sendMessage(Utilities.colorString("&cThe command not contain ':' character"));
            e.setCancelled(true);
        }
    }
}
