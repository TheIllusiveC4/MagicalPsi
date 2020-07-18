package top.theillusivec4.magipsi;

import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.resources.ClientResourcePackInfo;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.resources.IPackFinder;
import net.minecraft.resources.PackCompatibility;
import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
import net.minecraft.util.LazyValue;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;
import top.theillusivec4.magipsi.client.MagicalPsiProxyPack;
import top.theillusivec4.magipsi.client.ModelFocusingPlate;

public class MagicalPsiExecutor {

  private static ResourcePack resourcePack;

  public MagicalPsiExecutor() {
    List<ModFileInfo> modFiles = ModList.get().getModFiles();

    for (ModFileInfo modFileInfo : modFiles) {

      if (modFileInfo.getMods().get(0).getModId().equals(MagicalPsi.MODID)) {
        resourcePack = new MagicalPsiProxyPack(modFileInfo.getFile());
        break;
      }
    }

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

  public static LazyValue<BipedModel<?>> getArmorModel(EquipmentSlotType slot) {
    return new LazyValue<>(() -> new ModelFocusingPlate(slot));
  }
}
