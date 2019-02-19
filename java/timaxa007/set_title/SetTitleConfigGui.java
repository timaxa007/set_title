package timaxa007.set_title;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class SetTitleConfigGui extends GuiConfig {

	public SetTitleConfigGui(GuiScreen parent) {
		super(parent, new ConfigElement(Config.config.getCategory(Config.categoryGui)).getChildElements(),
				SetTitleMod.MODID, false, false, GuiConfig.getAbridgedConfigPath(Config.config.toString()));
	}

}
