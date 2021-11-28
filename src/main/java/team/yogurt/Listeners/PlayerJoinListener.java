package team.yogurt.Listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import team.yogurt.Utilities;

import static team.yogurt.PandoraStaff.getSql;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        String player = e.getPlayer().getName();
        String uuid = e.getPlayer().getUniqueId().toString();
        String ip = e.getPlayer().getAddress().getHostName();
        String last_ip = getSql().getLastIP(player);
        if(!getSql().userExist(player)){
            getSql().createUser(player, uuid, ip);
            return;
        }
        if(!last_ip.equals(ip)) {
            for (ProxiedPlayer staffs : ProxyServer.getInstance().getPlayers()) {
                if(staffs.hasPermission("staff.log")) {
                    staffs.sendMessage(Utilities.colorString("&c" + player + " ha entrado con otra dirección IP " + last_ip + " - " + ip));
                }
            }
            getSql().createUser(player, uuid, ip);
        }

    }
}
