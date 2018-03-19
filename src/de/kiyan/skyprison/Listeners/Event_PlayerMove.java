//package de.kiyan.skyprison.Listeners;
//
//import de.kiyan.skyprison.Config;
//import de.kiyan.skyprison.Main;
//import org.bukkit.Material;
//import org.bukkit.World;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerMoveEvent;
//
//import java.io.File;
//import java.util.HashMap;
//
//public class Event_PlayerMove implements Listener
//{
//    public static HashMap< Player, Long > setTimer;
//
//    @EventHandler
//    public void onPlayerMove( PlayerMoveEvent event )
//    {
//        Player player = event.getPlayer( );
//
//        if( player.getLocation( ).getBlock( ).getType( ) == Material.GOLD_PLATE )
//        {
//            player.sendMessage( Config.ParkourPrefix + " §9§lYour timer has been started!§r§l Go run for it!" );
//            setTimer.put( player, ( System.currentTimeMillis( ) / 1000L ) % 60 );
//        }
//    }
//
//    @EventHandler
//    public void onDisapear( Player)
//    {
//
//    }
//
//    public World WhichWorld( Player player )
//    {//Gets the cooldown length and returns the amount of seconds remaining
//        File f = new File( Main.instance.getDataFolder( ) + "/parkour/playerdata/" + player.getUniqueId( ) + ".yml" );
//        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( f );
//
//        if( )
//        return
//    }
//
//}
