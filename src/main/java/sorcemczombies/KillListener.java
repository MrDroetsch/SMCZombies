package sorcemczombies;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillListener implements Listener {

    private final Zombies plugin;

    public KillListener(Zombies plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if(entity.getType() != EntityType.ZOMBIE) return;

        Player killer = entity.getKiller();

        if(killer == null) return;

        int killedZombies = SQLiteZombies.getZombies(killer.getName()) + 1;

        SQLiteZombies.setZombies(killer.getName(), killedZombies);

        // Action-bar
        killer.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§7Zombie kills: §c" + killedZombies));

    }

}
