package sorcemczombies;

import java.sql.*;

public class SQLite {

    public static Connection con;
    private static String url = "jdbc:sqlite:" + Zombies.getInstance().getDataFolder() + "/statistics.db";

    /**
     * Connect to a sample database
     */
    public static void connect() {
        con = null;
        try {
            // db parameters
            // create a connection to the database
            con = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            createNewDatabase();
        }
    }

    public static void disconnect() {
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createNewDatabase() {
        try {
            con = DriverManager.getConnection(url);
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Statistics (\n"
                + "Name TEXT NOT NULL UNIQUE,\n"
                + "Zombies INTEGER DEFAULT 0\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
