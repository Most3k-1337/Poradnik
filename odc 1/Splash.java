package fajnyclient.gui.splash;

import java.awt.Color;
import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;

public class Splash {
	private static float splashPercent = 0;
	private static String splashProgress = "Initializing Minecraft";
	
	public static final int maxprogress = 10;
	
	public static void updateSplash(int stage, String progress) {
		updateSplash((float) stage / (float) maxprogress, progress);
	}
	
	public static void updateSplash(float percent, String progress) {
		splashPercent = percent;
		splashProgress = progress;
		draw();
	}
	
	public static void draw() {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		
		// przekopiowane troche erica golde
		// ale reszta nie :)
		
		Framebuffer framebuffer = new Framebuffer(sr.getScaledWidth() * sr.getScaleFactor(), sr.getScaledHeight() * sr.getScaleFactor(), true);
        framebuffer.bindFramebuffer(false);

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, sr.getScaledWidth(), sr.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();


        GlStateManager.resetColor();
        GlStateManager.color(1.0F,  1.0F, 1.0F, 1.0F);

        
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(sr.getScaledWidth() * sr.getScaleFactor(), sr.getScaledHeight() * sr.getScaleFactor());

        Gui.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), new Color(20,20,20).getRGB());
		
    	
		
		GlStateManager.pushMatrix();
		Gui gui = new Gui();
		GlStateManager.scale(2, 2, 2);
		
		gui.drawCenteredString(Minecraft.getMinecraft().fontRendererObj,"FajnyClient", sr.getScaledWidth() /2  / 2, (int) (sr.getScaledHeight() /2  / 2.5), -1);
		GlStateManager.popMatrix();
		
		
		GlStateManager.resetColor();

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);

        GlStateManager.pushMatrix();

		
		gui.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, String.format("%s | %s", splashProgress, new DecimalFormat("#.##%").format(splashPercent)), sr.getScaledWidth() /2 , (int) (sr.getScaledHeight() / 1.6f), -1);
		
		
		Gui.drawRect(10,  (int) (sr.getScaledHeight() / 1.5f), (int) ((int) (sr.getScaledWidth() - 10) * splashPercent), (int) (sr.getScaledHeight() / 1.5f + 10), new Color(88, 29, 224).getRGB());
		
		Minecraft.getMinecraft().updateDisplay();
	}	
	
	
}
