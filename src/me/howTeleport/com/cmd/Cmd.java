package me.howTeleport.com.cmd;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.TextComponentSerializer;

public class Cmd  implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player =  (Player) sender;
		
		// htp help
		if(args.length == 0 || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?") ) {
			
			
			player.sendMessage(ChatColor.GOLD + "/htp help" + ChatColor.WHITE + " - 顯示此列表");
			player.sendMessage(ChatColor.GOLD + "/htp <player>" + ChatColor.WHITE + " - 寄送請求傳送至 player 身邊");
			player.sendMessage(ChatColor.GOLD + "/htp invite <player>" + ChatColor.WHITE + " - 邀請 player 傳送至您身邊");
			/*player.sendMessage(ChatColor.GOLD + "/sethome" + ChatColor.WHITE + " - 設定此處為home");
			player.sendMessage(ChatColor.GOLD + "/home" + ChatColor.WHITE + " - 傳送至home");*/
			player.sendMessage(ChatColor.GOLD + "/spawn" + ChatColor.WHITE + " - 回到spawn");
			return true;
		}
		
		// htp <player>
		else if(args[0].equalsIgnoreCase(player.getDisplayName())) {
			player.sendMessage(ChatColor.RED + "傳送失敗 ： 不可指定 自己 為傳送對象 ! !");
			return true;
		}//傳送自己
		else if( args.length == 1 ) {

			 try{
					Player targetPlayer = player.getServer().getPlayer(args[0]);
			        Location targetPlayerLocation = targetPlayer.getLocation();
			        player.sendMessage(ChatColor.GREEN + "已成功傳送請求給 " + ChatColor.GOLD + targetPlayer.getDisplayName() + ChatColor.GREEN + " 請等待回應" );
			        targetPlayer.playSound(targetPlayerLocation, Sound.ENTITY_ARROW_HIT_PLAYER , 10 , 1);
			        targetPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "===============================================");
			        targetPlayer.sendMessage("");
			        targetPlayer.sendMessage("        玩家 " + player.getDisplayName() + " 請求傳送至您身邊");
			        targetPlayer.sendMessage(ChatColor.GREEN + "     【接受請求】           " + ChatColor.RED + "【拒絕請求】");
			        targetPlayer.sendMessage("");
			        targetPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "===============================================");
			        
			        //ready
			        player.teleport(targetPlayerLocation);
			        return true;
		     }
		     catch (Exception exc){
		        	player.sendMessage(ChatColor.RED + "傳送失敗 ： 該名玩家可能 不存在 或 不在線上 ! !");
					return true;
		     }
		}	
		
		
		// htp invite <player>
		else if ((args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("i")) && args[1].equalsIgnoreCase(player.getDisplayName())) {
			player.sendMessage(ChatColor.RED + "傳送失敗 ： 不可指定 自己 為邀請對象 ! !");
			return true;
		}//邀請自己
		else if(args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("i")) {
			if( args.length == 2 && !args[1].equalsIgnoreCase(player.getDisplayName())) {
				 try {
					Player targetPlayer = player.getServer().getPlayer(args[1]);
					Location targetPlayerLocation = targetPlayer.getLocation();
					
					TextComponent acceptButton = new TextComponent("【接受請求】");
					acceptButton.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/htp i accept " + targetPlayer.getDisplayName() ) );
					acceptButton.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("按下後將直接傳送至 " + targetPlayer.getDisplayName() + "身邊" ).create() ));
					acceptButton.setColor( net.md_5.bungee.api.ChatColor.GREEN );
					
					TextComponent rejectButton = new TextComponent("【拒絕請求】");
					acceptButton.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/htp i reject " + targetPlayer.getDisplayName() ) );
					acceptButton.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("拒絕來自 " + targetPlayer.getDisplayName() + " 的傳送邀請" ).create() ));
					acceptButton.setColor( net.md_5.bungee.api.ChatColor.RED );
					
					player.sendMessage(ChatColor.GREEN + "已成功傳送請求給 " + ChatColor.GOLD + targetPlayer.getDisplayName() + ChatColor.GREEN + " 請等待回應" );
					targetPlayer.playSound(targetPlayerLocation, Sound.ENTITY_ARROW_HIT_PLAYER , 10 , 1);
					targetPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "===============================================");
					targetPlayer.sendMessage("");
					targetPlayer.sendMessage("        您被邀請傳送至玩家 " + player.getDisplayName() + " 身邊");
					targetPlayer.sendMessage("     " + acceptButton +"           " + rejectButton);
					targetPlayer.sendMessage("");
					targetPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "===============================================");
		
					//targetPlayerLocation.teleport(player);
					
					return true;
		        }
		        catch (Exception exc){
		        	
		        	player.sendMessage(ChatColor.RED + "傳送失敗 ： 該名玩家可能 不存在 或 不在線上 ! !");
		        	
		        	return true;
		        }		 
			}

		}
		
		
		
		
		
		return false;
	}
	
}
