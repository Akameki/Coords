package me.akameki.coords;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        this.getCommand("coords").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("coords")) {
            Player player;
            if (sender instanceof Player) {
                player = (Player) sender;
            } else {
                sender.sendMessage(ChatColor.RED + "Only players can use this command!");
                return true;
            }
            TextComponent message = new TextComponent("Your coordinates: ");
            Location location = player.getLocation();
            String coords = location.getBlockX()+", "+location.getBlockY()+", "+location.getBlockZ();
            TextComponent coordsMessage = new TextComponent("["+coords+"]");
            coordsMessage.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            coordsMessage.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, coords));
            coordsMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to copy")));
            message.addExtra(coordsMessage);
            player.sendMessage(message);
            player.sendMessage(coordsMessage);
            return true;
        }
        return false;
    }
}
