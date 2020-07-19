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

package top.theillusivec4.magipsi.data;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import top.theillusivec4.magipsi.MagicalPsi;

@Mod.EventBusSubscriber(modid = MagicalPsi.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerator {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {

    if (event.includeServer()) {
      event.getGenerator().addProvider(new MagipsiRecipeProvider(event.getGenerator()));
    }
  }
}
