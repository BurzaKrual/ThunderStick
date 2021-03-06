package me.burzakrual.lightningstick;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this! Sorry.");
            return false;
        }
        Player p = (Player)sender;
        PlayerInventory pi = p.getInventory();
        List<String> lores = new ArrayList<>();
        lores.add(ChatColor.YELLOW + "You are a thunder god");
        lores.add(ChatColor.RED + "You can throw lightnings.");
        String myDisplayName =  "Lightning Stick";
        ItemStack myItem = new ItemStack(Material.STICK);
        ItemMeta im = myItem.getItemMeta();
        im.setDisplayName(myDisplayName);
        myItem.setItemMeta(im);
        im.setLore(lores);
        myItem.setItemMeta(im);
        if (cmd.getName().equalsIgnoreCase("lstick")) {
            if (sender.hasPermission("lstick.get"))
                pi.addItem(new ItemStack[] { myItem });
            sender.sendMessage(ChatColor.AQUA + "[Lightning Stick] " + ChatColor.GREEN + "You have got your stick");
            if (cmd.getName().equalsIgnoreCase("lstick")) {
                if (!sender.hasPermission("lstick.get"))
                    sender.sendMessage(ChatColor.AQUA + "[Lightning Stick] " + ChatColor.GOLD + ">ERROR<" + ChatColor.RED + " You dont have permission for this!");
                return true;
            }
            return false;
        }
        return false;
    }

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, (Plugin)this);
    }

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.STICK) {
            player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("Lightning Stick");
            player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
        }
    }
}
