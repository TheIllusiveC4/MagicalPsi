package top.theillusivec4.magipsi.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.psi.common.block.BlockProgrammer;

@Mixin(BlockProgrammer.class)
public class BlockProgrammerMixin {

  private static final VoxelShape SHAPE;

  static {
    VoxelShape top = Block.makeCuboidShape(0, 10, 0, 16, 16, 16);
    VoxelShape leg1 = Block.makeCuboidShape(1, 0, 1, 3, 10, 3);
    VoxelShape leg2 = Block.makeCuboidShape(13, 0, 13, 15, 10, 15);
    VoxelShape leg3 = Block.makeCuboidShape(1, 0, 13, 3, 10, 15);
    VoxelShape leg4 = Block.makeCuboidShape(13, 0, 1, 15, 10, 3);
    SHAPE = VoxelShapes.or(top, leg1, leg2, leg3, leg4);
  }

  @Inject(at = @At("HEAD"), method = "getShape", cancellable = true)
  public void _magipsi_getShape(BlockState state, IBlockReader world, BlockPos pos,
                                ISelectionContext ctx, CallbackInfoReturnable<VoxelShape> ci) {
    ci.setReturnValue(SHAPE);
  }
}
