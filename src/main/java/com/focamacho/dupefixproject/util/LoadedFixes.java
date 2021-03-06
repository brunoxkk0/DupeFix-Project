package com.focamacho.dupefixproject.util;

import net.minecraftforge.fml.common.Loader;

public class LoadedFixes {

    public static boolean actuallyAdditions = false;
    public static boolean bloodMagic = false;
    public static boolean extraUtilities = false;
    public static boolean industrialForegoing = false;
    public static boolean netherChest = false;
    public static boolean spiceOfLife = false;
    public static boolean thaumcraft = false;
    public static boolean tinyProgressions = false;
    public static boolean enderio = false;
    public static boolean theFarlanders = false;
    public static boolean thaumicWonders = false;
    public static boolean forestry = false;
    public static boolean thermalExpansion = false;

    public static String getModFixesNotLoaded() {
        String modFixesNotLoaded = "";
        if(Loader.isModLoaded("thaumcraft")) {
            if(!LoadedFixes.thaumcraft) modFixesNotLoaded += "Thaumcraft, ";
            if(Loader.isModLoaded("enderio") && !LoadedFixes.enderio) modFixesNotLoaded += "EnderIO, ";
        }
        if(Loader.isModLoaded("bloodmagic") && !LoadedFixes.bloodMagic) modFixesNotLoaded += "Blood Magic, ";
        if(Loader.isModLoaded("extrautils2") && !LoadedFixes.extraUtilities) modFixesNotLoaded += "Extra Utilities 2, ";
        if(Loader.isModLoaded("actuallyadditions") && !LoadedFixes.actuallyAdditions) modFixesNotLoaded += "Actually Additions, ";
        if(Loader.isModLoaded("industrialforegoing") && !LoadedFixes.industrialForegoing) modFixesNotLoaded += "Industrial Foregoing, ";
        if(Loader.isModLoaded("tp") && !LoadedFixes.tinyProgressions) modFixesNotLoaded += "Tiny Progressions, ";
        if(Loader.isModLoaded("spiceoflife") && !LoadedFixes.spiceOfLife) modFixesNotLoaded += "Spice of Life, ";
        if(Loader.isModLoaded("netherchest") && !LoadedFixes.netherChest) modFixesNotLoaded += "NetherChest, ";
        if(Loader.isModLoaded("farlanders") && !LoadedFixes.theFarlanders) modFixesNotLoaded += "The Farlanders, ";
        if(Loader.isModLoaded("thaumicwonders") && !LoadedFixes.thaumicWonders) modFixesNotLoaded += "Thaumic Wonders, ";
        if(Loader.isModLoaded("forestry") && !LoadedFixes.forestry) modFixesNotLoaded += "Forestry, ";
        if(Loader.isModLoaded("thermalexpansion") && !LoadedFixes.thermalExpansion) modFixesNotLoaded += "Thermal Expansion, ";
        return modFixesNotLoaded.isEmpty() ? "" : modFixesNotLoaded.substring(0, modFixesNotLoaded.length() - 2);
    }

}
