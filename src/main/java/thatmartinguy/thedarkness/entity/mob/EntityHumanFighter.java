package thatmartinguy.thedarkness.entity.mob;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityHumanFighter extends EntityHuman
{
	public EntityHumanFighter(World worldIn)
	{
		super(worldIn);
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
		this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
		this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
	}
	
	@Override
	protected void initEntityAI()
	{
		super.applyEntityAttributes();
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		
	}
}
