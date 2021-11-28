package team.yogurt.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import team.yogurt.Commands.SubCommands.List;
import team.yogurt.Commands.SubCommands.Toggle;
import team.yogurt.Managers.CommandManager;
import team.yogurt.Utilities;

import java.util.ArrayList;

import static team.yogurt.PandoraStaff.getConfig;

public class StaffCommand extends Command {
    ArrayList<CommandManager> commands = new ArrayList<>();
    public StaffCommand(String... aliases) {
        super("staffchat", getConfig().getString("staff-chat.permission"), aliases);
        commands.add(new Toggle());
        commands.add(new List());
    }



    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission(super.getPermission())){
            if (args.length <=0) {
                sender.sendMessage(Utilities.colorString(getConfig().getString("staff-chat.usage")));
            }else {
                for (CommandManager cmd : getCommands()) {
                    if (args[0].equals(cmd.getName())) {
                        cmd.perform(sender, args);
                        return;
                    }
                }
                StringBuilder message = new StringBuilder();
                for (String arg : args) {
                    message.append(arg).append(" ");
                }
                sendStaffChat(sender.getName(), message.toString().trim());
            }

        }
    }
    private ArrayList<CommandManager> getCommands() {
        return commands;
    }
    public static void sendStaffChat(String sender, String message){
        for(ProxiedPlayer staffs : ProxyServer.getInstance().getPlayers()){
            if(staffs.hasPermission(getConfig().getString("staff-chat.permission"))){
                staffs.sendMessage(Utilities.colorString(
                        getConfig().getString("staff-chat.format")
                                .replace("%player%", sender)
                                .replace("%message%", message)
                ));
            }
        }
    }
}
