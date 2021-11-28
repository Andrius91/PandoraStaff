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

public class StaffCommand extends Command {
    ArrayList<CommandManager> commands = new ArrayList<>();
    public StaffCommand(String... aliases) {
        super("staffchat", "pandorastaff.use", aliases);
        commands.add(new Toggle());
        commands.add(new List());
    }



    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission(super.getPermission())){
            if (args.length <=0) {
                sender.sendMessage(Utilities.colorString("&cUse: /sc <message>"));
            }else {
                for (CommandManager cmd : getCommands()) {
                    if (args[0].equalsIgnoreCase(cmd.getName())) {
                        cmd.perform(sender, args);
                        return;
                    }
                }
                StringBuilder reason = new StringBuilder();
                for (String arg : args) {
                    reason.append(arg).append(" ");
                }
                for(ProxiedPlayer staffs : ProxyServer.getInstance().getPlayers()){
                    if(staffs.hasPermission("pandorastaff.read")){
                        staffs.sendMessage(Utilities.colorString("&cSTAFF " + sender.getName()+ "&a: " +reason.toString().trim()));
                    }
                }
            }

        }
    }
    private ArrayList<CommandManager> getCommands() {
        return commands;
    }
}
