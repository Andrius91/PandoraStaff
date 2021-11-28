package team.yogurt.Commands.SubCommands;

import net.md_5.bungee.api.CommandSender;
import team.yogurt.Managers.CommandManager;

import static team.yogurt.PandoraStaff.getSql;
import static team.yogurt.Utilities.colorString;

public class Toggle implements CommandManager {
    @Override
    public String getName() {
        return "toggle";
    }

    @Override
    public String getDescription() {
        return "Toggle the staffchat";
    }

    @Override
    public String getSyntax() {
        return "/sc toggle";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length == 1){
            if(getSql().isToggled(sender.getName())){
                sender.sendMessage(colorString("&cHas desactivado el staffchat"));
                getSql().setToggle(sender.getName(), "false");
            }else{
                sender.sendMessage(colorString("&aHas activado el staffchat"));
                getSql().setToggle(sender.getName(), "true");
            }
        }else if (args.length > 1) {
            sender.sendMessage(colorString("&cPlease, use " + getSyntax()));
        }
    }
}
