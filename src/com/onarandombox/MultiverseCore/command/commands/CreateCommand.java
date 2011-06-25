package com.onarandombox.MultiverseCore.command.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.command.CommandSender;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.command.BaseCommand;

public class CreateCommand extends BaseCommand {
    
    public CreateCommand(MultiverseCore plugin) {
        super(plugin);
        this.name = "Create World";
        this.description = "Creates a new world of the specified type";
        this.usage = "/mvcreate" + ChatColor.GREEN + " {NAME} {TYPE}" + ChatColor.GOLD + " [SEED]";
        this.minArgs = 2;
        this.maxArgs = 3;
        this.identifiers.add("mvcreate");
        this.permission = "multiverse.world.create";
        this.requiresOp = true;
        
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        // TODO: Permissions, check
        
        int numOfParams = args.length;
        
        boolean hasSeed = numOfParams == 3;
        String worldName = args[0];
        String env = args[1];
        String seed = "";
        if (hasSeed) {
            seed = args[2];
        }
        
        if (new File(worldName).exists() || this.plugin.worlds.containsKey(worldName)) {
            sender.sendMessage(ChatColor.RED + "A Folder/World already exists with this name!");
            sender.sendMessage(ChatColor.RED + "If you are confident it is a world you can import with /mvimport");
            return;
        }
        if (plugin.addWorld(worldName, env, seed)) {
            sender.sendMessage(ChatColor.GREEN + "Complete!");
        } else {
            sender.sendMessage(ChatColor.RED + "FAILED.");
            if(plugin.getEnvFromString(env) == null) {
                sender.sendMessage("I should show the envs I know here...");
            }
        }
        return;
    }
    
}
