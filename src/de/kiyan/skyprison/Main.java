package de.kiyan.skyprison;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import de.kiyan.skyprison.Addon.PotionSpawner;
import de.kiyan.skyprison.Addon.Sponge_LeaderHead;
import de.kiyan.skyprison.Listeners.*;
import de.kiyan.skyprison.Utils.SignChangeDetector;
import de.kiyan.skyprison.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin
{
    private Plugin leaderheads;

    public static Main instance;

    public void onEnable( )
    {
        instance = this;
        CMDSponge.bAlreadySpawned = false;

        saveDefaultConfig( );
        reloadConfig( );

        new Config( ).AssignVar( );
        new CMDSponge( ).spawnSponge( );

        PotionSpawner.PotionRespawner( );
        PotionSpawner.PotionEffects( );

        Bukkit.getServer( ).getPluginManager( ).registerEvents( new Event_CraftItem( ), this );
        Bukkit.getServer( ).getPluginManager( ).registerEvents( new Event_SignChange( ), this );
        Bukkit.getServer( ).getPluginManager( ).registerEvents( new Event_Inventory( ), this );
        Bukkit.getServer( ).getPluginManager( ).registerEvents( new Event_SpongeSearch( ), this );
        Bukkit.getServer( ).getPluginManager( ).registerEvents( new Event_PickupItem( ), this );
        //        Bukkit.getServer( ).getPluginManager( ).registerEvents( new Event_PlayerMove( ), this );

        this.getCommand( "sponge" ).setExecutor( new CMDSponge( ) );
        this.getCommand( "signfilter" ).setExecutor( new CMDSignfilter( ) );
        this.getCommand( "chatfilter" ).setExecutor( new CMDChatfilter( ) );
        this.getCommand( "disablecraft" ).setExecutor( new CMDDisableCraft( ) );
        this.getCommand( "quickmod" ).setExecutor( new CMDQuickMod( ) );
        //this.getCommand( "parkour" ).setExecutor( new CMDParkour( ) );
        this.getCommand( "setpotion" ).setExecutor( new CMDSetPotion( ) );

        this.getServer( ).getMessenger( ).registerOutgoingPluginChannel( this, "QuickMod" );

        leaderheads = Bukkit.getPluginManager( ).getPlugin( "LeaderHeads" );
        if( leaderheads != null )
        {
            new Sponge_LeaderHead( );
        }

        ProtocolManager manager = ProtocolLibrary.getProtocolManager( );

        if( !manager.getPacketListeners( ).contains( new SignChangeDetector( Main.instance, ListenerPriority.NORMAL ) ) )
            manager.addPacketListener( new SignChangeDetector( Main.instance, ListenerPriority.NORMAL ) );

        Bukkit.getScheduler( ).scheduleSyncRepeatingTask( this, new Runnable( )
        {
            @Override
            public void run( )
            {
                gApple( );
            }
        }, 20 * 25, ( 20 * 60 ) * 5 );
    }

    public void onDisable( )
    {
        if( !CMDSponge.listSponges( ).isEmpty( ) )
            for( Location loc : CMDSponge.listSponges( ) )
                loc.getBlock( ).setType( Material.AIR );
    }

    private void gApple( )
    {
        ItemStack notchapple = new ItemStack( Material.GOLDEN_APPLE, 1, ( short ) 1 );

        ShapedRecipe Gapple = new ShapedRecipe( notchapple );
        Gapple.shape( new String[]{ "***", "*%*", "***" } );
        Gapple.setIngredient( '*', Material.GOLD_BLOCK );
        Gapple.setIngredient( '%', Material.APPLE );

        this.getServer( ).addRecipe( Gapple );
    }

    public Main getInstance( )
    {
        return instance;
    }
}
