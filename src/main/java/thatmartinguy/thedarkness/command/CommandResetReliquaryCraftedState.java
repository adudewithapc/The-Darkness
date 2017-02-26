package thatmartinguy.thedarkness.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import thatmartinguy.thedarkness.data.ReliquaryWorldData;

//Temporary test command that will be removed upon release
public class CommandResetReliquaryCraftedState extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "resetreliquarycrafted";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "resetreliquarycrafted";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		ReliquaryWorldData.get(sender.getEntityWorld()).setReliquaryCrafted(false);
		sender.addChatMessage(new TextComponentString("Reset ReliquaryWorldData"));
	}
}
