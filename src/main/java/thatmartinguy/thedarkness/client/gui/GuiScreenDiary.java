package thatmartinguy.thedarkness.client.gui;

import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import scala.reflect.internal.Trees.New;

public class GuiScreenDiary extends GuiScreenBook
{
	protected final ResourceLocation GUI_BOOK_LOCATION = new ResourceLocation("minecraft", "textures/gui/book.png");
	protected NBTTagList bookPages = new NBTTagList();
	protected ItemStack bookObject;
	protected int currPage;
	protected String inputString;
	
	public GuiScreenDiary(EntityPlayer player, ItemStack book, boolean isUnsigned, String inputString)
	{
		super(player, book, isUnsigned);
		this.inputString = inputString;
	}

    private void pageSetCurrent(String p_146457_1_)
    {
        if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount())
        {
            this.bookPages.set(this.currPage, new NBTTagString(p_146457_1_));
        }
    }
    
    private void pageInsertIntoCurrent(String input)
    {
    	
    }
}
