package com.sebasphere.udaexpansions.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.sebasphere.udaexpansions.common.Gravity;
import com.sebasphere.udaexpansions.common.GravityFuckerUpper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandGravityChanger extends CommandBase {
	
	//private String gravityDirection;
	
	enum SubCommand implements IStringSerializable {
		
		UP("UP"),
		DOWN("DOWN"),
		NORTH("NORTH"),
		EAST("EAST"),
		SOUTH("SOUTH"),
		WEST("WEST");
		
		private final String direction;
		
		SubCommand(String direction) {
			this.direction = direction;
		}
		
		void execute(String direction) {

			GravityFuckerUpper.setDirection(direction);
		}

		@Override
		public String getName () {
			return this.name().toUpperCase(Locale.ROOT);
		}
	}

    


    @Override
    public String getName() {
        return "gravchange";
    }

    @Override
    public String getUsage(ICommandSender sender) {
    	StringBuilder subcommands = new StringBuilder();
    	for(SubCommand sub : SubCommand.values()) {
    		if(subcommands.length() > 0) subcommands.append("|");
    		subcommands.append(sub.getName());
    	}
        return String.format("%sUsage: /%s <%s>", TextFormatting.RED, getName(), subcommands.toString());//"command.gravitychanger.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
    	EntityPlayerMP player = (EntityPlayerMP) sender.getCommandSenderEntity();
    	if(args.length < 1) {
    		sender.sendMessage(new TextComponentString(getUsage(sender)));
    	}
    	
    	getSubCommand(args[0]).ifPresent(subCommand -> {
    		subCommand.execute(getName());
    		//gravityDirection = subCommand.name().toLowerCase(Locale.ROOT);
    		String line = String.format("Set %s's Gravity", player.getName());
    		sender.sendMessage(new TextComponentString(line));
    	});
//        if (args != null && args.length > 0) {
//            for (String param : args) {
//                switch (param) {
//                    case "help":
//                        sender.sendMessage(help);
//                        break;
//                    case "up":
//                        this.gravityDirection = "UP";
//                        TextComponentString up = new TextComponentString("/gravitychanger up: Going up!");
//                        sender.sendMessage(up);
//                        break;
//                    case "down":
//                        this.gravityDirection = "DOWN";
//                        TextComponentString down = new TextComponentString("/gravitychanger down: Going down!");
//                        sender.sendMessage(down);
//                        break;
//                    case "north":
//                        this.gravityDirection = "NORTH";
//                        TextComponentString north = new TextComponentString("/gravitychanger north: Going north!");
//                        sender.sendMessage(north);
//                        break;
//                    case "east":
//                        this.gravityDirection = "EAST";
//                        TextComponentString east = new TextComponentString("/gravitychanger east: Going east!");
//                        sender.sendMessage(east);
//                        break;
//                    case "south":
//                        this.gravityDirection = "SOUTH";
//                        TextComponentString south = new TextComponentString("/gravitychanger south: Going south!");
//                        sender.sendMessage(south);
//                        break;
//                    case "west":
//                        this.gravityDirection = "WEST";
//                        TextComponentString west = new TextComponentString("/gravitychanger west: Going west!");
//                        sender.sendMessage(west);
//                        break;
//                    case "test":
//                        TextComponentString test = new TextComponentString("/gravitychanger test | getting value: " + gravityDirection);
//                        sender.sendMessage(test);
//                        break;
//                    default:
//                        sender.sendMessage(help);
//
//                }
//            }
//        }
    }
    
    private Optional<SubCommand> getSubCommand(String arg) {
    	for(SubCommand subCommand :SubCommand.values()) 
    		if(subCommand.name().equalsIgnoreCase(arg)) {
    			//gravityDirection = subCommand.name();
    			return Optional.of(subCommand);
    		}
    	return Optional.empty();
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
    	if (args.length == 1) 
    		return getListOfStringsMatchingLastWord(args, Arrays.stream(SubCommand.values())
    				.map(SubCommand::getName)
    				.collect(ImmutableList.toImmutableList()));
    	else
    		return ImmutableList.of();
    }

}
