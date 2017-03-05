package thatmartinguy.thedarkness.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import thatmartinguy.thedarkness.data.ModWorldData;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;

//Temporary test command that will be removed upon release
public class CommandCheckHost extends CommandBase
{
	@Override
	public String getName()
	{
		return "checkhost";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "checkhost";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if(sender.getCommandSenderEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
			player.sendMessage(new TextComponentString("The player is host = " + player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost()));
		}
	}
}
