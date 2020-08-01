package com.sebasphere.udaexpansions.commands;

import com.google.common.collect.ImmutableList;
import com.sebasphere.udaexpansions.util.GravityChanger;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.*;

public class CommandGravityChanger extends CommandBase {

	//ok so I understand depending on the enum called, it implements the interface to turn it into a String
	enum SubCommand implements IStringSerializable {

		UP("UP"),
		DOWN("DOWN"),
		NORTH("NORTH"),
		EAST("EAST"),
		SOUTH("SOUTH"),
		WEST("WEST");

		//why does this need to be final? Doesn't final mean the value can't be changed
		private final String direction;

		SubCommand(String direction) {
			this.direction = direction;
		}

		void execute (EntityPlayerMP player) {
			GravityChanger.setPlayer(player);
			GravityChanger.setDirection(this.direction);
		}

		@Override
		public String getName () {
			return this.name().toUpperCase(Locale.ROOT);
		}
	}

	@Override
	public String getName () {
		return "gravityself";
	}

	@Override
	public List<String> getAliases () {
		return ImmutableList.of("gravself", "gslf");
	}

	@Override
	public String getUsage (ICommandSender sender) {
		StringBuilder subcommands = new StringBuilder();
		StringBuilder aliases     = new StringBuilder();
		subcommands.append(String.join(" | ", getValidArgs()));
		aliases.append(String.join(" | ", getAliases()));

		//what????? I don't get what %s or any of that does. (I get it now)
		//wait oh is it formatting but explain
		//What does aliases.toString(), wait I get it

		return String.format("%sUsage: %s/%s %s<%s>\n%sCommand Aliases: %s%s", TextFormatting.RED, TextFormatting.AQUA, getName(), TextFormatting.GOLD, subcommands.toString(), TextFormatting.RED, TextFormatting.GREEN, aliases.toString());
	}

	//overrides CommandBase and called when command is executed. Main method
	@Override
	public void execute (MinecraftServer server, ICommandSender sender, String[] args) {
		EntityPlayerMP player = (EntityPlayerMP) sender.getCommandSenderEntity();
		if (args.length < 1) {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
			return;
		}
		//So if the message is not valid? Like "/gravityself AAAAAHELPME" would getUsage() and send to the sender
		if (!Arrays.stream(getValidArgs()).parallel().anyMatch(args[0]::equalsIgnoreCase)) {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
			return;
		}
		//help me. Understand that this returns the thing after the lambda if first condition is true
		if (Arrays.stream(getValidArgs()).parallel().anyMatch(args[0]::equalsIgnoreCase)) {
			getSubCommand(args[0]).ifPresent(subCommand -> {
				subCommand.execute(player);
				String line = String.format("Set %s's Gravity to %s", player.getName(), subCommand.getName());
				sender.sendMessage(new TextComponentString(line));
			});
		}

	}

	//What the fuck please help explain the next two methods in some depth
	private Optional<SubCommand> getSubCommand (String arg) {
		for (SubCommand subCommand : SubCommand.values())
			if (subCommand.name().equalsIgnoreCase(arg)) {
				return Optional.of(subCommand);
			}
		return Optional.empty();
	}

	private String[] getValidArgs () {
		List<String> valid = new ArrayList<>();
		for (SubCommand subCommand : SubCommand.values()) {
			valid.add(subCommand.getName());
		}
		return Arrays.copyOf(valid.toArray(), valid.size(), String[].class);
	}


	//Ok so this is in charge of completing tab if I'm not mistaken?? I don't understand that return statement except that it's taking in the args of the sender
	@Override
	public List<String> getTabCompletions (MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		if (args.length == 1)
			return getListOfStringsMatchingLastWord(args, Arrays.stream(SubCommand.values()).map(SubCommand::getName)
					.collect(ImmutableList.toImmutableList()));
		else
			return ImmutableList.of();
	}

}
