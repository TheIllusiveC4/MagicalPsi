/*
 * Copyright (c) 2020 C4
 *
 * This file is part of Magical Psi, a mod made for Minecraft.
 *
 * Magical Psi is free software: you can redistribute it and/or modify it under the terms of the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International Public License.
 *
 * You should have received a copy of the license along with Magical Psi.
 * If not, see <https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode.txt>.
 */

package top.theillusivec4.magipsi.server;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import top.theillusivec4.magipsi.MagicalPsi;
import vazkii.psi.common.lib.LibMisc;

public class ServerEventHandler {

  // Fixes a datapack loading error when loading Magical Psi into existing worlds
  @SubscribeEvent
  public void serverStarting(final FMLServerStartingEvent evt) {
    ResourcePackList<ResourcePackInfo> resourcepacklist = evt.getServer().getResourcePacks();
    List<ResourcePackInfo> resourcePackInfos = (List<ResourcePackInfo>) resourcepacklist
        .getEnabledPacks();

    if (!resourcePackInfos.isEmpty() && resourcePackInfos.get(0).getName()
        .equals("mod:" + MagicalPsi.MODID)) {
      ResourcePackInfo resourcepackinfo = resourcepacklist.getPackInfo("mod:" + LibMisc.MOD_ID);

      if (resourcepackinfo != null) {
        MagicalPsi.LOGGER.info("Misaligned datapack order detected, reordering...");
        List<ResourcePackInfo> list = Lists.newArrayList(resourcepacklist.getEnabledPacks());
        list.remove(resourcepackinfo);
        resourcepacklist.setEnabledPacks(list);
        WorldInfo worldinfo = evt.getServer().getWorld(DimensionType.OVERWORLD).getWorldInfo();
        worldinfo.getEnabledDataPacks().clear();
        resourcepacklist.getEnabledPacks()
            .forEach((pack) -> worldinfo.getEnabledDataPacks().add(pack.getName()));
        worldinfo.getDisabledDataPacks().add(resourcepackinfo.getName());
        MagicalPsi.LOGGER.info("Datapacks reordered, reloading...");
        evt.getServer().reload();
        MagicalPsi.LOGGER.info("Reloading complete.");
      }
    }
  }
}
