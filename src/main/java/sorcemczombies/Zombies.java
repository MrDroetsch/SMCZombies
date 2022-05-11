package sorcemczombies;

import org.bukkit.plugin.java.JavaPlugin;

public final class Zombies extends JavaPlugin {

    private static Zombies instance;

    @Override
    public void onEnable() {
        instance = this;

        if(!getDataFolder().exists())
            getDataFolder().mkdirs();

        // SQL
        SQLite.connect();
        SQLite.createNewTable();

        // Listener
        new KillListener(this);

        // Commands
        getCommand("zombiekills").setExecutor(new ZombieKills());

    }

    @Override
    public void onDisable() {
        SQLite.disconnect();
    }

    static Zombies getInstance() {
        return instance;
    }

}
