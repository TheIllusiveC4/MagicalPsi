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
