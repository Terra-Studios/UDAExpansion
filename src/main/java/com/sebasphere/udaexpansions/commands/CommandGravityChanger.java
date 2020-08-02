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

	/**
	 * $NAME ( &DIRECTION )<br>
	 * 
	 * String DIRECTION is what we send to {@link com.sebasphere.udaexpansions.common.GravityChanger}
	 */
	enum SubCommand implements IStringSerializable {

		UP("UP"),
		DOWN("DOWN"),
		NORTH("NORTH"),
		EAST("EAST"),
		SOUTH("SOUTH"),
		WEST("WEST");

		/**
		 * The never does change because each Enum value is the direction we want and that never changes
		 */
		private final String direction;

		SubCommand(String direction) {
			this.direction = direction;
		}

		
		/**
		 * direction is final so we can specify "this.direction" which on every command will be final
		 */
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

		return String
				.format("%sUsage: %s/%s %s<%s>\n%sCommand Aliases: %s%s", TextFormatting.RED, TextFormatting.AQUA, getName(), TextFormatting.GOLD, subcommands
						.toString(), TextFormatting.RED, TextFormatting.GREEN, aliases.toString());
	}

	@Override
	public void execute (MinecraftServer server, ICommandSender sender, String[] args) {
		EntityPlayerMP player = (EntityPlayerMP) sender.getCommandSenderEntity();
		
		// First check if no arguments were sent, so if args is less than 1, we send help message
		// and return; so the method ends and does not continue to further checks
		if (args.length < 1) {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
			// stop method execution beyond this point
			// see https://imgur.com/1WlezXX for what shows if we don't return
			return;
		}
		
		// Second check, only fires if first check passed and the command does have an argument
		// but the argument is not a valid one SEE getValidArgs() 
		if (!Arrays.stream(getValidArgs()).parallel().anyMatch(args[0]::equalsIgnoreCase)) {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
			// same reason as the first check
			return;
		}
		
		// And our last check, this only fires if the argument matches any 1 of the valid args
		// and then runs the command
		if (Arrays.stream(getValidArgs()).parallel().anyMatch(args[0]::equalsIgnoreCase)) {
			getSubCommand(args[0]).ifPresent(subCommand -> {
				subCommand.execute(player);
				String line = String.format("Set %s's Gravity to %s", player.getName(), subCommand.getName());
				sender.sendMessage(new TextComponentString(line));
			});
		}

	}

	// alright how can i explain this like im speaking to a 4 year old
	// SubCommand is an enum, and enums have values. 
	private Optional<SubCommand> getSubCommand (String arg) {
		// Optional is to ensure we don't throw a NullPointExemption if the string we pass to this method 
		// is NOT a value from SubCommand enum.
		
		// so we take our collection of all values in SubCommand, which is every $NAME from SubCommand
		for (SubCommand subCommand : SubCommand.values())
			//now if the name of subCommand is equal to (String arg), does not take case into account
			if (subCommand.name().equalsIgnoreCase(arg)) {
				// we return the SubCommand enum value
				return Optional.of(subCommand);
			}
		// if the arg doesn't match any value from SubCommand enum, we return an empty instance.
		return Optional.empty();
	}

	// Right so this is basically the same as above, except this only used to 
	// built a List of every SubCommand value's name so we can run our checks in execute()
	private String[] getValidArgs () {
		List<String> valid = new ArrayList<>();
		//same shit as above, for every value
		for (SubCommand subCommand : SubCommand.values()) {
			// we add the name for this value to the list
			valid.add(subCommand.getName());
		}
		// now we just return an Array of the list, this is simply a convenience method for the checks
		// we do we do this? Arrays cannot be modified, but List can
		// there is actually a much simpler way to return this, but im going to keep it for you to figure out
		// because the answer is in this very class
		return Arrays.copyOf(valid.toArray(), valid.size(), String[].class);
	}

	@Override
	public List<String> getTabCompletions (MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		if (args.length == 1)
			
			// so getListOfStringsMatchingLastWord is a method from Minecraft class
			// and we use Arrays.steam to modify the list. honestly, this is just knowing Collections and way too long to figure out
			// but we only want the tab completion to return add 1 value, if we dont check for (args.length == 1) on the second tab
			// it would add a second arg, which we do not want
			return getListOfStringsMatchingLastWord(args, Arrays.stream(SubCommand.values()).map(SubCommand::getName)
					.collect(ImmutableList.toImmutableList()));
		else
			// every if statement SHOULD have a else declaration in case the world started to burn and our game oofed during the first check
			// we would return a list of nothing, an empty list
			return ImmutableList.of();
	}
}
