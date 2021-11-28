package team.yogurt.Listeners;

import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import team.yogurt.Commands.StaffCommand;
import team.yogurt.PandoraStaff;

public class ChatListener implements Listener {
    @EventHandler
    public void onSendMessage(ChatEvent e){
        String message = e.getMessage();
        String sender = e.getSender().toString();
        if(PandoraStaff.getSql().isToggled(sender) && !e.isCommand()){
            StaffCommand.sendStaffChat(sender, message);
            e.setCancelled(true);
        }


    }
}
