package de.kiyan.skyprison.Listeners;

import de.kiyan.skyprison.Config;
import de.kiyan.skyprison.Main;
import de.kiyan.skyprison.Utils.SignInputAPI;
import de.kiyan.skyprison.Utils.SignInputEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Event_Inventory implements Listener
{
    private String target, function;

    @EventHandler
    public void activityInventory( InventoryClickEvent event )
    {
        Inventory inv = event.getInventory( );

        if( inv.getName( ).equalsIgnoreCase( Config.QuickmodDisplay ) )
        {
            Player player = ( Player ) event.getWhoClicked( );
            ItemStack clicked = event.getCurrentItem( );
            SignInputAPI Sign = new SignInputAPI( );

            if( !( player instanceof Player ) )
            {
                return;
            }

            if( clicked.getType( ) == Material.SKULL_ITEM )
            {
                event.setCancelled( true );
            }
            if( clicked.getType( ) == Material.LEATHER_BOOTS )
            {
                event.setCancelled( true );

                player.closeInventory( );
                target = clicked.getItemMeta( ).getLore( ).get( 6 );
                function = "kick";

                Sign.openEditor( player );
            }

            if( clicked.getType( ) == Material.SIGN )
            {
                event.setCancelled( true );

                player.closeInventory( );
                target = clicked.getItemMeta( ).getLore( ).get( 5 );
                function = "warn";

                Sign.openEditor( player );
            }
            if( clicked.getType( ) == Material.LAPIS_BLOCK )
            {
                event.setCancelled( true );

                player.closeInventory( );
                target = clicked.getItemMeta( ).getLore( ).get( 7 );
                function = "jail";

                Sign.openEditor( player );
            }
            if( clicked.getType( ) == Material.BEACON )
            {
                event.setCancelled( true );

                player.closeInventory( );
                target = clicked.getItemMeta( ).getLore( ).get( 6 );
                function = "tempmute";

                Sign.openEditor( player );
            }
            if( clicked.getType( ) == Material.BARRIER )
            {
                event.setCancelled( true );

                player.closeInventory( );
                target = clicked.getItemMeta( ).getLore( ).get( 4 );
                function = "mute";

                sendToBungeeCord( player, "temp" + function + " " + ChatColor.stripColor( target ) + " 1h Advertisement" );
            }
            if( clicked.getType( ) == Material.REDSTONE )
            {
                event.setCancelled( true );

                player.closeInventory( );
                target = clicked.getItemMeta( ).getLore( ).get( 6 );
                function = "tempban";

                Sign.openEditor( player );
            }
        }
    }

    @EventHandler
    public void SignInput( SignInputEvent event )
    {
        Player player = ( Player ) event.getPlayer( );
        Bukkit.getScheduler( ).scheduleSyncDelayedTask( Main.instance, new Runnable( )
        {
            @Override
            public void run( )
            {
                String signLines = Arrays.toString( event.getLines( ) ).replace( ",", "" ).replace( "[", "" ).replace( "]", "" ).trim( );

                if( function == "tempmute" )
                {
                    sendToBungeeCord( player, function + " " + ChatColor.stripColor( target ) + " 30m " + signLines );
                } else if( function == "jail" )
                {
                    player.chat( "/" + function + " " + ChatColor.stripColor( target ) + " " + signLines );
                } else if( function == "tempban" )
                {
                    sendToBungeeCord( player, function + " " + ChatColor.stripColor( target ) + " 24h " + signLines );
                } else if( function == "warn" )
                {
                    sendToBungeeCord( player, function + " " + ChatColor.stripColor( target ) + " " + signLines );
                } else if( function == "kick" )
                {
                    sendToBungeeCord( player, function + " " + ChatColor.stripColor( target ) + " 30m " + signLines );
                }
            }
        }, 5 );
    }

    public void sendToBungeeCord( Player player, String sub )
    {
        ByteArrayOutputStream b = new ByteArrayOutputStream( );
        DataOutputStream out = new DataOutputStream( b );
        try
        {
            out.writeUTF( "QuickMod" );
            out.writeUTF( sub );
        } catch( IOException e )
        {
            e.printStackTrace( );
        }

        player.sendPluginMessage( Main.getPlugin( Main.class ), "QuickMod", b.toByteArray( ) );
    }

}
