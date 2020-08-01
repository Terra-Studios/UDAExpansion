package com.sebasphere.udaexpansions.common;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import uk.co.mysterymayhem.gravitymod.api.API;
import uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection;

public class GravityChanger {

	private static String         direction = "DEFAULT";
	private static EntityPlayerMP player;

	@SubscribeEvent
	public void onServerTick (TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END)
			return;
		EntityPlayerMP player           = GravityChanger.getPlayer();
		String         gravityDirection = direction;
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
		case "DEFAULT":
			break;
		default:

			System.out.println("AAAAAA I'm SPAMMING CONSOLE, PLEASE REPORT ME");
			//}
		}
	}

	/**
	 * Used for the Gravity command to set the direction for the player
	 * 
	 */
	public static void setDirection (String direction) {
		GravityChanger.direction = direction;
	}

	/**
	 * Used for the Gravity command to set the player
	 * 
	 */
	public static void setPlayer (EntityPlayerMP player) {
		GravityChanger.player = player;
	}

	private static EntityPlayerMP getPlayer () {
		return player;
	}
}
