package com.sebasphere.udaexpansions.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.sebasphere.udaexpansions.common.GravityChanger;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandGravityChanger extends CommandBase {

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
		return "gravchange";
	}

	@Override
	public List<String> getAliases () {
		return ImmutableList.of("derp", "grav");
	}

	@Override
	public String getUsage (ICommandSender sender) {
		StringBuilder subcommands = new StringBuilder();
		StringBuilder aliases     = new StringBuilder();
		subcommands.append(String.join(" | ", getValidArgs()));
		aliases.append(String.join(" | ", getAliases()));
		
		return String
				.format("%sUsage: %s/%s %s<%s>\n%sCommand Aliases: %s%s", TextFormatting.RED, TextFormatting.AQUA, getName(), TextFormatting.GOLD, subcommands
						.toString(), TextFormatting.RED, TextFormatting.GREEN, aliases.toString());
	}

	@Override
	public void execute (MinecraftServer server, ICommandSender sender, String[] args) {
		EntityPlayerMP player = (EntityPlayerMP) sender.getCommandSenderEntity();
		if (args.length < 1) {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
			return;
		}
		if (!Arrays.stream(getValidArgs()).parallel().anyMatch(args[0]::equalsIgnoreCase)) {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
			return;
		}
		if (Arrays.stream(getValidArgs()).parallel().anyMatch(args[0]::equalsIgnoreCase)) {
			getSubCommand(args[0]).ifPresent(subCommand -> {
				subCommand.execute(player);
				String line = String.format("Set %s's Gravity to %s", player.getName(), subCommand.getName());
				sender.sendMessage(new TextComponentString(line));
			});
		}

	}

	private Optional<SubCommand> getSubCommand (String arg) {
		for (SubCommand subCommand : SubCommand.values())
			if (subCommand.name().equalsIgnoreCase(arg)) {
				//gravityDirection = subCommand.name();
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

	@Override
	public List<String> getTabCompletions (MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		if (args.length == 1)
			return getListOfStringsMatchingLastWord(args, Arrays.stream(SubCommand.values()).map(SubCommand::getName)
					.collect(ImmutableList.toImmutableList()));
		else
			return ImmutableList.of();
	}
}
