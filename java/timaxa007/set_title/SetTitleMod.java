package timaxa007.set_title;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SetTitleMod.MODID, name = SetTitleMod.NAME, version = SetTitleMod.VERSION, guiFactory = "timaxa007.set_title.GuiFactory")
public class SetTitleMod {

	public static final String
	MODID = "set_title",
	NAME = "Set Title Mod",
	VERSION = "1.2";

	@Mod.Instance(MODID)
	public static SetTitleMod instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//Config.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new Events());
	}

}
