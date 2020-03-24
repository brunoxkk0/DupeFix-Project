package com.focamacho.dupefixproject;

import org.apache.logging.log4j.Logger;

import com.focamacho.dupefixproject.fixes.AstralSorceryFixes;
import com.focamacho.dupefixproject.fixes.CyclicFixes;
import com.focamacho.dupefixproject.fixes.MekanismFixes;
import com.focamacho.dupefixproject.fixes.ProjectRedWorldFixes;
import com.focamacho.dupefixproject.fixes.SpiceOfLifeFixes;
import com.focamacho.dupefixproject.fixes.TConstructFixes;
import com.focamacho.dupefixproject.fixes.ThaumcraftEnderIOFixes;
import com.focamacho.dupefixproject.fixes.ThaumcraftFixes;
import com.focamacho.dupefixproject.fixes.TinyProgressionsFixes;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DupeFixProject.MODID, name = DupeFixProject.NAME, version = DupeFixProject.VERSION, acceptableRemoteVersions = "*")
public class DupeFixProject {
	
    public static final String MODID = "dupefixproject";
    public static final String NAME = "DupeFix Project";
    public static final String VERSION = "1.8";

    public static Logger logger;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	//Thaumcraft
        if(Loader.isModLoaded("thaumcraft")) {
        	MinecraftForge.EVENT_BUS.register(new ThaumcraftFixes());
        	if(Loader.isModLoaded("enderio")) {
        		MinecraftForge.EVENT_BUS.register(new ThaumcraftEnderIOFixes());
        	}
        	logger.info("Thaumcraft Fixes Loaded");
        }
        //Tinkers' Construct
        if(Loader.isModLoaded("tconstruct")) {
        	MinecraftForge.EVENT_BUS.register(new TConstructFixes());
        	logger.info("Tinkers' Construct Fixes Loaded");
        }
        //Tiny Progressions
        if(Loader.isModLoaded("tp")) {
        	MinecraftForge.EVENT_BUS.register(new TinyProgressionsFixes());
        	logger.info("Tiny Progressions Fixes Loaded");
        }
        //Spice of Life
        if(Loader.isModLoaded("spiceoflife")) {
        	MinecraftForge.EVENT_BUS.register(new SpiceOfLifeFixes());
        	logger.info("Spice of Life Fixes Loaded");
        }
        //Project Red World
        if(Loader.isModLoaded("projectred-exploration")) {
        	MinecraftForge.EVENT_BUS.register(new ProjectRedWorldFixes());
        	logger.info("Project Red World Fixes Loaded");
        }
        //Mekanism
        if(Loader.isModLoaded("mekanism")) {
        	MinecraftForge.EVENT_BUS.register(new MekanismFixes());
        	MekanismFixes.init();
        	logger.info("Mekanism Fixes Loaded");
        }
        //Cyclic
        if(Loader.isModLoaded("cyclicmagic")) {
        	MinecraftForge.EVENT_BUS.register(new CyclicFixes());
        	logger.info("Cyclic Fixes Loaded");
        }
        //Astral Sorcery
        if(Loader.isModLoaded("astralsorcery")) {
        	MinecraftForge.EVENT_BUS.register(new AstralSorceryFixes());
        	logger.info("Astral Sorcery Fixes Loaded");
        }
    }
    
}
