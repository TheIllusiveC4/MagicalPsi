package top.theillusivec4.magipsi.common;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.magipsi.MagicalPsi;
import vazkii.psi.common.block.tile.TileProgrammer;
import vazkii.psi.common.lib.LibBlockNames;
import vazkii.psi.common.lib.LibMisc;

@Mod.EventBusSubscriber(modid = MagicalPsi.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MagipsiBlocks {

  public static final Block INSCRIBER = new InscriberBlock(
      Block.Properties.create(Material.WOOD).hardnessAndResistance(5, 10).sound(SoundType.WOOD)
          .notSolid());
  public static final Block CONSTRUCTOR = new ConstructorBlock(
      Block.Properties.create(Material.WOOD).hardnessAndResistance(5, 10).sound(SoundType.WOOD)
          .notSolid());

  @SubscribeEvent(priority = EventPriority.LOW)
  public static void registerBlocks(RegistryEvent.Register<Block> evt) {
    evt.getRegistry()
        .registerAll(INSCRIBER.setRegistryName(LibMisc.MOD_ID, LibBlockNames.PROGRAMMER),
            CONSTRUCTOR.setRegistryName(LibMisc.MOD_ID, LibBlockNames.CAD_ASSEMBLER));
  }

  @SuppressWarnings("ConstantConditions")
  @SubscribeEvent(priority = EventPriority.LOW)
  public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> evt) {
    evt.getRegistry().register(
        TileEntityType.Builder.create(TileProgrammer::new, INSCRIBER).build(null)
            .setRegistryName(INSCRIBER.getRegistryName()));
  }
}
