package kazuki.login;

import java.io.File;
import java.util.LinkedHashMap;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Restriction extends PluginBase implements Listener{
	Config setting;
	Config player;
	@SuppressWarnings("deprecation")
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info("§a起動しました §bby kazuki102812");
		this.getDataFolder().mkdirs();
		this.player = new Config(new File(this.getDataFolder(), "player.yml"),Config.YAML);
		this.setting = new Config(new File(this.getDataFolder(), "setting.yml"),Config.YAML,
				new LinkedHashMap<String, Object>() {
			{
				put("Restriction", "false");
				put("kickMessage", "§a[RT]§fメンテナンス中");
			}
		}
		);
	}
	@EventHandler
	public void onJoin(PlayerPreLoginEvent event) {
        if(this.setting.getString("Restriction").equals("true")) {
            Player player = event.getPlayer();
            String name = player.getName();
            if(!(this.player.getString(name) != null)) {
            	event.isCancelled();
            }
        }
	}
	public boolean onCommand(final CommandSender sender, Command command, String label, String[] args){
		switch(command.getName()){
		case "restriction":
			try {
				if(args[0] != null){
					switch (args[0]) {
					case "on":
						try {
							Player player = (Player)sender;
							if(player.isOp()) {
								if(this.setting.getString("Restriction").equals("false")) {
									for(Player all : Server.getInstance().getOnlinePlayers().values()){
										if(!(all.isOp())) {
											String kickmessage = this.setting.getString("kickMessage");
											all.kick(kickmessage, false);
										}
									}
									this.setting.set("Restriction", "true");
									this.setting.save();
									player.sendMessage("§f>>>§a[RT]§fLoginを制限しました");
								}else if(this.setting.getString("Restriction").equals("true")){
									player.sendMessage("§f>>>§a[RT]§f既にLoginを制限しています");
								}else {
									for(Player all : Server.getInstance().getOnlinePlayers().values()){
										if(!(all.isOp())) {
											String kickmessage = this.setting.getString("kickMessage");
											all.kick(kickmessage, false);
										}
									}
									this.setting.set("Restriction", "true");
									this.setting.save();
									player.sendMessage("§f>>>§a[RT]§fLoginを制限しました");
								}
							}else {
								player.sendMessage("§f>>>§a[RT]§f使用する権限がありません");
							}
						}
						catch(ArrayIndexOutOfBoundsException e){
							sender.sendMessage("§f>>>§a[RT]§f/restriction");
					  }

					case "off":
						try {
							Player player = (Player)sender;
							if(player.isOp()) {
								if(this.setting.getString("Restriction").equals("true")) {
									player.sendMessage("§f>>>§a[RT]§fLoginを制限を解除しました");
									this.setting.set("Restriction", "false");
									this.setting.save();
								}else if(this.setting.getString("Restriction").equals("false")) {
									player.sendMessage("§f>>>§a[RT]§f既にLoginの制限を解除しています");
								}else {
									player.sendMessage("§f>>>§a[RT]§fLoginを制限を解除しました");
									this.setting.set("Restriction", "false");
									this.setting.save();
								}
							}else {
								player.sendMessage("§f>>>§a[RT]§f使用する権限がありません");
							}
						}
						catch(ArrayIndexOutOfBoundsException e){
							sender.sendMessage("§f>>>§a[RT]§f/restriction");
					  }

					case "add":
						try {
							Player player = (Player)sender;
                             if(player.isOp()) {

                             }else {
                            	 player.sendMessage("§f>>>§a[RT]§f使用する権限がありません");
                             }
						}
						catch(ArrayIndexOutOfBoundsException e){
							sender.sendMessage("§f>>>§a[RT]§f/restriction");
					  }

					case "del":
						try {
							Player player = (Player)sender;
                            if(player.isOp()) {

                            }else {
                           	 player.sendMessage("§f>>>§a[RT]§f使用する権限がありません");
                            }
						}
						catch(ArrayIndexOutOfBoundsException e){
							sender.sendMessage("§f>>>§a[RT]§f/restriction");
					  }

					case "list":
						try {
							Player player = (Player)sender;
                            if(player.isOp()) {

                            }else {
                           	 player.sendMessage("§f>>>§a[RT]§f使用する権限がありません");
                            }
						}
						catch(ArrayIndexOutOfBoundsException e){
							sender.sendMessage("§f>>>§a[RT]§f/restriction");
					  }

					}
				}
			}
			catch(ArrayIndexOutOfBoundsException e){
				sender.sendMessage("§f>>>§a[RT]§f/restriction");
		  }
		  break;
		}
		return false;
	}

}
