package com.sebasphere.udaexpansions.common;

import com.sebasphere.udaexpansions.commands.GravityDirectionCommand;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import uk.co.mysterymayhem.gravitymod.api.API;
import uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection;


public class GravityFuckerUpper {
    GravityDirectionCommand gravity = new GravityDirectionCommand();

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        //It initially grabs a "PLACEHOLDER" String
        String gravityDirection = gravity.getGravityDirection();

        if (event.phase == TickEvent.Phase.END) return;
        for (EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().getServer().getPlayerList().getPlayers()) {
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
                case "PLACEHOLDER":
                    break;
                default:
                    //this likely shouldn't happen
                    System.out.println("AAAAAA I'm SPAMMING CONSOLE, PLEASE REPORT ME");
            }
        }
    }
}
