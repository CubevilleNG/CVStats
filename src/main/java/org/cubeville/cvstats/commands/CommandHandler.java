package org.cubeville.cvstats.commands;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cubeville.cvstats.CVStats;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandHandler implements CommandExecutor {

    private final Map<String, BaseCommand> commandMap = new HashMap<>() {{
        put("send", new SendMetricCommand("cvstats.send"));
    }};

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase(Locale.ROOT);
        if (!commandName.equals("cvstats") || args == null || args.length == 0) return false;
        BaseCommand baseCommand = commandMap.get(args[0].toLowerCase());
        if (baseCommand == null) {
            sender.sendMessage("§cThat command does not exist!");
            return true;
        }
        // remove first arg, since that's used to get the command
        baseCommand.runCommandIfPossible(sender, Arrays.copyOfRange(args, 1, args.length));
        return true;
    }
}
