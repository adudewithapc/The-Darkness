package thatmartinguy.thedarkness.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import thatmartinguy.thedarkness.data.ModWorldData;

//Temporary test command that will be removed upon release
public class CommandResetReliquaryCraftedState extends CommandBase
{
	@Override
	public String getName()
	{
		return "resetreliquarycrafted";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "resetreliquarycrafted";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		ModWorldData.get(sender.getEntityWorld()).setReliquaryCrafted(false);
		sender.sendMessage(new TextComponentString("Reset ReliquaryWorldData"));
	}
}
