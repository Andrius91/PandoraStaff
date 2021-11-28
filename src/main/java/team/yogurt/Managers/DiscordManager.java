package team.yogurt.Managers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

public interface DiscordManager {
    TextChannel getChannel();
    JDA getJDA();
    String getToken();
    void getStatus();
    void connect();
    void disconnect();
}
