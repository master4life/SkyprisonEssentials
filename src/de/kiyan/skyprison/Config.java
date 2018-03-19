package de.kiyan.skyprison;

import java.time.Duration;
import java.util.List;

public class Config
{
    public static String SignPrefix, ChatPrefix, SignFirstLine, SignSecondLine, WarnMessage, Message, DisablePrefix;
    public static List< String > filterWords;

    // Sponges
    public static String SpongePrefix;
    public static Integer MaxSponges, SpongeReward, delaySponge;

    // Jump Potion
    public static String PotionPrefix;
    public static Double PotionMin, PotionMax;

    // Quick mod
    public static String QuickmodPrefix, QuickmodDisplay;

    // Parkour
    public static String ParkourPrefix;

    // Minions
    // public static String MinionPrefix;

    public void AssignVar( )
    {
        SignPrefix = Main.instance.getConfig( ).getString( "SignPrefix" ).replaceAll( "&", "§" );
        ChatPrefix = Main.instance.getConfig( ).getString( "ChatPrefix" ).replaceAll( "&", "§" );
        DisablePrefix = Main.instance.getConfig( ).getString( "DisablePrefix" ).replaceAll( "&", "§" );
        SignFirstLine = Main.instance.getConfig( ).getString( "SignFirstLine" );
        SignSecondLine = Main.instance.getConfig( ).getString( "SignSecondLine" );
        WarnMessage = Main.instance.getConfig( ).getString( "WarnMessage" ).replaceAll( "&", "§" );
        Message = Main.instance.getConfig( ).getString( "Message" ).replaceAll( "&", "§" );
        filterWords = Main.instance.getConfig( ).getStringList( "Words" );

        // Sponge
        SpongePrefix = Main.instance.getConfig( ).getString( "Sponge.SpongePrefix" ).replaceAll( "&", "§" );
        MaxSponges = Main.instance.getConfig( ).getInt( "Sponge.MaxSponge" );
        SpongeReward = Main.instance.getConfig( ).getInt( "Sponge.Reward" );
        delaySponge = Main.instance.getConfig( ).getInt( "Sponge.delayNext" );

        // Potion
        PotionPrefix = Main.instance.getConfig( ).getString( "Potions.PotionPrefix" ).replaceAll( "&", "§" );
        PotionMin = Main.instance.getConfig( ).getDouble( "Potions.JumpMin" );
        PotionMax = Main.instance.getConfig( ).getDouble( "Potions.JumpMax" );

        // QuickMod
        QuickmodPrefix = Main.instance.getConfig( ).getString( "QuickMod.QuickmodPrefix" ).replaceAll( "&", "§" );
        QuickmodDisplay = Main.instance.getConfig( ).getString( "QuickMod.QuickmodDisplay" ).replaceAll( "&", "§" );

        // Parkour
        ParkourPrefix = Main.instance.getConfig( ).getString( "Parkour.ParkourPrefix" ).replaceAll( "&", "§" );

        // Monions

        //MinionPrefix = Main.instance.getConfig().getString( "Minions.MinionPrefix" );
    }
}
