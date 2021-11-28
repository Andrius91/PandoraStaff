package team.yogurt.Commands.SubCommands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import team.yogurt.Managers.CommandManager;
import team.yogurt.PandoraStaff;

import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static team.yogurt.PandoraStaff.getConfig;
import static team.yogurt.Utilities.colorString;

public class List implements CommandManager {

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "View the online staffs";
    }

    @Override
    public String getSyntax() {
        return "/sc list";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Map<String, ServerInfo> proxy = ProxyServer.getInstance().getServers();
        if(args.length == 1){
            for(ServerInfo svs: proxy.values()){
                StringJoiner joiner = new StringJoiner(getConfig().getString("staff-list.delimiter"));
                for (ProxiedPlayer proxiedPlayer : svs.getPlayers()) {
                    if (proxiedPlayer.hasPermission(getConfig().getString("staff-list.permission"))) {
                        joiner.add(proxiedPlayer.getName());
                    }
                }
                sender.sendMessage(colorString(getConfig().getString("staff-list.format")
                        .replace("%server%", svs.getName())
                        .replace("%staffs%", joiner.toString())));
            }
        }else{
            sender.sendMessage(colorString("&cPlease, use " + getSyntax()));
        }
    }
}
