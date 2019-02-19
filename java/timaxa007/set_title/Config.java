package timaxa007.set_title;

import java.io.File;

import org.lwjgl.opengl.Display;

import net.minecraftforge.common.config.Configuration;

/**https://forum.mcmodding.ru/threads/1-7-build-1147-%D0%A2%D1%83%D1%82%D0%BE%D1%80%D0%B8%D0%B0%D0%BB-%D0%9A%D0%B0%D0%BA-%D1%81%D0%B4%D0%B5%D0%BB%D0%B0%D1%82%D1%8C-%D0%BA%D0%BE%D0%BD%D1%84%D0%B8%D0%B3-%D1%81-%D0%B3%D1%83%D0%B8.4682/post-49710**/
public class Config {

	public static Configuration config;
	public static final String categoryGui = "gui";

	public static String title;

	public static void init(File file) {
		config = new Configuration(file);
		config.load();
		syncConfig();
	}

	public static void syncConfig() {
		title = config.get(categoryGui, "scale", "Minecraft 1.7.10 Custom Title").getString();
		if (config.hasChanged()) {
			Display.setTitle(title);
			config.save();
		}
	}

}
