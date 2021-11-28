package team.yogurt.Discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import team.yogurt.Managers.DiscordManager;

import javax.security.auth.login.LoginException;

import static team.yogurt.PandoraStaff.getConfig;

public class Discord implements DiscordManager {

    private JDA jda;
    @Override
    public TextChannel getChannel() {
        return getJDA().getTextChannelById(getConfig().getString("discord.channel-id"));
    }

    @Override
    public JDA getJDA() {
        return jda;
    }

    @Override
    public String getToken() {
        return getConfig().getString("discord.bot-token");
    }

    @Override
    public void getStatus() {
        getJDA().getPresence().setActivity(Activity.of(Activity.ActivityType.valueOf(getConfig().getString("discord.status.type")), getConfig().getString("discord.status.message")));
    }

    @Override
    public void connect() {
        try{
            jda = JDABuilder.createDefault(getToken()).build().awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void disconnect() {
        if(jda != null){
            jda.shutdownNow();
        }
    }
}
