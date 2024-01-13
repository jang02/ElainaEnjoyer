package me.jumpi.elainaenjoyer.commands;

import me.jumpi.bosses.BossType;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnBossCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0)
            return true;

        if (sender instanceof Player player){
            BossType boss = BossType.getBoss(args[0]);

            if (boss == null){
                sender.sendMessage("gg go next, wrong boss name");
                return true;
            }
            boss.getBoss().spawn(player.getLocation());
        }

        return true;
    }
}
