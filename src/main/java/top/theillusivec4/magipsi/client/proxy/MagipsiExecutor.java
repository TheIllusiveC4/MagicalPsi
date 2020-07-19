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

package top.theillusivec4.magipsi.client.proxy;

import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ClientResourcePackInfo;
import net.minecraft.resources.IPackFinder;
import net.minecraft.resources.PackCompatibility;
import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;
import top.theillusivec4.magipsi.MagicalPsi;

public class MagipsiExecutor {

  private static ResourcePack resourcePack;

  // Code derived from Enchanted Book Redesign by Tfarcenim
  // Enchanted Book Redesign uses the Unlicense License
  @SuppressWarnings("ConstantConditions")
  public MagipsiExecutor() {
    List<ModFileInfo> modFiles = ModList.get().getModFiles();

    for (ModFileInfo modFileInfo : modFiles) {

      if (modFileInfo.getMods().get(0).getModId().equals(MagicalPsi.MODID)) {
        resourcePack = new MagipsiProxyPack(modFileInfo.getFile());
        break;
      }
    }
    Minecraft minecraft = Minecraft.getInstance();

    if (minecraft != null) {
      ResourcePackList<ClientResourcePackInfo> resourcePackList = Minecraft.getInstance()
          .getResourcePackList();

      resourcePackList.addPackFinder(new IPackFinder() {
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ResourcePackInfo> void addPackInfosToMap(
            @Nonnull Map<String, T> packNameToInfo,
            @Nonnull ResourcePackInfo.IFactory<T> packInfoFactory) {
          T pack = (T) new ClientResourcePackInfo(MagicalPsi.MODID, true, () -> resourcePack,
              new StringTextComponent(resourcePack.getName()),
              new StringTextComponent(resourcePack.getName()), PackCompatibility.COMPATIBLE,
              ResourcePackInfo.Priority.TOP, true, null, true);
          packNameToInfo.put(MagicalPsi.MODID, pack);
        }
      });
    }
  }
}
