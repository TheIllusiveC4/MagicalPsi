package top.theillusivec4.magipsi.client;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;
import net.minecraftforge.fml.packs.ModFileResourcePack;
import top.theillusivec4.magipsi.MagicalPsi;

public class MagipsiPackLoader {

  private static ResourcePack resourcePack;

  @SuppressWarnings("ConstantConditions")
  public MagipsiPackLoader() {
    List<ModFileInfo> modFileInfos = ModList.get().getModFiles();

    for (ModFileInfo modFileInfo : modFileInfos) {

      if (modFileInfo.getMods().get(0).getModId().equals(MagicalPsi.MODID)) {
        resourcePack = new MagicalPsiResourcePack(modFileInfo.getFile());
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

  private static class MagicalPsiResourcePack extends ModFileResourcePack {

    public MagicalPsiResourcePack(ModFile modFile) {
      super(modFile);
    }

    @Override
    public String getName() {
      return "Magical Psi";
    }
  }
}
