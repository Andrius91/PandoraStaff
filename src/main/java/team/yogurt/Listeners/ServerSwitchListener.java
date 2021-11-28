package team.yogurt.Listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import team.yogurt.Utilities;

public class ServerSwitchListener implements Listener {

    @EventHandler
    public void onSwitchSwerver(ServerConnectEvent e){
        if(e.getPlayer().getServer() != null){
            ProxiedPlayer p = e.getPlayer();
            String from = e.getPlayer().getServer().getInfo().getName();
            String to = e.getTarget().getName();
            for(ProxiedPlayer staffs : ProxyServer.getInstance().getPlayers()){
                staffs.sendMessage(Utilities.colorString("&a"+ p+ " ha cambiado de "+ from + " - " +to));
            }
        }
    }
}
