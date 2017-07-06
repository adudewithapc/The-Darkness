package thatmartinguy.thedarkness.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;

import javax.annotation.Nullable;

public class EntityLivingShadow extends EntityMob
{
    private EntityPlayer player;
    private final AxisAlignedBB EMPTY_AABB = new AxisAlignedBB(0, 0, 0, 0, 0, 0);

    public EntityLivingShadow(World worldIn)
    {
        super(worldIn);
    }

    public EntityLivingShadow setPlayer(EntityPlayer player)
    {
        this.player = player;
        return this;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
    }

    @Override
    public ItemStack getHeldItem(EnumHand hand)
    {
        return player.getHeldItem(hand);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1d);
    }

    @Override
    public void onUpdate()
    {
        if(world.isDaytime())
        {
            this.setDropItemsWhenDead(false);
            this.setDead();
            for(EntityPlayer player : world.playerEntities)
            {
                player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).setFollowed(false);
            }
        }
        this.setPosition(player.posX, player.posY, player.posZ - 2);
    }

    @Override
    public boolean hitByEntity(Entity entityIn)
    {
        this.attackEntityAsMob(entityIn);
        return true;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return EMPTY_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return EMPTY_AABB;
    }

    @Override
    public AxisAlignedBB getEntityBoundingBox()
    {
        return EMPTY_AABB;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_ENDERMEN_AMBIENT;
    }

    @Override
    protected float getSoundPitch()
    {
        return 0.2f;
    }
}
