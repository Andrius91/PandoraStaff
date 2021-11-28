package team.yogurt.Commands.SubCommands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import team.yogurt.Managers.CommandManager;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import static team.yogurt.Utilities.colorString;

public class List implements CommandManager {

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Show the online staffs";
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
                sender.sendMessage(colorString("&c" + svs.getName() + " - " + svs.getPlayers().stream().filter(proxiedPlayer -> proxiedPlayer.hasPermission("pandorastaff.list")).collect(Collectors.toList())));
            }
        }else{
            sender.sendMessage(colorString("&cPlease, use " + getSyntax()));
        }
    }
}
