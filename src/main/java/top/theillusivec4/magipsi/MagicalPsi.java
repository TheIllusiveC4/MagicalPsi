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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.util.LazyValue;
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
import top.theillusivec4.magipsi.client.FocusingPlateModel;
import top.theillusivec4.magipsi.client.MagipsiPackLoader;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.common.item.ItemExosuitSensor;
import vazkii.psi.common.item.armor.ItemPsimetalArmor;
import vazkii.psi.common.item.base.ModItems;

@Mod(MagicalPsi.MODID)
public class MagicalPsi {

  public static final String MODID = "magipsi";
  public static final Logger LOGGER = LogManager.getLogger();

  public MagicalPsi() {
    DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> MagipsiPackLoader::new);
    ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST,
        () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
  }

  private void clientSetup(final FMLClientSetupEvent evt) {
    SpellParam.RED = 0xFF003F;
    SpellParam.GREEN = 0x3FFF00;
    SpellParam.BLUE = 0x007FFF;
    SpellParam.PURPLE = 0xBF7FFF;
    SpellParam.CYAN = 0x00FFBF;
    SpellParam.YELLOW = 0xFFBF00; // For entities
    SpellParam.GRAY = 0x3F3F3F; // For connectors

    ItemExosuitSensor.defaultColor = 0xEFBFFF;
    ItemExosuitSensor.fireColor = 0xFF1F00;
    ItemExosuitSensor.lightColor = 0xFFDF00;
    ItemExosuitSensor.lowHealthColor = 0x7FFF00;
    ItemExosuitSensor.underwaterColor = 0x003FFF;

    try {
      Field model = ItemPsimetalArmor.class.getDeclaredField("model");
      model.setAccessible(true);
      Map<EquipmentSlotType, Item> armor = new HashMap<>();
      armor.put(EquipmentSlotType.FEET, ModItems.psimetalExosuitBoots);
      armor.put(EquipmentSlotType.CHEST, ModItems.psimetalExosuitChestplate);
      armor.put(EquipmentSlotType.LEGS, ModItems.psimetalExosuitLeggings);
      armor.put(EquipmentSlotType.HEAD, ModItems.psimetalExosuitHelmet);

      for (Map.Entry<EquipmentSlotType, Item> entry : armor.entrySet()) {
        model.set(entry.getValue(), new LazyValue<>(() -> new FocusingPlateModel(entry.getKey())));
      }
    } catch (NoSuchFieldException | IllegalAccessException e) {
      MagicalPsi.LOGGER.error("Reflection error in models!");
    }
  }
}

