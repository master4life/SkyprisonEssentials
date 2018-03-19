package de.kiyan.skyprison.commands;

import de.kiyan.skyprison.Config;
import de.kiyan.skyprison.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CMDChatfilter implements CommandExecutor
{
    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args )
    {
        if( !( sender instanceof Player ) )
        {
            sender.sendMessage( "§cYou must be a player" );

            return false;
        }

        if( !label.equalsIgnoreCase( "chatfilter" ) )
        {
            return false;
        }

        Player player = ( Player ) sender;

        if( !player.hasPermission( "subzero.chatfilter" ) )
        {
            player.sendMessage( Config.ChatPrefix + " §cYou dont have enough permissions!" );

            return false;
        }

        File fConfig = new File( "plugins/DeluxeChat/config.yml" );

        if( !fConfig.exists( ) )
        {
            player.sendMessage( Config.ChatPrefix + " §cDeluxeChat is not installed yet." );

            return false;
        }
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( fConfig );

        List< String > filterChat = yaml.getStringList( "chat_filter.list" );

        if( args.length == 0 )
        {
            player.sendMessage( "     §e§l" + Config.ChatPrefix );
            player.sendMessage( "§e§lCensored words :" );
            player.sendMessage( "§d" + filterChat.toString( ) );

            return true;
        }

        if( args[ 0 ].equalsIgnoreCase( "add" ) )
        {
            if( args.length == 1 )
            {
                player.sendMessage( Config.ChatPrefix + " §ePlease provide a word to being excluded." );

                return true;
            }

            if( !filterChat.contains( args[ 1 ] ) )
            {
                player.sendMessage( Config.ChatPrefix + " §d" + args[ 1 ] + "§a added to your list." );
                filterChat.add( args[ 1 ] );
                yaml.set( "chat_filter.list", filterChat );
                saveFilter( yaml );

                return true;
            } else
            {
                player.sendMessage( Config.ChatPrefix + " §cThis word is already in the list." );

                return true;
            }
        }

        if( args[ 0 ].equalsIgnoreCase( "remove" ) )
        {
            if( args.length == 1 )
            {
                player.sendMessage( Config.ChatPrefix + " §ePlease provide a word to being excluded." );

                return true;
            }

            if( filterChat.contains( args[ 1 ] ) )
            {
                player.sendMessage( Config.ChatPrefix + " §d" + args[ 1 ] + "§a removed from your list." );
                filterChat.remove( args[ 1 ] );
                yaml.set( "chat_filter.list", filterChat );
                saveFilter( yaml );

                return true;
            } else
            {
                player.sendMessage( Config.ChatPrefix + " §cThis word does not exist in the list." );

                return true;
            }
        }
        if( args[ 0 ].equalsIgnoreCase( "reload" ) )
        {
            player.sendMessage( Config.ChatPrefix + " §aConfig has reloaded." );
            new Config( ).AssignVar( );

            return true;
        }

        return false;
    }

    private void saveFilter( YamlConfiguration yaml )
    {
        try
        {
            yaml.save( "plugins/DeluxeChat/config.yml" );
        } catch( IOException ex )
        {
            ex.printStackTrace( );
        }
    }
}
