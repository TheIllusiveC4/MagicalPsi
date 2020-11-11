package top.theillusivec4.magipsi.client.proxy;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;
import top.theillusivec4.magipsi.MagicalPsi;

public class MagipsiExecutor {

  private static ResourcePack resourcePack;

  @SuppressWarnings("ConstantConditions")
  public MagipsiExecutor() {
    List<ModFileInfo> modFileInfos = ModList.get().getModFiles();

    for (ModFileInfo modFileInfo : modFileInfos) {

      if (modFileInfo.getMods().get(0).getModId().equals(MagicalPsi.MODID)) {
        resourcePack = new MagipsiProxyPack(modFileInfo.getFile());
        break;
      }
    }
    Minecraft minecraft = Minecraft.getInstance();

    if (minecraft != null) {
      ResourcePackList resourcePackList = minecraft.getResourcePackList();
      resourcePackList.addPackFinder((infoConsumer, infoFactory) -> infoConsumer.accept(
          ResourcePackInfo
              .createResourcePack(MagicalPsi.MODID, true, () -> resourcePack, infoFactory,
                  ResourcePackInfo.Priority.TOP, IPackNameDecorator.PLAIN)));
    }
  }
}
