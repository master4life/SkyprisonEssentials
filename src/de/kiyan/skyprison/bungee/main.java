package de.kiyan.skyprison.bungee;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class main extends Plugin implements Listener
{
    @Override
    public void onEnable( )
    {
        BungeeCord.getInstance( ).getPluginManager( ).registerListener( this, this );
        BungeeCord.getInstance( ).registerChannel( "QuickMod" );
    }

    @EventHandler
    public void onPluginMessage( PluginMessageEvent event )
    {
        if( event.getTag( ).equalsIgnoreCase( "QuickMod" ) )
        {
            DataInputStream in = new DataInputStream( new ByteArrayInputStream( event.getData( ) ) );
            try
            {
                String channel = in.readUTF( );
                if( channel.equals( "QuickMod" ) )
                {
                    ProxiedPlayer player = ( ProxiedPlayer ) event.getReceiver( );
                    if( !( player instanceof ProxiedPlayer ) )
                    {
                        return;
                    }

                    String message = in.readUTF( );

                    System.out.print( message );
                    ProxyServer.getInstance( ).getPluginManager( ).dispatchCommand( player, message );
                }
            } catch( IOException ex )
            {
                ex.printStackTrace( );
            }

        }
    }
}
