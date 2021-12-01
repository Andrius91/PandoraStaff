package team.yogurt.Listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import team.yogurt.PandoraStaff;

import static team.yogurt.PandoraStaff.getConfig;
import static team.yogurt.PandoraStaff.getSql;
import static team.yogurt.Utilities.colorString;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        String player = e.getPlayer().getName();
        String uuid = e.getPlayer().getUniqueId().toString();
        String new_ip = e.getPlayer().getAddress().getHostName();
        String last_ip = getSql().getLastIP(player);
        ProxyServer.getInstance().getPlayers().forEach(p -> {
            if (p.hasPermission(getConfig().getString("server-switch.permission"))) {
                p.sendMessage(colorString(PandoraStaff.getConfig().getString("server-switch.on-join").replace("%player%", e.getPlayer().getName())));
            }
        });
        if(e.getPlayer().hasPermission(getConfig().getString("staffchat.use"))){
            if (!getSql().userExist(player)) {
                getSql().createUser(player, uuid, new_ip);
                return;
            }
            if (!last_ip.equals(new_ip)) {
                for (ProxiedPlayer staffs : ProxyServer.getInstance().getPlayers()) {
                    if (staffs.hasPermission(getConfig().getString("server-switch.permission"))) {
                        staffs.sendMessage(colorString(getConfig().getString("server-switch.on-new-join")
                                .replace("%player%", player)
                                .replace("%lastip%", last_ip)
                                .replace("%newip%", new_ip)));
                    }
                }
                getSql().createUser(player, uuid, new_ip);
        }

        }

    }
}
