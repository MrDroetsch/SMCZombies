package sorcemczombies;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ZombieKills implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("Nur fuer Spieler asufuehrbar, weil Test-Plugin.");
            return true;
        }

        Player player = (Player) sender;

        int killedZombies = 0;
        String playerName = "null";

        switch(args.length) {
            case 0:
                playerName = player.getName();
                break;
            case 1:
                playerName = args[0];
                break;
            default:
                player.sendMessage("§c/zombiekills [Spielername]");
                return true;
        }

        if(playerName.equals("null")) {
            player.sendMessage("§4ERROR. Bitte einem Admin bescheid geben.");
            return true;
        }

        killedZombies = SQLiteZombies.getZombies(playerName);

        player.sendMessage("§8Zombies getötet:");
        player.sendMessage("§7" + playerName + ":§c " + killedZombies);

        return true;
    }
}
