package com.sebasphere.udaexpansions.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class GravityDirectionCommand extends CommandBase {

    private static String gravityDirection = "PLACEHOLDER";

    TextComponentString help = new TextComponentString("/gravitychanger up|down|north|east|south|west");



    @Override
    public String getName() {
        return "gravitychanger";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.gravitychanger.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args != null && args.length > 0) {
            for (String param : args) {
                switch (param) {
                    case "help":
                        sender.sendMessage(help);
                        break;
                    case "up":
                        this.gravityDirection = "UP";
                        TextComponentString up = new TextComponentString("/gravitychanger up: Going up!");
                        sender.sendMessage(up);
                        break;
                    case "down":
                        this.gravityDirection = "DOWN";
                        TextComponentString down = new TextComponentString("/gravitychanger down: Going down!");
                        sender.sendMessage(down);
                        break;
                    case "north":
                        this.gravityDirection = "NORTH";
                        TextComponentString north = new TextComponentString("/gravitychanger north: Going north!");
                        sender.sendMessage(north);
                        break;
                    case "east":
                        this.gravityDirection = "EAST";
                        TextComponentString east = new TextComponentString("/gravitychanger east: Going east!");
                        sender.sendMessage(east);
                        break;
                    case "south":
                        this.gravityDirection = "SOUTH";
                        TextComponentString south = new TextComponentString("/gravitychanger south: Going south!");
                        sender.sendMessage(south);
                        break;
                    case "west":
                        this.gravityDirection = "WEST";
                        TextComponentString west = new TextComponentString("/gravitychanger west: Going west!");
                        sender.sendMessage(west);
                        break;
                    case "test":
                        TextComponentString test = new TextComponentString("/gravitychanger test | getting value: " + gravityDirection);
                        sender.sendMessage(test);
                        break;
                    default:
                        sender.sendMessage(help);

                }

            }


        }
    }


    public String getGravityDirection() {
        return gravityDirection;
    }
}
