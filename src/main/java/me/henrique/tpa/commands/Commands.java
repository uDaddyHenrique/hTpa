package me.henrique.tpa.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

public abstract class Commands extends Command {

    public Commands(String name){
        super(name);
        SimpleCommandMap commandMap = ((CraftServer) Bukkit.getServer()).getCommandMap();
        commandMap.register(name, "htpa", this);
    }

    public static void makeCommands(){
        new TpaCommand();
    }
}
