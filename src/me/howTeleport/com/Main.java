package me.howTeleport.com;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import me.howTeleport.com.cmd.Cmd;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.getCommand("htp").setExecutor(new Cmd());
	}

	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player =  (Player) sender;
		if(label.equals("spawn")) {
			if( player.getWorld().getName().equalsIgnoreCase("world1") ) {
				player.sendMessage("原地不動三秒後將傳回spawn(這個之後再做 好麻煩)");
				player.teleport(new Location(player.getWorld(), -243.5, 65.5, 251.5, 180, 20));
				return true;
			}	
		}
		
		return false;
	}

	
}
