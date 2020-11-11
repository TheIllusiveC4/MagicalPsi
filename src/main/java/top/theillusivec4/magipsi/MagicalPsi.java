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

package top.theillusivec4.magipsi;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.magipsi.client.ClientMagicalPsi;
import top.theillusivec4.magipsi.client.proxy.MagipsiExecutor;

@Mod(MagicalPsi.MODID)
public class MagicalPsi {

  public static final String MODID = "magipsi";
  public static final Logger LOGGER = LogManager.getLogger();

  public MagicalPsi() {
    DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> MagipsiExecutor::new);
    ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST,
        () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
  }

  private void clientSetup(final FMLClientSetupEvent evt) {
    ClientMagicalPsi.setupPsiOverrides();
  }
}

