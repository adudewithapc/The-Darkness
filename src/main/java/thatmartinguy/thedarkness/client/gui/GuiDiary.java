package thatmartinguy.thedarkness.client.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thatmartinguy.thedarkness.util.Reference;

public class GuiDiary extends GuiScreen
{
	private final int imageWidth = 192;
	private final int imageHeight = 192;
	private int currentPage = 0;
	private static int totalPages;
	private static final ResourceLocation bookTexture = new ResourceLocation(Reference.MOD_ID, "textures/gui/book.png");
	private String[] pageText;
	private GuiButton buttonDone;
	private PageButton buttonNext;
	private PageButton buttonPrevious;
	
	public GuiDiary()
	{
		pageText = new String[3];
		pageText[0] = "Lorem";
		pageText[1] = "ipsum";
		pageText[2] = ".";
	}
	
	@Override
	public void initGui()
	{
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		
		buttonDone = new GuiButton(0, this.width - 192 / 2 - 240, 196, 200, 20, I18n.format("gui.done", new Object[0]));
		buttonNext = new PageButton(1, (this.width - 192) / 2 - 240, 156, true);
		buttonPrevious = new PageButton(2, (this.width - 192) / 2 + 38, 156, false);
		
		buttonList.add(buttonDone);
		buttonList.add(buttonNext);
		buttonList.add(buttonPrevious);
	}
	
	@Override
	public void updateScreen()
	{
		buttonDone.visible = true;
		buttonNext.visible = (currentPage < totalPages - 1);
		buttonPrevious.visible = currentPage > 0;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		mc.getTextureManager().bindTexture(bookTexture);
		this.drawTexturedModalRect((this.width - 192) / 2, 2, 0, 0, 192, 192);
		
		fontRendererObj.drawSplitString(pageText[currentPage], (this.width - 192) / 2 + 36, 34, 116, 0);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button == buttonDone)
		{
			mc.displayGuiScreen((GuiScreen)null);
		}
		else if(button == buttonNext)
		{
			currentPage++;
		}
		else if(button == buttonPrevious)
		{
			currentPage--;
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	static class PageButton extends GuiButton
	{
		private final boolean isNextButton;
		
		public PageButton(int buttonId, int posX, int posY, boolean isNextButton)
		{
			super(buttonId, posX, posY, 23, 13, "");
			this.isNextButton = isNextButton;
		}
		
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY)
		{
			if(visible)
			{
				boolean isButtonHovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height; 
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(bookTexture);
				int textureX = 0;
				int textureY = 192;
				
				if(isButtonHovered)
				{
					textureX += 23;
				}
				
				if(!isNextButton)
				{
					textureY += 13;
				}
				
				drawTexturedModalRect(xPosition, yPosition, textureX, textureY, 23, 13);
			}
		}
	}
}
