package thatmartinguy.thedarkness.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.block.BlockHidden;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.init.ModBlocks;
import thatmartinguy.thedarkness.init.ModPotions;

import javax.annotation.Nullable;
import java.util.List;

public class ItemReliquary extends ItemBase
{
    public ItemReliquary(String name)
    {
        super(name);
        this.setCreativeTab(null);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(Integer.toString(getUses(stack)));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World worldIn, EntityLivingBase entityLiving)
    {
        if(entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
            if(worldIn.isRemote)
                worldIn.playSound(player, new BlockPos(player), SoundEvents.ENTITY_ENDERMEN_HURT, SoundCategory.PLAYERS, 0.5F, 0.2F);


            if(player instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) player, itemStack);
            }
        }

        return handleDrinking(itemStack, worldIn, entityLiving);
    }

    public ItemStack handleDrinking(ItemStack itemStack, World world, EntityLivingBase entityLiving)
    {
        if(entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
            switch (getUses(itemStack))
            {
                case 1:
                    itemStack.shrink(1);
                    world.setWorldTime(18000);
                    if(world.isRemote)
                    {
                        world.playSound(player, new BlockPos(player), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.PLAYERS, 0.5F, 0.2F);
                        player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "... I will consume you."), true);
                    }
                    else
                    {
                        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200));
                        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200));
                        player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).setTransforming(true);
                        player.setHealth(player.getHealth() - 1);
                    }
                    break;
                case 2:
                    world.setWorldTime(18000);
                    reduceUses(itemStack);
                    createHole(world, player.getPosition());
                    if(world.isRemote)
                    {
                        world.playSound(player, new BlockPos(player), SoundEvents.BLOCK_PORTAL_TRIGGER, SoundCategory.PLAYERS, 0.5F, 0.2F);
                        player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "Mortal..."), true);
                    }
                    else
                    {
                        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200));
                        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
                        player.addPotionEffect(new PotionEffect(ModPotions.potionFading, 24000));
                    }
                    break;
                case 3:
                    reduceUses(itemStack);
                    world.setWorldTime(18000);
                    if(world.isRemote)
                    {
                        world.playSound(player, new BlockPos(player), SoundEvents.AMBIENT_CAVE, SoundCategory.PLAYERS, 0.5F, 0.2F);
                        player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "..."), true);
                    }
                    else
                    {
                        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,200));
                        player.addPotionEffect(new PotionEffect(ModPotions.potionFading, 24000));
                    }
                    break;
            }
        }
        return itemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if(!playerIn.isCreative() && !playerIn.isPotionActive(ModPotions.potionFading))
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
        }
        else
        {
            return new ActionResult<>(EnumActionResult.FAIL, itemStack);
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    public NBTTagCompound getNBT(ItemStack itemStack)
    {
        NBTTagCompound compound;
        if(itemStack.hasTagCompound())
        {
            compound = itemStack.getTagCompound();
        }
        else
        {
            compound = new NBTTagCompound();
        }

        if(!compound.hasKey("Uses"))
        {
            compound.setInteger("Uses", 3);
        }
        itemStack.setTagCompound(compound);
        return compound;
    }

    public int getUses(ItemStack itemStack)
    {
        if(itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("Uses"))
        {
            return itemStack.getTagCompound().getInteger("Uses");
        }
        else
        {
            return getNBT(itemStack).getInteger("Uses");
        }
    }

    public void reduceUses(ItemStack itemStack)
    {
        getNBT(itemStack).setInteger("Uses", getUses(itemStack) - 1);
    }

    private void createHole(World world, BlockPos playerPos)
    {
        BlockPos origin = new BlockPos(playerPos.getX() + 1, world.getActualHeight(), playerPos.getZ() + 1);
        for(int y = 0; y < world.getActualHeight(); y++)
        {
            for(int x = origin.getX(); x < origin.getX() - 2; x--)
            {
                for(int z = origin.getZ(); z < origin.getZ() + 2; z++)
                {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    world.setBlockState(currentPos, ModBlocks.blockHidden.createHiddenBlock(world, currentPos, world.getBlockState(currentPos), world.getTileEntity(currentPos)));
                }
            }
        }
    }
}