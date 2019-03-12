package timaxa007.set_title;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.common.config.Configuration;

/**https://forum.mcmodding.ru/threads/1-7-build-1147-%D0%A2%D1%83%D1%82%D0%BE%D1%80%D0%B8%D0%B0%D0%BB-%D0%9A%D0%B0%D0%BA-%D1%81%D0%B4%D0%B5%D0%BB%D0%B0%D1%82%D1%8C-%D0%BA%D0%BE%D0%BD%D1%84%D0%B8%D0%B3-%D1%81-%D0%B3%D1%83%D0%B8.4682/post-49710**/
public class Config {

	public static Configuration config;
	public static final String categoryGui = "window";

	public static String
	title,
	icon16x16,
	icon32x32
	;

	public static void init(File file) {
		config = new Configuration(file);
		config.load();
		syncConfig();
	}

	public static void syncConfig() {
		title = config.get(categoryGui, "Custom Title", "Minecraft 1.7.10 Custom Title").getString();
		Display.setTitle(title);

		icon16x16 = config.get(categoryGui, "Icon 16x16", SetTitleMod.MODID + ":icons/icon_16x16.png").getString();
		icon32x32 = config.get(categoryGui, "Icon 32x32", SetTitleMod.MODID + ":icons/icon_32x32.png").getString();
		ResourceLocation rl_icon16x16 = new ResourceLocation(icon16x16);
		ResourceLocation rl_icon32x32 = new ResourceLocation(icon32x32);

		Util.EnumOS enumos = Util.getOSType();

		if (enumos != Util.EnumOS.OSX) {
			try {
				InputStream inputstream = Config.class.getResourceAsStream("/assets/" + rl_icon16x16.getResourceDomain() + "/" + rl_icon16x16.getResourcePath());;
				InputStream inputstream1 = Config.class.getResourceAsStream("/assets/" + rl_icon32x32.getResourceDomain() + "/" + rl_icon32x32.getResourcePath());;

				if (inputstream != null && inputstream1 != null) {
					Display.setIcon(new ByteBuffer[] {func_152340_a(inputstream), func_152340_a(inputstream1)});
				}
			}
			catch (IOException ioexception) {
				System.err.println("Couldn\'t set icon");
			}
		}

		if (config.hasChanged()) config.save();
	}

	private static ByteBuffer func_152340_a(InputStream p_152340_1_) throws IOException {
		BufferedImage bufferedimage = ImageIO.read(p_152340_1_);
		int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
		ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
		int[] aint1 = aint;
		int i = aint.length;

		for (int j = 0; j < i; ++j) {
			int k = aint1[j];
			bytebuffer.putInt(k << 8 | k >> 24 & 255);
		}

		bytebuffer.flip();
		return bytebuffer;
	}

}
