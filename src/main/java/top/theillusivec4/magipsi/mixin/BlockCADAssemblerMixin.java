package top.theillusivec4.magipsi.mixin;

import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.psi.common.block.BlockCADAssembler;

@Mixin(BlockCADAssembler.class)
public class BlockCADAssemblerMixin extends HorizontalBlock {

  protected BlockCADAssemblerMixin(Properties builder) {
    super(builder);
  }

  @SuppressWarnings("deprecation")
  @Nonnull
  @Override
  public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader worldIn,
                             @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
    VoxelShape top = Block.makeCuboidShape(0, 6, 0, 16, 16, 16);
    VoxelShape middle = Block.makeCuboidShape(4, 2, 4, 12, 6, 12);
    VoxelShape bottom = Block.makeCuboidShape(2, 0, 2, 14, 2, 14);
    return VoxelShapes.or(top, middle, bottom);
  }
}
