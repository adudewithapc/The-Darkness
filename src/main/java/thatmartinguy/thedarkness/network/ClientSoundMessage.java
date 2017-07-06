package thatmartinguy.thedarkness.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thatmartinguy.thedarkness.TheDarkness;

public class ClientSoundMessage implements IMessage
{
    private SoundEvent sound;
    private float volume;
    private float pitch;

    public ClientSoundMessage() {}

    public ClientSoundMessage(SoundEvent sound)
    {
        this(sound, 1f, 1f);
    }

    public ClientSoundMessage(SoundEvent sound, float volume, float pitch)
    {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        sound = SoundEvent.REGISTRY.getObject(new ResourceLocation(ByteBufUtils.readUTF8String(buf)));
        volume = buf.readFloat();
        pitch = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, sound.getSoundName().toString());
        buf.writeFloat(volume);
        buf.writeFloat(pitch);
    }

    public static class Handler implements IMessageHandler<ClientSoundMessage, IMessage>
    {
        @Override
        public IMessage onMessage(ClientSoundMessage message, MessageContext ctx)
        {
            TheDarkness.proxy.getThreadListener(ctx).addScheduledTask(() ->
            {
               final EntityPlayer player = TheDarkness.proxy.getClientPlayer();

               player.playSound(message.sound, message.volume, message.pitch);
            });

            return null;
        }
    }
}
