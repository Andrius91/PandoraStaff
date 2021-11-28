package team.yogurt.MySQL;

public enum Querys {


    CREATE_USERS_TABLE("CREATE TABLE IF NOT EXISTS users (" + "username VARCHAR(16)," + "uuid VARCHAR(50),"+ "last_ip VARCHAR(15)," + "is_toggled VARCHAR(5)," +"PRIMARY KEY (username));"),

    IS_TOGGLED("SELECT is_toggled FROM users WHERE username=?;"),

    CREATE_USER("INSERT INTO users (username, uuid, last_ip, is_toggled) VALUES (?, ?, ?, ?);"),

    UPDATE_USER("UPDATE users SET last_ip = ? WHERE username = ?;"),
    UPDATE_TOGGLED_PLAYER("UPDATE users SET is_toggled = ? WHERE username = ?;"),

    USER_EXIST("SELECT * FROM users WHERE username=?;"),

    GET_LAST_IP("SELECT last_ip FROM users WHERE username = ?;")

    ;

    private final String query;
    @Override
    public String toString() {
        return query;
    }

    Querys(String query){
        this.query = query;
    }
}
