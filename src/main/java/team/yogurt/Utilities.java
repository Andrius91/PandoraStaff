package team.yogurt;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class Utilities {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static BaseComponent[] colorString(String string) {
        if (string == null) {
            throw new NullPointerException("String vacia");
        } else {
            return TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', string));
        }
    }
    public static void sendMessage(String message){
        PandoraStaff.getInstance().getLogger().info(color(message));
    }
}
