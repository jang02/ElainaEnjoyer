package me.jumpi;

import me.jumpi.elainaenjoyer.commands.SpawnBossCommand;
import me.jumpi.elainaenjoyer.commands.TyfusCommand;
import me.jumpi.listeners.BossListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;

public class ElainaEnjoyer extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new BossListener(), this);

        initializeCommands();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private PluginCommand getCommand(String name, Plugin pl){

        PluginCommand cmd = null;

        try{
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);

            cmd = c.newInstance(name, pl);
        }
        catch(Exception ex){
            System.out.println("gg go next");
            this.onDisable();
        }

        return cmd;
    }

    private void initializeCommands(){
        registerCommand("tyfus", new TyfusCommand());
        registerCommand("spawnboss", new SpawnBossCommand());
    }

    private void registerCommand(String name, CommandExecutor commandExecutor){
        PluginCommand cmd = getCommand(name, this);
        cmd.setExecutor(commandExecutor);
        this.getServer().getCommandMap().register(this.getDescription().getName(), cmd);
    }
}
