package com.sebasphere.udaexpansions.common;

import static uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection.DOWN;
import static uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection.EAST;
import static uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection.NORTH;
import static uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection.SOUTH;
import static uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection.UP;
import static uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection.WEST;

import net.minecraft.entity.player.EntityPlayerMP;
import uk.co.mysterymayhem.gravitymod.api.API;

public class Gravity {

	private static EntityPlayerMP       player;


	public static void setNorth () {
		API.forceSetPlayerGravity(NORTH, player, false);
	}

	public static void setEast () {
		API.forceSetPlayerGravity(EAST, player, false);
	}

	public static void setSouth () {
		API.forceSetPlayerGravity(SOUTH, player, false);
	}

	public static void setWest () {
		API.forceSetPlayerGravity(WEST, player, false);
	}

	public static void setDown () {
		API.forceSetPlayerGravity(DOWN, player, false);
	}

	public static void setUp () {
		 API.forceSetPlayerGravity(UP, player, false);
	}

	public static EntityPlayerMP getPlayer () {
		return player;
	}

	public static void setPlayer (EntityPlayerMP player) {
		Gravity.player = player;
	}

}
