package team.yogurt;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import team.yogurt.Commands.StaffCommand;
import team.yogurt.Discord.Discord;
import team.yogurt.Listeners.PlayerJoinListener;
import team.yogurt.Listeners.ServerSwitchListener;
import team.yogurt.Managers.ConfigManager;
import team.yogurt.MySQL.MySQL;


public class PandoraStaff extends Plugin {
    private static PandoraStaff instance;
    private static Discord discord;
    private static MySQL sql;
    @Override
    public void onEnable() {
        instance = this;
        ConfigManager.createFolder();
        getConfig();
        discord = new Discord();
        sql = new MySQL();
        getDiscord().connect();
        getSql().createTables();
        registerListeners();
        registerCommands();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        getSql().disconnect();
        getDiscord().disconnect();
        // Plugin shutdown logic
    }
    public static PandoraStaff getInstance() {
        return instance;
    }
    public static MySQL getSql(){
        return sql;
    }
    public static Discord getDiscord(){
        return discord;
    }
    public static Configuration getConfig(){
        return ConfigManager.getFile("config.yml");
    }
    public static Configuration getMySQL(){
        return ConfigManager.getFile("mysql.yml");
    }

    private void registerListeners(){
        PluginManager instance = getProxy().getPluginManager();
        instance.registerListener(this, new PlayerJoinListener());
        instance.registerListener(this, new ServerSwitchListener());
    }

    private void registerCommands(){
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new StaffCommand(getAliases("commands.aliases")));
    }

    public String[] getAliases(String path) {
        String[] aliases = new String[getConfig().getStringList(path).size()];
        
        for(int i = 0; i<aliases.length; i++){
            aliases[i] = getConfig().getStringList(path).get(i);
        }
        return aliases;
    }
}
