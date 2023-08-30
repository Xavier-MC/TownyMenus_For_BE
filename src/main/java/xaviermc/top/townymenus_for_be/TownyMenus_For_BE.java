package xaviermc.top.townymenus_for_be;

import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.*;

import xaviermc.top.townymenus_for_be.gui.*;

public final class TownyMenus_For_BE extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN + "命令功能注册中");
        loadCommands();

    }

    private void loadCommands() {
        PluginCommand begui = getCommand("begui");
        if (begui == null) {
            getLogger().severe("插件加载命令时发生错误，你是否加载了完整的插件？");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            getLogger().info(ChatColor.GREEN + "命令功能加载成功");
        }
        begui.setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.equals(getCommand("begui"))) {
            if (sender instanceof Player) {
                MainForm.sendMainForm((Player) sender);
            } else {
                sender.sendMessage("§c只有玩家才能使用这个命令");
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GREEN + "插件卸载成功");
    }
}


