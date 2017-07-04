package thatmartinguy.thedarkness.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;

public class CommandHost extends CommandBase
{
    @Override
    public String getName()
    {
        return "makehost";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "makehost {boolean}";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;
            player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).setHost(Boolean.parseBoolean(args[0]));
            player.sendMessage(new TextComponentString("Player host = " + args[0]));
        }
    }
}
