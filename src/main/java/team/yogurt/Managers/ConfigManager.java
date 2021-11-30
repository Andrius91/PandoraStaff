package team.yogurt.Managers;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import team.yogurt.PandoraStaff;
import team.yogurt.Utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigManager {


    public static void createFolder(){
        if (!PandoraStaff.getInstance().getDataFolder().exists()){
            PandoraStaff.getInstance().getDataFolder().mkdir();
        }
    }
    public static Configuration getConf(String filename){
        File file = new File(PandoraStaff.getInstance().getDataFolder(), filename);
        Configuration config = null;
        if (!file.exists()){
            try {
                InputStream in = PandoraStaff.getInstance().getResourceAsStream(filename);
                Files.copy(in, file.toPath());
                PandoraStaff.getInstance().getLogger().info(Utilities.color(filename + " &aregistered!"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
    
    public static File getFile(String filename){
        File file = new File(PandoraStaff.getInstance().getDataFolder(), filename);
        if (!file.exists()){
            try {
                InputStream in = PandoraStaff.getInstance().getResourceAsStream(filename);
                Files.copy(in, file.toPath());
                PandoraStaff.getInstance().getLogger().info(Utilities.color(filename + " &aregistered!"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return file;
    }
}
