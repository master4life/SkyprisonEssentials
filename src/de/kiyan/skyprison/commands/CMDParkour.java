//package de.kiyan.skyprison.commands;
//
//import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
//import com.sk89q.worldguard.protection.regions.ProtectedRegion;
//import de.kiyan.skyprison.Main;
//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
//import org.bukkit.Location;
//import org.bukkit.Material;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class CMDParkour implements CommandExecutor
//{
//    static enum ParkourType //Parkour types
//    {
//        starter, pirate_sea, jungle_jumble, frozen, sand_temple, mine_shaft, redstone, skyscrapes, parkourcenter;
//    }
//
//    @Override
//    public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args )
//    {
//        if( !( sender instanceof Player ) )
//        {
//            sender.sendMessage( "§cYou must be a player" );
//
//            return false;
//        }
//
//        if( !label.equalsIgnoreCase( "parkour" ) )
//        {
//            return false;
//        }
//
//        Player player = ( Player ) sender;
//
//        ParkourGUIOpen( player );
//
//        return false;
//    }
//
//    public void ParkourGUIOpen( Player p )//Opens Gui when called
//    {
//        Inventory inv = Bukkit.createInventory( p, 9, "§b§lSkyPrison Parkour" );
//
//        createInventory( 0, Material.DIRT, , "§rParkour 1", "§2Difficult: §2§lEasy", "§eLength: §b§lShort", "§7Token reward: §a" + TokenReward( "starter" ) + "§7 Money reward:§a $" + CashReward( "starter" ), ParkourType.starter.toString( ), p, inv );
//        createInventory( 1, Material.LEAVES, "§aPirate Sea", "§rParkour 2", "§2Difficulty: §2§lEasy", "§eLength: §1§lLong", "§7Token reward: §a" + TokenReward( "pirate_sea" ) + "§7 Money reward:§a $" + CashReward( "pirate_sea" ), ParkourType.pirate_sea.toString( ), p, inv );
//        createInventory( 2, Material.LOG, "§2Jungle Jumble", "§rParkour 3", "§2Difficulty: §2§lEasy/§6§lMedium", "§eLength: §b§lShort", "§7Token reward: §a" + TokenReward( "jungle_jumble" ) + "§7 Money reward:§a $" + CashReward( "jungle_jumble" ), ParkourType.jungle_jumble.toString( ), p, inv );
//        createInventory( 3, Material.SNOW_BLOCK, "§fFrozen", "§rParkour 4", "§2Difficulty: §6§lMedium", "§eLength: §9§lNormal", "§7Token reward: §a" + TokenReward( "frozen" ) + "§7 Money reward:§a $" + CashReward( "frozen" ), ParkourType.frozen.toString( ), p, inv );
//        createInventory( 4, Material.SANDSTONE, "§eSand Temple", "§rParkour 5", "§2Difficulty: §6§lMedium", "§eLength: §1§lLong", "§7Token reward: §a" + TokenReward( "sand_temple" ) + "§7 Money reward:§a $" + CashReward( "sand_temple" ), ParkourType.sand_temple.toString( ), p, inv );
//        createInventory( 5, Material.RAILS, "§6Mineshaft", "§rParkour 6", "§2Difficulty: §6§lMedium/§4§lHard", "§eLength: §9§lNormal", "§7Token reward: §a" + TokenReward( "mine_shaft" ) + "§7 Money reward:§a $" + CashReward( "mine_shaft" ), ParkourType.mine_shaft.toString( ), p, inv );
//        createInventory( 6, Material.REDSTONE_BLOCK, "§4Redstone", "§rParkour 7", "§2Difficulty: §4§lHard", "§eLength: §b§lShort", "§7Token reward: §a" + TokenReward( "redstone" ) + "§7 Money reward:§a $" + CashReward( "redstone" ), ParkourType.redstone.toString( ), p, inv );
//        createInventory( 7, Material.LADDER, "§8SkyScrapes", "§rParkour 8", "§2Difficulty: §9§lExtreme!", "§eLength: §1§lLong", "§7Token reward: §a" + TokenReward( "skyscrapers" ) + "§7 Money reward:§a $" + CashReward( "skyscrapes" ), ParkourType.skyscrapes.toString( ), p, inv );
//        createParkCentInv( 8, Material.COMPASS, "§bParkour Center", inv );
//
//        p.openInventory( inv );
//    }
//
//    public void createParkCentInv( int Slot, Material material, String name, Inventory inv ) //Inventory spot for parkour center
//    {
//        ItemStack item = new ItemStack( material );
//        ItemMeta meta = item.getItemMeta( );
//        meta.setDisplayName( name );
//        item.setItemMeta( meta );
//        inv.setItem( Slot, item );
//    }
//
//    public void createInventory( int Slot, Material material, String name, String lore, String lore2, String lore3, String lore4, String type, Player p, Inventory inv ) //create each inventory spot
//    {
//        ItemStack item = new ItemStack( material );
//        ItemMeta meta = item.getItemMeta( );
//        meta.setDisplayName( name );
//        ArrayList< String > Lore = new ArrayList<>( );
//
//        Lore.add( lore );
//        Lore.add( lore2 );
//        Lore.add( lore3 );
//        Lore.add( lore4 );
//        String lore5;
//        if( CheckCooldown( p, type, "completed" ) )
//        {
//            lore5 = "§4§lReward Available: " + Main.instance.LongToRemaining( CooldownLength( p, type, "completed" ) );
//        } else
//        {
//            lore5 = "§2§lReward Available";
//        }
//        Lore.add( lore5 );
//
//        meta.setLore( Lore );
//        item.setItemMeta( meta );
//
//        inv.setItem( Slot, item );
//    }
//
//    public Integer ParkourGUICheck( Player player, Inventory inventory, Integer slot, Integer rawslot, Integer cursoramount )
//    {//Runs on a InventoryClickEvent and checks if the inventory is the parkour inv then checks which item was clicked - Return of 1 cancels the event
//        if( ChatColor.stripColor( inventory.getName( ) ).equalsIgnoreCase( "SkyPrison Parkour" ) )
//        {
//            if( rawslot != slot || cursoramount > 0 )
//            {
//                return 1;
//            } else
//            {
//                String map = "";
//                if( slot == 0 )
//                {
//                    map = "parkour_starter";
//                    player.sendMessage( "§c[§BParkour§c] §9You have been sent to the §bStarter §9parkour!" );
//                } else if( slot == 1 )
//                {
//                    map = "parkour_pirate_sea";
//                    player.sendMessage( "§c[§BParkour§c] §9You have been sent to the §bPirate Sea §9parkour!" );
//                } else if( slot == 2 )
//                {
//                    map = "parkour_jungle_jumble";
//                    player.sendMessage( "§c[§BParkour§c] §9You have been sent to the §bJungle Jumble §9parkour!" );
//                } else if( slot == 3 )
//                {
//                    map = "parkour_frozen";
//                    player.sendMessage( "§c[§BParkour§c] §9You have been sent to the §bFrozen §9parkour!" );
//                } else if( slot == 4 )
//                {
//                    map = "parkour_sand_temple";
//                    player.sendMessage( "§c[§BParkour§c] §9You have been sent to the §bSand Temple §9parkour!" );
//                } else if( slot == 5 )
//                {
//                    map = "parkour_mine_shaft";
//                    player.sendMessage( "§c[§BParkour§c] §9You have been sent to the §bMine Shaft §9parkour!" );
//                } else if( slot == 6 )
//                {
//                    map = "parkour_redstone";
//                    player.sendMessage( "§c[§BParkour§c] §9You have been sent to the §bRedstone §9parkour!" );
//                } else if( slot == 7 )
//                {
//                    map = "parkour_skyscrapes";
//                    player.sendMessage( "§c[§BParkour§c] §9You have been sent to the §bSkyscrapes §9parkour!" );
//                } else if( slot == 8 )
//                {
//                    map = "parkours";
//                    player.sendMessage( "§c[§BParkour§c] §9You have been sent to the §bParkour Center§9!" );
//                }
//                File f2 = new File( "plugins/Essentials/warps/" + map + ".yml" );
//                YamlConfiguration yaml2 = YamlConfiguration.loadConfiguration( f2 );
//                String structure = "";
//                ParkourTeleportation( player, yaml2, structure );
//                player.setFoodLevel( 20 );
//                player.setHealth( 20 );
//                player.setFireTicks( 0 );
//                return 1;
//            }
//        }
//        return 0;
//    }
//
//    public void ParkourFall( Player p )
//    {//checks if a player is in a parkour region and determines if a player should be sent to the checkpoint or
//        for( ProtectedRegion r : ( ( WorldGuardPlugin ) Bukkit.getPluginManager( ).getPlugin( "WorldGuard" ) ).getRegionManager( p.getWorld( ) ).getApplicableRegions( p.getLocation( ) ) )
//        {
//            String map = "";
//            if( r.getId( ).equalsIgnoreCase( "parkourwater_starter" ) )
//                map = "starter";
//            else if( r.getId( ).equalsIgnoreCase( "parkourwater_piratesea" ) )
//                map = "pirate_sea";
//            else if( r.getId( ).equalsIgnoreCase( "parkourwater_junglejumble" ) )
//                map = "jungle_jumble";
//            else if( r.getId( ).equalsIgnoreCase( "parkourwater_frozen" ) )
//                map = "frozen";
//            else if( r.getId( ).equalsIgnoreCase( "parkourwater_sandtemple" ) )
//                map = "sand_temple";
//            else if( r.getId( ).equalsIgnoreCase( "parkourwater_mineshaft" ) || r.getId( ).equalsIgnoreCase( "parkourwater_mineshaft_2" ) || r.getId( ).equalsIgnoreCase( "parkourwater_mineshaft_3" ) || r.getId( ).equalsIgnoreCase( "parkourwater_mineshaft_4" ) || r.getId( ).equalsIgnoreCase( "parkourwater_mineshaft_5" ) || r.getId( ).equalsIgnoreCase( "parkourwater_mineshaft_6" ) || r.getId( ).equalsIgnoreCase( "parkourwater_mineshaft_7" ) )
//                map = "mine_shaft";
//            else if( r.getId( ).equalsIgnoreCase( "parkourwater_redstone" ) )
//                map = "redstone";
//            else if( r.getId( ).equalsIgnoreCase( "parkourwater_skyscrapes" ) )
//                map = "skyscrapes";
//            if( !map.equalsIgnoreCase( "" ) )
//            {
//                File f = new File( Main.instance.getDataFolder( ) + "/parkour/playerdata/" + p.getUniqueId( ) + ".yml" );
//                YamlConfiguration yaml = YamlConfiguration.loadConfiguration( f );
//                Integer checktime = ( Integer ) yaml.get( map + ".checkpoint.time" );
//                Long current = System.currentTimeMillis( ) / 1000L;
//                if( current - checktime.longValue( ) <= 43200 )
//                {
//                    String structure = map + ".checkpoint.";
//                    ParkourTeleportation( p, yaml, structure );
//                    return;
//                }
//                File f2 = new File( "plugins/Essentials/warps/parkour_" + map + ".yml" );
//                YamlConfiguration yaml2 = YamlConfiguration.loadConfiguration( f2 );
//                String structure = "";
//                ParkourTeleportation( p, yaml2, structure );
//                return;
//            }
//        }
//    }
//
//
//    public void ParkourCompletion( Player p, String map, String mapname )
//    {//Activates upon command /rh8park complete <map> <Map Display Name>
//        if( CheckCooldown( p, map, "completed" ) )
//        {
//            p.sendMessage( "§c[§BParkour§c] §9You need to wait §c" + main.LongToRemaining( CooldownLength( p, map, "completed" ) ) + "§9 before you can claim another reward for this parkour. (Check /parkour to choose a different parkour or see when you can complete this parkour again)" );
//            return;
//        } else
//        {
//            File f = new File( Main.instance.getDataFolder( ) + "/parkour/playerdata/" + p.getUniqueId( ) + ".yml" );
//            YamlConfiguration yaml = YamlConfiguration.loadConfiguration( f );
//            yaml.set( map + ".completed.bool", true );
//            yaml.set( map + ".completed.time", System.currentTimeMillis( ) / 1000L );
//            Integer completed = 0;
//            for( ParkourType types : ParkourType.values( ) )
//            {
//                if( yaml.get( types + ".completed.bool" ).equals( true ) )
//                {
//                    completed++;
//                }
//            }
//            p.sendMessage( "§c[§BParkour§c] §9CONGRATULATIONS! You have completed the\n §b " + mapname + "§9 Parkour! You have been rewarded §b" + TokenReward( map ) + " tokens§9 and §b$" + CashReward( map ) + ". §9You have completed §c" + completed + "/8 Parkours§9!" );
//            Bukkit.getServer( ).dispatchCommand( Bukkit.getServer( ).getConsoleSender( ), "runalias tokenadmin give " + p.getName( ) + " " + TokenReward( map ) );
//            Bukkit.getServer( ).dispatchCommand( Bukkit.getServer( ).getConsoleSender( ), "eco give " + p.getName( ) + " " + CashReward( map ) );
//            yaml.set( map + ".checkpoint.time", 0 );
//            if( completed == 8 && yaml.get( "completedall" ).equals( false ) )
//            {
//                Bukkit.getServer( ).broadcastMessage( "§e" + p.getName( ) + " has just completed all eight parkours! Do /parkour to check your own progress!" );
//                yaml.set( "completedall", true );
//            }
//            try
//            {
//                yaml.save( f );
//            } catch( IOException e )
//            {
//                e.printStackTrace( );
//            }
//            return;
//        }
//    }
//
//    public void ParkourCheckpoint( Player player, String map )
//    {//Sets a checkpoint and saves it in the player's datafile
//        File f = new File( Main.instance.getDataFolder( ) + "/parkour/playerdata/" + player.getUniqueId( ) + ".yml" );
//        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( f );
//        if( !f.exists( ) )
//        {
//            try
//            {
//                f.createNewFile( );
//            } catch( IOException var13 )
//            {
//                var13.printStackTrace( );
//            }
//            for( ParkourType types : ParkourType.values( ) )
//            {
//                yaml.set( types + ".checkpoint.time", 0 );
//                yaml.set( types + ".checkpoint.world", player.getLocation( ).getWorld( ).getName( ) );
//                yaml.set( types + ".checkpoint.x", 0 );
//                yaml.set( types + ".checkpoint.y", 0 );
//                yaml.set( types + ".checkpoint.z", 0 );
//                yaml.set( types + ".checkpoint.yaw", 0 );
//                yaml.set( types + ".checkpoint.pitch", 0 );
//                yaml.set( types + ".completed.time", 0 );
//                yaml.set( types + ".completed.bool", false );
//                yaml.set( "completedall", false );
//            }
//            try
//            {
//                yaml.save( f );
//            } catch( IOException e )
//            {
//                e.printStackTrace( );
//            }
//        }
//        yaml.set( map + ".checkpoint.time", System.currentTimeMillis( ) / 1000L );
//        yaml.set( map + ".checkpoint.world", player.getLocation( ).getWorld( ).getName( ) );
//        yaml.set( map + ".checkpoint.x", Double.valueOf( player.getLocation( ).getX( ) ) );
//        yaml.set( map + ".checkpoint.y", Double.valueOf( player.getLocation( ).getY( ) ) );
//        yaml.set( map + ".checkpoint.z", Double.valueOf( player.getLocation( ).getZ( ) ) );
//        yaml.set( map + ".checkpoint.yaw", Float.valueOf( player.getLocation( ).getYaw( ) ) );
//        yaml.set( map + ".checkpoint.pitch", Float.valueOf( player.getLocation( ).getPitch( ) ) );
//        try
//        {
//            yaml.save( f );
//        } catch( IOException e )
//        {
//            e.printStackTrace( );
//        }
//        player.sendMessage( "§c[§BParkour§c] §9You have successfully set a checkpoint at your current location! This check point will expire in §c12 Hours§9!" );
//        return;
//    }
//
//
//    //////////////////////////////////////////
//    //Internal called methods
//
//
//    public void ParkourTeleportation( Player p, YamlConfiguration yaml, String structure )
//    {//teleports a player - structure is used to change between warp file setup and player file setup
//        String w = ( String ) yaml.get( structure + "world" );
//        Double x = ( Double ) yaml.getDouble( "" + structure + "x" );
//        Double y = ( Double ) yaml.getDouble( "" + structure + "y" );
//        Double z = ( Double ) yaml.getDouble( "" + structure + "z" );
//        float yaw = yaml.getLong( "" + structure + "yaw" );
//        float pitch = yaml.getLong( "" + structure + "pitch" );
//        p.teleport( new Location( Bukkit.getWorld( w ), x, y, z, yaw, pitch ) );
//        return;
//    }
//
//
//    public Boolean CheckCooldown( Player player, String type, String cooldowntype )//checks if a player has a cooldown for a map - returns true for in a cooldown
//    {
//        File f = new File( Main.instance.getDataFolder( ) + "/parkour/playerdata/" + player.getUniqueId( ) + ".yml" );
//        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( f );
//        if( !f.exists( ) )
//        {
//            try
//            {
//                f.createNewFile( );
//            } catch( IOException var13 )
//            {
//                var13.printStackTrace( );
//            }
//            for( ParkourType types : ParkourType.values( ) )
//            {
//                yaml.set( types + ".checkpoint.time", 0 );
//                yaml.set( types + ".checkpoint.world", null );
//                yaml.set( types + ".checkpoint.x", 0 );
//                yaml.set( types + ".checkpoint.y", 0 );
//                yaml.set( types + ".checkpoint.z", 0 );
//                yaml.set( types + ".checkpoint.yaw", 0 );
//                yaml.set( types + ".checkpoint.pitch", 0 );
//                yaml.set( types + ".completed.time", 0 );
//                yaml.set( types + ".completed.bool", false );
//                yaml.set( "completedall", false );
//            }
//            try
//            {
//                yaml.save( f );
//            } catch( IOException e )
//            {
//                e.printStackTrace( );
//            }
//        }
//        if( CooldownLength( player, type, cooldowntype ) >= 0 )
//        {
//            return true;
//        }
//        return false;
//    }
//
//
//    public Long CooldownLength( Player player, String type, String cooldown )
//    {//Gets the cooldown length and returns the amount of seconds remaining
//        File f = new File( Main.instance.getDataFolder( ) + "/parkour/playerdata/" + player.getUniqueId( ) + ".yml" );
//        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( f );
//        Long current = System.currentTimeMillis( ) / 1000L;
//        Integer completed = ( Integer ) yaml.get( type + "." + cooldown + ".time" );
//        Long remaining;
//        remaining = 86400 - ( current - completed );
//        return remaining;
//    }
//
//    public Integer TokenReward( String type )
//    { //gets the token reward for a map
//        File f = new File( Main.instance.getDataFolder( ) + "/parkour/mapdata/maps.yml" );
//        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( f );
//        Integer reward = ( Integer ) yaml.get( type + ".tokenreward" );
//        return reward;
//    }
//
//    public Integer CashReward( String type )
//    { //gets the cash reward for a map
//        File f = new File( Main.instance.getDataFolder( ) + "/parkour/mapdata/maps.yml" );
//        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( f );
//        Integer reward = ( Integer ) yaml.get( type + ".cashreward" );
//        return reward;
//    }
//}
