package thatmartinguy.thedarkness.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandDimension extends CommandBase
{
    @Override
    public String getName()
    {
        return "dimension";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "dimension";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;
            player.changeDimension(Integer.parseInt(args[0]));
        }
    }
}
