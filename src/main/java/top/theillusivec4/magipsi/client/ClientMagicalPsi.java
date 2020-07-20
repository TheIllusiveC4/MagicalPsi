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

package top.theillusivec4.magipsi.client;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.util.LazyValue;
import top.theillusivec4.magipsi.MagicalPsi;
import top.theillusivec4.magipsi.client.render.FocusingPlateModel;
import top.theillusivec4.magipsi.client.render.SensorLayer;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.common.item.ItemExosuitSensor;
import vazkii.psi.common.item.armor.ItemPsimetalArmor;
import vazkii.psi.common.item.armor.ItemPsimetalExosuitHelmet;
import vazkii.psi.common.item.base.ModItems;

public class ClientMagicalPsi {

  public static void setupPsiOverrides() {
    // todo: Remove when/if Psi implements the PR fix
    ItemColors colors = Minecraft.getInstance().getItemColors();
    colors.register((stack, tintIndex) -> tintIndex < 1 ? -1
            : ((ItemPsimetalExosuitHelmet) stack.getItem()).getColor(stack),
        ModItems.psimetalExosuitHelmet);

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
        model.set(entry.getValue(), ClientMagicalPsi.getArmorModel(entry.getKey()));
      }
    } catch (NoSuchFieldException | IllegalAccessException e) {
      MagicalPsi.LOGGER.error("Reflection error in models!");
    }
  }

  // todo: Remove when/if Psi implements the PR fix
  public static void setupSensorLayer() {
    EntityRendererManager rendererManager = Minecraft.getInstance().getRenderManager();
    rendererManager.getSkinMap().values().forEach(renderer -> renderer
        .addLayer(new SensorLayer<>(renderer, new BipedModel<>(0.5F), new BipedModel<>(1.0F))));
  }

  public static LazyValue<BipedModel<?>> getArmorModel(EquipmentSlotType slot) {
    return new LazyValue<>(() -> new FocusingPlateModel(slot));
  }
}