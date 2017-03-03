package thatmartinguy.thedarkness.achievement;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import thatmartinguy.thedarkness.item.ModItems;

public class ModAchievements
{
	public static AchievementPage theDarknessAchievmentPage;
	
	public static Achievement acquireDiary;
	public static Achievement acquireBrighstoneSword;
	public static Achievement transform;
	
	public static void init()
	{
		acquireDiary = (new Achievement("achievement.diary", "acquireDiary", 0, 0, ModItems.itemDiaryOne, AchievementList.KILL_WITHER));
		acquireBrighstoneSword = (new Achievement("achievement.brighstonesword", "acquireBrightstoneSword", 2, 0, ModItems.swordBrightstone, acquireDiary));
		transform = (new Achievement("achievement.transform", "transform", 4, 0, ModItems.itemReliquary, acquireBrighstoneSword));
		
		theDarknessAchievmentPage = new AchievementPage("The Darkness", acquireDiary, acquireBrighstoneSword, transform);
		AchievementPage.registerAchievementPage(theDarknessAchievmentPage);
	}
}
