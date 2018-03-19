package de.kiyan.skyprison.commands;

import de.kiyan.skyprison.Config;
import de.kiyan.skyprison.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CMDSignfilter implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if( ! ( sender instanceof Player) )
        {
            sender.sendMessage( "§cYou must be a player" );

            return false;
        }

        if( !label.equalsIgnoreCase("signfilter"))
        {
            return false;
        }

        Player player = (Player)sender;

        if( !player.hasPermission( "subzero.signfilter"))
        {
            player.sendMessage(Config.SignPrefix + " §cYou dont have enough permissions!");

            return false;
        }
        List<String> filterWords = Main.instance.getConfig().getStringList( "Words" );

        if( args.length == 0 )
        {
            player.sendMessage( "     §e§l" + Config.SignPrefix );
            player.sendMessage( "§e§lCensored words :");
            player.sendMessage( "§d"+ filterWords.toString() );

            return true;
        }

        if( args[ 0 ].equalsIgnoreCase( "add" ))
        {
            if( args.length == 1 )
            {
                player.sendMessage( Config.SignPrefix + " §ePlease provide a word to being excluded." );

                return true;
            }

            if( ! filterWords.contains( args[ 1 ] ) )
            {
                player.sendMessage( Config.SignPrefix + " §d" + args[ 1 ] +"§a added to your list.");
                filterWords.add( args[ 1 ] );
                Main.instance.getConfig().set( "Words", filterWords );
                Main.instance.saveConfig();

                return true;
            }
            else
            {
                player.sendMessage(Config.SignPrefix + " §cThis word is already in the list." );

                return true;
            }
        }

        if( args[ 0 ].equalsIgnoreCase( "remove" ))
        {
            if( args.length == 1 )
            {
                player.sendMessage( Config.SignPrefix + " §ePlease provide a word to being excluded." );

                return true;
            }

            if( filterWords.contains( args[ 1 ] ) )
            {
                player.sendMessage( Config.SignPrefix + " §d" + args[ 1 ] +"§a removed from your list.");
                filterWords.remove( args[ 1 ] );
                Main.instance.getConfig().set( "Words", filterWords );
                Main.instance.saveConfig();

                return true;
            }
            else
            {
                player.sendMessage(Config.SignPrefix + " §cThis word does not exist in the list." );

                return true;
            }
        }

        if( args[ 0 ].equalsIgnoreCase( "reload" ) )
        {
            player.sendMessage( Config.ChatPrefix + " §aConfig has reloaded." );
            new Config().AssignVar();

            return true;
        }

        return false;
    }
}
