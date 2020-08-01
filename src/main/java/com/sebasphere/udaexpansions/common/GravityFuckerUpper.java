package com.sebasphere.udaexpansions.common;

import java.util.Set;

import javax.annotation.Nonnull;

import com.sebasphere.udaexpansions.commands.CommandGravityChanger;

import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import uk.co.mysterymayhem.gravitymod.GravityMod;
import uk.co.mysterymayhem.gravitymod.api.API;
import uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection;
import uk.co.mysterymayhem.gravitymod.api.events.GravityTransitionEvent;
import uk.co.mysterymayhem.gravitymod.asm.Hooks;
import uk.co.mysterymayhem.gravitymod.common.capabilities.gravitydirection.GravityDirectionCapability;
import uk.co.mysterymayhem.gravitymod.common.capabilities.gravitydirection.IGravityDirectionCapability;
import uk.co.mysterymayhem.gravitymod.common.packets.PacketHandler;
import uk.co.mysterymayhem.gravitymod.common.packets.gravitychange.GravityChangeMessage;
import uk.co.mysterymayhem.gravitymod.common.registries.GravityPriorityRegistry;

public class GravityFuckerUpper {
	//GravityDirectionCommand gravity = new GravityDirectionCommand();
	private static String direction;
	

	@SubscribeEvent
	public void onServerTick (TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END)
			return;
		for (EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().getServer()
				.getPlayerList().getPlayers()) {
			//It initially grabs a "PLACEHOLDER" String
			String gravityDirection = direction;
			switch (gravityDirection) {
			case "UP":
				API.setPlayerGravity(EnumGravityDirection.UP, player, 20);
				break;
			case "DOWN":
				API.setPlayerGravity(EnumGravityDirection.DOWN, player, 20);
				break;
			case "NORTH":
				API.setPlayerGravity(EnumGravityDirection.NORTH, player, 20);
				break;
			case "EAST":
				API.setPlayerGravity(EnumGravityDirection.EAST, player, 20);
				break;
			case "SOUTH":
				API.setPlayerGravity(EnumGravityDirection.SOUTH, player, 20);
				break;
			case "WEST":
				API.setPlayerGravity(EnumGravityDirection.WEST, player, 20);
				break;

			default:
				break;
				//System.out.println("AAAAAA I'm SPAMMING CONSOLE, PLEASE REPORT ME");
			}
		}
	}


	public static String getDirection () {
		return direction;
	}


	public static void setDirection (String direction) {
		GravityFuckerUpper.direction = direction;
	}
}
