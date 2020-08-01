package com.sebasphere.udaexpansions.compat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection;
import uk.co.mysterymayhem.gravitymod.common.capabilities.gravitydirection.GravityDirectionCapability;

@SideOnly(Side.CLIENT)
public class ModHooks {

	@Method(modid = "mysttmtgravitymod")
	public static void setDirection(EntityPlayer player, EnumGravityDirection direction) {
		GravityDirectionCapability.setGravityDirection(player, direction, false);
	}
}
