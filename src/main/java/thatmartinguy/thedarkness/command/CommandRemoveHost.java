package thatmartinguy.thedarkness.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;

public class CommandRemoveHost extends CommandBase
{
	@Override
	public String getName()
	{
		return "removehost";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "removehost";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if(sender.getCommandSenderEntity() instanceof EntityPlayer)
		{
			IPlayerHostCapability host = sender.getCommandSenderEntity().getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);
			host.setHost(false);
			sender.getCommandSenderEntity().sendMessage(new TextComponentString("You are no longer a host"));
		}
	}
}
