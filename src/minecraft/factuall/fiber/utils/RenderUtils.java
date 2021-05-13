package factuall.fiber.utils;


import org.lwjgl.opengl.GL11;

import factuall.fiber.Fiber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class RenderUtils {

    
	public static void drawTracerLine(Entity entity, float red, float green, float blue, float alpha, float width) {
		
		double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX)) - Fiber.mc.getRenderManager().renderPosX;
		double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY)) - Fiber.mc.getRenderManager().renderPosY;
		double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ)) - Fiber.mc.getRenderManager().renderPosZ;
		
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glLineWidth(width);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin((int) 1);
		float dupa = 1;
		Vec3d playerVec = Fiber.mc.thePlayer.getLookVec();
		playerVec.addVector(1, 1, 1);
		//Fiber.addChatMessage(Double.toString(playerVec.xCoord) + " " + Double.toString(playerVec.yCoord) + " " + Double.toString(playerVec.zCoord));
        GL11.glVertex3d(playerVec.xCoord*10, Fiber.mc.thePlayer.getEyeHeight()-30, playerVec.zCoord*10);
        GL11.glVertex3d(xPos, yPos, zPos);

        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
	}

	public static void drawESP(Entity entity, float red, float green, float blue, float alpha) {
    	try {
            double renderPosX = Minecraft.getMinecraft().getRenderManager().viewerPosX;
            double renderPosY = Minecraft.getMinecraft().getRenderManager().viewerPosY;
            double renderPosZ = Minecraft.getMinecraft().getRenderManager().viewerPosZ;


           
            
            double xPos = ((entity.lastTickPosX + (entity.posX - entity.lastTickPosX)) - renderPosX);
            double yPos = ((entity.lastTickPosY + (entity.posY - entity.lastTickPosY))  + entity.height / 2.0f - renderPosY);
            double zPos = ((entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ)) - renderPosZ);
            
            
            
            float playerViewY = Minecraft.getMinecraft().getRenderManager().playerViewY;
            float playerViewX = Minecraft.getMinecraft().getRenderManager().playerViewX;
            boolean thirdPersonView = Minecraft.getMinecraft().getRenderManager().options.thirdPersonView == 2;
            
        	GL11.glPushMatrix();
            GlStateManager.translate(xPos, yPos, zPos);
            GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-entity.rotationYaw, 0.0F, 1.0F, 0.0F);
            //GlStateManager.rotate((float)(thirdPersonView ? -1 : 1) * playerViewX, 1.0F, 0.0F, 0.0F);
            //GlStateManager.rotate(1, 50,500,0);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(false);
            GL11.glLineWidth(2.0F);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glColor4f(red, green, blue, alpha);
            GL11.glBegin((int) 1);
            
            float height = entity.height/2f;
            float size = entity.width/2;
            
            GL11.glVertex3d((double) size, (double) height , (double) -size);
            GL11.glVertex3d((double) 0-size, (double) height , (double) -size);
            GL11.glVertex3d((double) size, (double) -height , (double) -size);
            GL11.glVertex3d((double) 0-size, (double) -height , (double) -size);
            GL11.glVertex3d((double) size, (double) height, (double) -size);
            GL11.glVertex3d((double) size, (double) -height, (double) -size);
            GL11.glVertex3d((double) -size, (double) height, (double) -size);
            GL11.glVertex3d((double) -size, (double) -height, (double) -size);
            
            GL11.glVertex3d((double) size, (double) height , (double) size);
            GL11.glVertex3d((double) 0-size, (double) height , (double) size);
            GL11.glVertex3d((double) size, (double) -height , (double) size);
            GL11.glVertex3d((double) 0-size, (double) -height , (double) size);
            GL11.glVertex3d((double) size, (double) height, (double) size);
            GL11.glVertex3d((double) size, (double) -height, (double) size);
            GL11.glVertex3d((double) -size, (double) height, (double) size);
            GL11.glVertex3d((double) -size, (double) -height, (double) size);
            
            
            GL11.glVertex3d((double) size, (double) height , (double) size);
            GL11.glVertex3d((double) size, (double) height , (double) -size);
            
            GL11.glVertex3d((double) 0-size, (double) height , (double) -size);
            GL11.glVertex3d((double) 0-size, (double) height , (double) size);
            
            GL11.glVertex3d((double) size, (double) -height , (double) size);
            GL11.glVertex3d((double) size, (double) -height , (double) -size);
            
            GL11.glVertex3d((double) 0-size, (double) -height , (double) -size);
            GL11.glVertex3d((double) 0-size, (double) -height , (double) size);
            
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
        } catch (Exception exception) {
        	exception.printStackTrace();
        }
    }

	public static void drawESPBox(double x, double y, double z, float red, float green, float blue, float alpha) {
    	try {
            double renderPosX = Minecraft.getMinecraft().getRenderManager().viewerPosX;
            double renderPosY = Minecraft.getMinecraft().getRenderManager().viewerPosY;
            double renderPosZ = Minecraft.getMinecraft().getRenderManager().viewerPosZ;

            double xPos = x - renderPosX + 0.5;
            double yPos = y - renderPosY + 0.5;
            double zPos = z - renderPosZ + 0.5;
            
            float playerViewY = Minecraft.getMinecraft().getRenderManager().playerViewY;
            float playerViewX = Minecraft.getMinecraft().getRenderManager().playerViewX;
            boolean thirdPersonView = Minecraft.getMinecraft().getRenderManager().options.thirdPersonView == 2;
            
        	GL11.glPushMatrix();
            GlStateManager.translate(xPos, yPos, zPos);
            GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
            //GlStateManager.rotate(-playerViewY, 0.0F, 1.0F, 0.0F);
            //GlStateManager.rotate((float)(thirdPersonView ? -1 : 1) * playerViewX, 1.0F, 0.0F, 0.0F);
            //GlStateManager.rotate(1, 50,500,0);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(false);
            GL11.glLineWidth(2.0F);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glColor4f(red, green, blue, alpha);
            GL11.glBegin((int) 1);
            
            GL11.glVertex3d((double) 0.5f, (double) 0.5 , (double) -0.5);
            GL11.glVertex3d((double) 0-0.5f, (double) 0.5 , (double) -0.5);
            GL11.glVertex3d((double) 0.5f, (double) -0.5 , (double) -0.5);
            GL11.glVertex3d((double) 0-0.5f, (double) -0.5 , (double) -0.5);
            GL11.glVertex3d((double) 0.5f, (double) 0.5, (double) -0.5);
            GL11.glVertex3d((double) 0.5f, (double) -0.5, (double) -0.5);
            GL11.glVertex3d((double) -0.5f, (double) 0.5, (double) -0.5);
            GL11.glVertex3d((double) -0.5f, (double) -0.5, (double) -0.5);
            
            GL11.glVertex3d((double) 0.5f, (double) 0.5 , (double) 0.5);
            GL11.glVertex3d((double) 0-0.5f, (double) 0.5 , (double) 0.5);
            GL11.glVertex3d((double) 0.5f, (double) -0.5 , (double) 0.5);
            GL11.glVertex3d((double) 0-0.5f, (double) -0.5 , (double) 0.5);
            GL11.glVertex3d((double) 0.5f, (double) 0.5, (double) 0.5);
            GL11.glVertex3d((double) 0.5f, (double) -0.5, (double) 0.5);
            GL11.glVertex3d((double) -0.5f, (double) 0.5, (double) 0.5);
            GL11.glVertex3d((double) -0.5f, (double) -0.5, (double) 0.5);
            
            GL11.glVertex3d((double) 0.5f, (double) 0.5 , (double) 0.5);
            GL11.glVertex3d((double) 0.5f, (double) 0.5 , (double) -0.5);
            
            GL11.glVertex3d((double) 0-0.5f, (double) 0.5 , (double) -0.5);
            GL11.glVertex3d((double) 0-0.5f, (double) 0.5 , (double) 0.5);
            
            GL11.glVertex3d((double) 0.5f, (double) -0.5 , (double) 0.5);
            GL11.glVertex3d((double) 0.5f, (double) -0.5 , (double) -0.5);
            
            GL11.glVertex3d((double) 0-0.5f, (double) -0.5 , (double) -0.5);
            GL11.glVertex3d((double) 0-0.5f, (double) -0.5 , (double) 0.5);

            
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
        } catch (Exception exception) {
        	exception.printStackTrace();
        }
    }
	
	
	
}
