package team.yogurt.MySQL;

import team.yogurt.Managers.DatabaseManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static team.yogurt.MySQL.Querys.*;
import static team.yogurt.PandoraStaff.getMySQL;
import static team.yogurt.Utilities.sendMessage;

public class MySQL extends DatabaseManager {
    public MySQL() {
        super(
                getMySQL().getString("mysql.username"),
                getMySQL().getString("mysql.password"),
                getMySQL().getString("mysql.database"),
                getMySQL().getString("mysql.host"),
                getMySQL().getInt("mysql.port"),
                getMySQL().getString("mysql.extra"));

    }
    private PreparedStatement ps;

    public void createTables(){
        generateTables(CREATE_USERS_TABLE.toString());
    }
    public void generateTables(String query) {
        if (isConnected()) {
            try {
                ps = this.getConnection().prepareStatement(query);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            sendMessage("MySQL Error - Not connected");
        }
    }

    public void createUser(String player, String uuid, String ip){
        try{
            if(!userExist(player)){
                ps = this.getConnection().prepareStatement(CREATE_USER.toString());
                ps.setString(1, player);
                ps.setString(2, uuid);
                ps.setString(3, ip);
                ps.setString(4, "true");
            }else{
                ps = this.getConnection().prepareStatement(UPDATE_USER.toString());
                ps.setString(1, ip);
                ps.setString(2, player);
            }
            ps.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public String getLastIP(String player){
        try{
            ps = this.getConnection().prepareStatement(GET_LAST_IP.toString());
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("last_ip");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean userExist(String player) {
        try {
            ps = this.getConnection().prepareStatement(USER_EXIST.toString());
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void setToggle(String player, String value){
        try {
            ps = this.getConnection().prepareStatement(UPDATE_TOGGLED_PLAYER.toString());
            ps.setString(1, value);
            ps.setString(2, player);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isToggled(String player) {
        try {
            ps = this.getConnection().prepareStatement(IS_TOGGLED.toString());
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("is_toggled").equals("true");
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
