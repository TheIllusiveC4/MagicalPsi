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

package top.theillusivec4.magipsi.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import top.theillusivec4.magipsi.MagicalPsi;
import vazkii.psi.common.block.base.ModBlocks;

public class PsiOverride {

  public static void setup() {

    try {
      setFinalStatic(ModBlocks.class.getDeclaredField("cadAssembler"), new RodConstructorBlock(
          Block.Properties.create(Material.WOOD).hardnessAndResistance(5, 10).sound(SoundType.WOOD)
              .notSolid()));
      setFinalStatic(ModBlocks.class.getDeclaredField("programmer"), new InscriberBlock(
          Block.Properties.create(Material.WOOD).hardnessAndResistance(5, 10).sound(SoundType.WOOD)
              .notSolid()));
    } catch (NoSuchFieldException e) {
      MagicalPsi.LOGGER.error("Could not find fields while attempting to override Psi!");
      e.printStackTrace();
    } catch (Exception e) {
      MagicalPsi.LOGGER.error("Encountered unknown error while attempting to override Psi!");
      e.printStackTrace();
    }
  }

  private static void setFinalStatic(Field field, Object value) throws Exception {
    field.setAccessible(true);
    Field mod = Field.class.getDeclaredField("modifiers");
    mod.setAccessible(true);
    mod.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    field.set(null, value);
  }
}
