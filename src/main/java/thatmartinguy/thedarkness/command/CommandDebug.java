package thatmartinguy.thedarkness.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;

public class CommandDebug extends CommandBase
{
    @Override
    public String getName()
    {
        return "debugcaps";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "debugcaps {log|clear|capability} [true|false]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(args.length == 0)
            throw new CommandException("Usage: " + getUsage(sender));
        if (sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;
            IPlayerHostCapability capability = player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);
            if(args.length == 2)
            {
                boolean isArgBoolean = (args[1].toLowerCase() == "true" || args[1].toLowerCase() == "false") && !args[0].toLowerCase().equals("log");
                if (isArgBoolean)
                    throw new CommandException(args[1] + " is not a valid boolean");
            }
            switch(args[0].toLowerCase())
            {
                case "log":
                    player.sendMessage(new TextComponentString("Host = " + capability.isHost()));
                    player.sendMessage(new TextComponentString("Transforming = " + capability.isTransforming()));
                    player.sendMessage(new TextComponentString("Followed = " + capability.isFollowed()));
                    break;
                case "host":
                    capability.setHost(Boolean.parseBoolean(args[1]));
                    player.sendMessage(new TextComponentString("Host = " + capability.isHost()));
                    break;
                case "transforming":
                    capability.setTransforming(Boolean.parseBoolean(args[1]));
                    player.sendMessage(new TextComponentString("Transforming = " + capability.isTransforming()));
                    break;
                case "followed":
                    capability.setFollowed(Boolean.parseBoolean(args[1]));
                    player.sendMessage(new TextComponentString("Followed = " + capability.isFollowed()));
                case "clear":
                    capability.setFollowed(false);
                    capability.setTransforming(false);
                    capability.setHost(false);
                    break;
            }
        }
        else
        {
            sender.sendMessage(new TextComponentString("This command is only for players"));
        }
    }
}
