package timaxa007.set_title;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.common.config.Configuration;

/**https://forum.mcmodding.ru/threads/1-7-build-1147-%D0%A2%D1%83%D1%82%D0%BE%D1%80%D0%B8%D0%B0%D0%BB-%D0%9A%D0%B0%D0%BA-%D1%81%D0%B4%D0%B5%D0%BB%D0%B0%D1%82%D1%8C-%D0%BA%D0%BE%D0%BD%D1%84%D0%B8%D0%B3-%D1%81-%D0%B3%D1%83%D0%B8.4682/post-49710**/
public class Config {

	public static Configuration config;
	public static final String categoryGui = "window";

	private static boolean isInit;

	public static String
	title,
	cursor,
	icon16x16,
	icon32x32
	;

	public static void init(File file) {
		isInit = true;
		config = new Configuration(file);
		config.load();
		syncConfig();
		isInit = false;
	}

	public static void syncConfig() {
		title = config.get(categoryGui, "Custom Title", /*"Minecraft 1.7.10 Custom Title"*/"").getString();
		if (title != null && title.trim().length() > 0)
			Display.setTitle(title);
		//-----------------------------------------------------------------------
		if (!isInit) cursor();
		//-----------------------------------------------------------------------
		icon16x16 = config.get(categoryGui, "Icon small (16x16)", /*SetTitleMod.MODID + ":icons/icon_16x16.png"*/"").getString();
		icon32x32 = config.get(categoryGui, "Icon big (32x32)", /*SetTitleMod.MODID + ":icons/icon_32x32.png"*/"").getString();

		if (icon16x16 == null || icon16x16.trim().length() <= 0)
			icon16x16 = "icons/icon_16x16.png";

		if (icon32x32 == null || icon32x32.trim().length() <= 0)
			icon32x32 = "icons/icon_32x32.png";

		if (icon16x16.equals("icons/icon_16x16.png") && icon32x32.equals("icons/icon_32x32.png")) return;

		ResourceLocation rl_icon16x16 = new ResourceLocation(icon16x16);
		ResourceLocation rl_icon32x32 = new ResourceLocation(icon32x32);

		if (Util.getOSType() != Util.EnumOS.OSX) {
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
		//-----------------------------------------------------------------------
		if (config.hasChanged()) config.save();
	}

	public static void cursor() {
		cursor = config.get(categoryGui, "Cursor", /*SetTitleMod.MODID + ":cursors/cursor_32.png"*/"").getString();
		if (cursor == null || cursor.trim().length() <= 0) return;
		ResourceLocation rl_cursor = new ResourceLocation(cursor);
		try {
			InputStream inputstream = Config.class.getResourceAsStream("/assets/" + rl_cursor.getResourceDomain() + "/" + rl_cursor.getResourcePath());;
			if (inputstream != null) {
				BufferedImage bufferedimage = ImageIO.read(inputstream);
				int w = bufferedimage.getWidth();
				int h = bufferedimage.getHeight();
				IntBuffer buffer = BufferUtils.createIntBuffer(w * h);
				for (int y = 0; y < h; ++y) {
					for (int x = 0; x < w; ++x) {
						buffer.put(bufferedimage.getRGB(x, (h - 1) - y));
					}
				}
				buffer.flip();
				Mouse.setNativeCursor(new Cursor(w, h, 1, h - 2, 1, buffer, null));
			}
		}
		catch (IOException ioexception) {
			System.err.println("Couldn\'t set cursor");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
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
