package sorcemczombies;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteZombies {

    public static boolean isRegistered(String player) {
        String sql = "SELECT * FROM Statistics WHERE Name = ?;";

        try {
            PreparedStatement ps = SQLite.con.prepareStatement(sql);
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public static void setZombies(String player, int zombies) {
        if(isRegistered(player)) {
            String sql = "UPDATE Statistics SET Zombies = ? WHERE Name = ?;";

            try{
                PreparedStatement ps = SQLite.con.prepareStatement(sql);
                ps.setInt(1, zombies);
                ps.setString(2, player);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String sql = "INSERT INTO Statistics(Name, Zombies) VALUES(?,?);";

            try{
                PreparedStatement ps = SQLite.con.prepareStatement(sql);
                ps.setString(1, player);
                ps.setInt(2, zombies);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addZombie(String player) {
        int zombies = getZombies(player) + 1;
        setZombies(player, zombies);
    }

    public static int getZombies(String player) {
        String sql = "SELECT Zombies FROM Statistics WHERE Name = ?;";

        try {
            PreparedStatement ps = SQLite.con.prepareStatement(sql);
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt("Zombies");
            else
                return 0;
        } catch (SQLException e) {
            return -1;
        }
    }

}
