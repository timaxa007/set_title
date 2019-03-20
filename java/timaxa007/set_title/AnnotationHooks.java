package timaxa007.set_title;

import java.io.File;

import gloomyfolken.hooklib.asm.Hook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.client.ForgeHooksClient;

public class AnnotationHooks {

	@Hook
	public static void createDisplay(ForgeHooksClient clazz) {
		Config.init(new File(Minecraft.getMinecraft().mcDataDir, "/config/" + SetTitleMod.MODID + ".cfg"));
	}

	@Hook
	public static void initializeTextures(OpenGlHelper clazz) {
		Config.cursor();
	}

}
